package com.example.hive.udtf;
 
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.Set;
 
public class Redis_Gmp extends GenericUDTF{
 
	@Override
	public void close() throws HiveException {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void process(Object[] arg0) throws HiveException {
		
		String input = arg0[0].toString();
		String[] split = input.split(",\\[");
		
		String content_id = split[0].replace("(", "");
		String json_str = split[1].replace(")", "");
		json_str = "["+json_str;
		
		JSONArray json_arr = JSONArray.parseArray(json_str);
		for(int i =0 ;i < json_arr.size();i++){
			
			String[] result = new String[5];
			result[0] = content_id;
			JSONObject ele = json_arr.getJSONObject(i);
			Set<String> ks = ele.keySet();
			for(String k : ks){
				result[1] = k;
			}
			result[2] = ele.getJSONObject(result[1]).getString("click");
			result[3] = ele.getJSONObject(result[1]).getString("impression");
			result[4] = ele.getJSONObject(result[1]).getString("ctr");
			forward(result);
			//System.out.println(result[0] + " " + result[1] + " " + result[2] +" "+result[3]+" "+result[4]);
		}
		
	}
	
	@Override
	public StructObjectInspector initialize(ObjectInspector[] args)
			throws UDFArgumentException {
		if (args.length != 1) {
			throw new UDFArgumentLengthException(
					"Redis_Gmp takes only one argument");
		}
		if (args[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
			throw new UDFArgumentException(
					"Redis_Gmp takes string as a parameter");
		}
 
		ArrayList<String> fieldNames = new ArrayList<String>();
		ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
		fieldNames.add("content_id");
		fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
		fieldNames.add("app");
		fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
		fieldNames.add("click");
		fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
		fieldNames.add("impression");
		fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
		fieldNames.add("ctr");
		fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
 
		return ObjectInspectorFactory.getStandardStructObjectInspector(
				fieldNames, fieldOIs);
	}
	
	public static void main(String[] args) throws HiveException {
		
		//(119962233,[{"-1":{"impression":150,"click":0.3438084,"ctr":0.006597938}},{"coolpad":{"impression":56,"click":0.3438084,"ctr":0.018344998}},{"emui":{"impression":64,"click":0,"ctr":0}}])
		//(98337176,[{"-1":{"impression":167,"click":0.9933209,"ctr":0.02424849}},{"ali":{"impression":163,"click":0.9933209,"ctr":0.025131164}}])
		
		Redis_Gmp redis_gmp = new Redis_Gmp();
		
		String s1 = "(98337176,[{\"-1\":{\"impression\":167,\"click\":0.9933209,\"ctr\":0.02424849}},{\"ali\":{\"impression\":163,\"click\":0.9933209,\"ctr\":0.025131164}}])";
		String s2 = "(119962233,[{\"-1\":{\"impression\":150,\"click\":0.3438084,\"ctr\":0.006597938}},{\"coolpad\":{\"impression\":56,\"click\":0.3438084,\"ctr\":0.018344998}},{\"emui\":{\"impression\":64,\"click\":0,\"ctr\":0}}])";
		
		Object[] arg0 = new Object[]{s2};
		
		redis_gmp.process(arg0);
		
		
	}
 
	
	
}