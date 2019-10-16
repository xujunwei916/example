package com.example.others.proc;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

public class TestProcess {
    public static void main(String[] args) throws Exception {
//        Process p = Runtime.getRuntime().exec("jps ");
//
//        List<String> result = IOUtils.readLines(p.getInputStream());
//        System.out.println(result);
        String HIVE_UDF_WHITE_LIST =
                "date,decimal,timestamp," + // SENTRY-312
                        "abs,acos,and,array,array_contains,ascii,asin,assert_true,atan,avg," +
                        "between,bin,case,cast,ceil,ceiling,coalesce,collect_set,compute_stats,concat,concat_ws," +
                        "UDFConv,UDFHex,UDFSign,UDFToBoolean,UDFToByte,UDFToDouble,UDFToFloat,UDFToInteger,UDFToLong,UDFToShort,UDFToString," +
                        "context_ngrams,conv,corr,cos,count,covar_pop,covar_samp,create_union,date_add,date_sub," +
                        "datediff,day,dayofmonth,degrees,div,e,elt,ewah_bitmap,ewah_bitmap_and,ewah_bitmap_empty," +
                        "ewah_bitmap_or,exp,explode,field,find_in_set,floor,format_number,from_unixtime," +
                        "from_utc_timestamp,get_json_object,hash,hex,histogram_numeric,hour,if,in,in_file,index," +
                        "inline,instr,isnotnull,isnull," + // java_method is skipped
                        "json_tuple,lcase,length,like,ln,locate,log," +
                        "log10,log2,lower,lpad,ltrim,map,map_keys,map_values,max,min," +
                        "minute,month,named_struct,negative,ngrams,not,or,parse_url,parse_url_tuple,percentile," +
                        "percentile_approx,pi,pmod,positive,pow,power,printf,radians,rand," + // reflect is skipped
                        "regexp,regexp_extract,regexp_replace,repeat,reverse,rlike,round,rpad,rtrim,second," +
                        "sentences,sign,sin,size,sort_array,space,split,sqrt,stack,std," +
                        "stddev,stddev_pop,stddev_samp,str_to_map,struct,substr,substring,sum,tan,to_date," +
                        "to_utc_timestamp,translate,trim,ucase,unhex,union_map,unix_timestamp,upper,var_pop,var_samp," +
                        "variance,weekofyear,when,xpath,xpath_boolean,xpath_double,xpath_float,xpath_int,xpath_long," +
                        "xpath_number,xpath_short,xpath_string,year,base64,cume_dist, decode, dense_rank, first_value," +
                        "lag, last_value, lead, noop, noopwithmap, ntile, nvl, percent_rank, rank, to_unix_timestamp," +
                        "current_database, char, varchar, matchpath, row_number" +
                        "unbase64,windowingtablefunction";
        System.out.println(HIVE_UDF_WHITE_LIST);
    }
}
