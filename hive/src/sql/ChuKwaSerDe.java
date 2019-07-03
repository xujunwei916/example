package com.example.hive.serde;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.chukwa.extraction.engine.ChukwaRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.AbstractPrimitiveJavaObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

@SerDeSpec(schemaProps = { serdeConstants.LIST_COLUMNS, serdeConstants.LIST_COLUMN_TYPES,
		ChuKwaSerDe.CHUKWA_COLUMNS_MAPPING })
public class ChuKwaSerDe extends AbstractSerDe {

	public static final Log LOG = LogFactory.getLog(ChuKwaSerDe.class.getName());
	public static final String CHUKWA_COLUMNS_MAPPING = "chukwa.columns.mapping";

	int numColumns;
	String inputRegex;

	Pattern inputPattern;

	StructObjectInspector rowOI;
	List<Object> row;
	List<TypeInfo> columnTypes;
	Object[] outputFields;
	Text outputRowText;
	List<String> cloumnMappingNames;

	boolean alreadyLoggedNoMatch = false;
	boolean alreadyLoggedPartialMatch = false;

	@Override
	public void initialize(Configuration conf, Properties tbl) throws SerDeException {
		// ***************************************************************************//
		// 获得列名和类型
		// ***************************************************************************//
		String clumnMappingProperty=tbl.getProperty(ChuKwaSerDe.CHUKWA_COLUMNS_MAPPING);
		cloumnMappingNames=Arrays.asList(clumnMappingProperty.split(","));
		String columnNameProperty = tbl.getProperty(serdeConstants.LIST_COLUMNS);
		String columnTypeProperty = tbl.getProperty(serdeConstants.LIST_COLUMN_TYPES);
		List<String> columnNames = Arrays.asList(columnNameProperty.split(","));
		columnTypes = TypeInfoUtils.getTypeInfosFromTypeString(columnTypeProperty);
		assert columnNames.size() == columnTypes.size();
		assert cloumnMappingNames.size() == columnTypes.size();
		
		numColumns = columnNames.size();
		List<ObjectInspector> columnOIs = new ArrayList<ObjectInspector>(columnNames.size());
		for (int c = 0; c < numColumns; c++) {
			TypeInfo typeInfo = columnTypes.get(c);
			if (typeInfo instanceof PrimitiveTypeInfo) {
				PrimitiveTypeInfo pti = (PrimitiveTypeInfo) columnTypes.get(c);
				AbstractPrimitiveJavaObjectInspector oi = PrimitiveObjectInspectorFactory
						.getPrimitiveJavaObjectInspector(pti);
				columnOIs.add(oi);
			} else {
				throw new SerDeException(getClass().getName() + " doesn't allow column [" + c + "] named "
						+ columnNames.get(c) + " with type " + columnTypes.get(c));
			}
		}
		rowOI = ObjectInspectorFactory.getStandardStructObjectInspector(columnNames, columnOIs,
				Lists.newArrayList(Splitter.on('\0').split(tbl.getProperty("columns.comments"))));
		row = new ArrayList<Object>(numColumns);
		for (int c = 0; c < numColumns; c++) {
			row.add(null);
		}
		outputFields = new Object[numColumns];
		outputRowText = new Text();
	}

	@Override
	public ObjectInspector getObjectInspector() throws SerDeException {
		// TODO Auto-generated method stub
		return rowOI;
	}

	@Override
	public Class<? extends Writable> getSerializedClass() {
		return ChukwaRecord.class;
	}

	// Number of rows not matching the regex
	long unmatchedRowsCount = 0;
	// Number of rows that match the regex but have missing groups.
	long partialMatchedRowsCount = 0;

	@Override
	public Object deserialize(Writable blob) throws SerDeException {
		ChukwaRecord record = (ChukwaRecord) blob;
		// Otherwise, return the row.
		for (int c = 0; c < numColumns; c++) {
			try {
				String t = record.getValue(cloumnMappingNames.get(c));
				TypeInfo typeInfo = columnTypes.get(c);

				// Convert the column to the correct type when needed and set in
				// row obj
				PrimitiveTypeInfo pti = (PrimitiveTypeInfo) typeInfo;
				switch (pti.getPrimitiveCategory()) {
				case STRING:
					row.set(c, t);
					break;
				case BYTE:
					Byte b;
					b = Byte.valueOf(t);
					row.set(c, b);
					break;
				case SHORT:
					Short s;
					s = Short.valueOf(t);
					row.set(c, s);
					break;
				case INT:
					Integer i;
					i = Integer.valueOf(t);
					row.set(c, i);
					break;
				case LONG:
					Long l;
					l = Long.valueOf(t);
					row.set(c, l);
					break;
				case FLOAT:
					Float f;
					f = Float.valueOf(t);
					row.set(c, f);
					break;
				case DOUBLE:
					Double d;
					d = Double.valueOf(t);
					row.set(c, d);
					break;
				case BOOLEAN:
					Boolean bool;
					bool = Boolean.valueOf(t);
					row.set(c, bool);
					break;
				case TIMESTAMP:
					Timestamp ts;
					ts = Timestamp.valueOf(t);
					row.set(c, ts);
					break;
				case DATE:
					Date date;
					date = Date.valueOf(t);
					row.set(c, date);
					break;
				case DECIMAL:
					HiveDecimal bd = HiveDecimal.create(t);
					row.set(c, bd);
					break;
				case CHAR:
					HiveChar hc = new HiveChar(t, ((CharTypeInfo) typeInfo).getLength());
					row.set(c, hc);
					break;
				case VARCHAR:
					HiveVarchar hv = new HiveVarchar(t, ((VarcharTypeInfo) typeInfo).getLength());
					row.set(c, hv);
					break;
				default:
					throw new SerDeException("Unsupported type " + typeInfo);
				}
			} catch (RuntimeException e) {
				partialMatchedRowsCount++;
				if (!alreadyLoggedPartialMatch) {
					// Report the row if its the first row
					LOG.warn("" + partialMatchedRowsCount + " partially unmatched rows are found, "
							+ " cannot find group " + c + ": " + record);
					alreadyLoggedPartialMatch = true;
				}
				row.set(c, null);
			}
		}
		return row;
	}

	@Override
	public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
		 throw new UnsupportedOperationException(
		          "Chukwa SerDe doesn't support the serialize() method");
	}

	@Override
	public SerDeStats getSerDeStats() {
		// TODO Auto-generated method stub
		return null;
	}

}
