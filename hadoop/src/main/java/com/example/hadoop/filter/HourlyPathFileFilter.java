package com.example.hadoop.filter;

import java.util.regex.Pattern;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

public class HourlyPathFileFilter implements PathFilter {

    private Pattern pattern;

    public HourlyPathFileFilter() {
        pattern=Pattern.compile("16.*\\.snappy");
    }

    @Override
    public boolean accept(Path path) {
        System.out.println("-----"+path.getName());
        String name = path.getName();
        if(name.startsWith("section")){
            return true;
        }

        if(pattern.matcher(name).matches()){
            return true;
        }else{
            return false;
        }
//        return !name.startsWith("_") && !name.startsWith(".") && ! name.endsWith("._COPY_");
    }
}
