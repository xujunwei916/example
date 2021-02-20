import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TestRegex {

 static Map<String, Pattern> map =  new HashMap<>();
  static {
    map.put("dimension",Pattern.compile("[\\u4e00-\\u9fa5_a-zA-Z0-9]{1,30}"));
    map.put("amount",Pattern.compile("[0-9\\.]{1,30}"));
    map.put("model",Pattern.compile("[0-9+A-Z_]{1,16}"));

  }



  public static void main(String[] args) throws Exception {

    String input =args[0];

    BufferedReader reader = new BufferedReader(new FileReader(input));

    String line = reader.readLine();

    String d[]=line.split(",");

    while((line=reader.readLine())!=null){
      String []values = line.split(",");
      if(values.length!=d.length){
        System.out.println(line);
      }
      for (int i = 0; i < d.length; i++) {
        if(!map.get(d[i]).matcher(values[i]).matches()){
          System.out.println(line);
          System.out.println(d[i]);
        }
      }
    }
    reader.close();
  }
}
