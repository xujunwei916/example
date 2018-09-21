package com.example.others.test;

import org.bang50.union2.guess.tools.UrlTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws Exception {


//        Map<String, String> map = new HashMap<>();
//
//        String tmp = map.put("a", "a");
//        System.out.println(tmp);
//        tmp = map.putIfAbsent("a", "b");
//        System.out.println(tmp);
//        System.out.println(map);
//        tmp = map.put("a", "b");
//        System.out.println(tmp);
//        System.out.println(map);

//       String host =  UrlTools.urlDecode("https://www.baidu.com/s?word=%E5%85%8D%E8%B4%B9%E8%AF%BE%E4%BB%B6%E7%BD%91&tn=site888_3_pg&lm=-1&ssl_s=1&ssl_c=ssl1_1642180e1ce#ie=utf-8&f=8&rsv_bp=1&tn=site888_3_pg&wd=%E5%B0%8F%E5%AD%A6%E9%9F%B3%E4%B9%90%E5%8F%A4%E8%AF%97%E6%96%B0%E5%94%B1&oq=%25E5%2585%258D%25E8%25B4%25B9%25E8%25AF%25BE%25E4%25BB%25B6%25E7%25BD%2591&rsv_pq=a55e485f0001c883&rsv_t=749fqf40axA7ZcWH6C1YrlRR82zIgn5yYoXP1MEpr12%2BCJ603BTeFY10RT9%2FjPdIhfBI&rqlang=cn&rsv_enter=1&rsv_sug3=27&rsv_sug1=28&rsv_sug7=100&bs=%E5%85%8D%E8%B4%B9%E8%AF%BE%E4%BB%B6%E7%BD%91");
//        System.out.println(host);
//        BigDecimal  bgLon = new BigDecimal(111.55555555555555);
//        double bb=bgLon.setScale(5, BigDecimal.ROUND_FLOOR).doubleValue();
//        System.out.println(bb);


        Set<String> set = new HashSet<String>();
        set.addAll(Arrays.asList(new String[]{
                "E4BB59014733000E15CB072010EF",
                "53AB0AAEACE164B9713A2145AE3B",
                "7B73564C501B000EC971397001AB",
                "FF0859266FFA0007E5C3625B29D2",
                "6CDC5835BA1500085B433D990A66",
                "6E4C56E60DCE0005567A15810144",
                "CB0C597F1C3A0007B203213611A0",
                "58E058B41CA80008891DA3FE4ADC",
                "154555CB47B0000A2E569C9D011D",
                "03C85ADD7E6C000D2C1FD6A062EC",
                "4D0458886EEC000ACEF808FC8863",
                "97745749474A00016A9F3307045F",
                "990259D0FF6E000A236CEA548EB8",
                "182FF56C99BD4A46506737A92C35",
                "091155024FFF00041889B618003D",
                "4CDF59BB949600007D8D6421688F",
                "E4AE568BB7B90006C286BD9C05F4",
                "46465930BE2D0000FE82EDBF22EB",
                "6DD65A7D17BE0009332676670533",
                "E7BB56AAE99F00051788128A0410",
                "44861EAA21CE2BE649D17AB53066",
                "052659261BD200041C0A77F4152C",
                "57E6588B65E90007CCF06AAD616E",
                "4C5159978A5F00020567195F365F",
                "5F9258A2CEF80007E448A3DA40BC",
                "159C58D2479C000D6A31F81A788F",
                "1599598FBEE8000D052175820512",
                "58135A1FB8E40008AA8494622694",
                "441D591E649700024FD11D191CB7",
                "9E8B59B757F3000B74DC91A42C5E",
                "5B055AFD72030004BC64366D0001",
                "5ED45905F82900094803701412A4",
                "1A0C58996FD700079B81303E54A4",
                "ADFE5657EEBB000A668BEA6200C5",
                "94F65A2B75A5000ADA6704B141FB",
                "AEEE594F170A000363D6A2931243",
                "C2CB590011DA0004EFFE31C12D1F",
                "C9E25878930C0007630B74CF4139",
                "3C2E5AC4D0DE000DD195A3507EC8",
                "A68857D4C48B0002F46289859E10",
                "9B1B5A7E8AD000030A52493759DC",
                "1663586CF739000DA5AA9E958F38",
                "6D1F595C9E1B0009213754245738",
                "1573525666510004A8C82B0F020B",
                "F49F5694C95F0007284FAB240637",
                "F27B566BC56F000D8CD85AB401CE",
                "155955F810280003DCD58C640222",
                "4BF125C503B84600E9E6CC95B858",
                "7A8859184970000ABE265F9D45FC",
                "083D56C5C53F0009C554972806CA",
                "398E57D0C63B000547C6285C8D98",
                "4565567FB2B2000D05EAB8800662",
                "0E025795AA8300064664A849B76F",
                "3E735765F6B2000C6EA0EA734644",
                "D6505981E745000DD8C70FB67E59",
                "DC5C579D9CC70005D508138D11CC",
                "AA1D58148F0C00015C4C39371E30",
                "6772573181190001A6E575B20193",
                "CA5E5742637C000156AED2C701B2",
                "E5755737E56600065C12C21301DF",
                "AE6F56AD9541000BB4250A0C0296",
                "94B657DFB6A1000A73D48E1FC0CF",
                "6110575A3F3900084462126DC6E6",
                "37145AB70DAF0003502F20ED76FE",
                "416F59393B660005CE61B93C5EA4",
                "5E30592BD8A200086BB8D09A0579",
                "9CEC55E2A01F000651A1A0FD01B3",
                "D5EA5983E80000024B53C29C648A",
                "F15F3C665476F231217A15F6DB80",
                "9417567A5C6B000994F156790036",
                "764D562866680001B28F672103D8",
                "FB6857B171F3000509B18897BDB6",
                "0A1B5AC0B3D3000463F4F2A4299B",
                "5BD657E73B2B0000EFC77BF83C96",
                "AC45591D0005000B65A8C6921077",
                "758C595F0570000D54CC5A1A1774",
                "AAB85AB7062D000E9FBE87C76D39",
                "0DA55639EB86000C17727B2703AC",
                "B76D584E7C36000AEC96FAFE9082",
                "D1F92676BF4969C5D6229AB7879E",
                "62D75A3719170005BD0237E242E9",
                "99B957BEA7E2000610F70B381EA4",
                "BDB257C28AB10002AA1FE20D31E4",
                "BD9D59FEF9BB000E27F022ED637A",
                "BF845A97532A0003EE0BA8F107C6",
                "9129582E489C00049558523F9789",
                "74DA58C1420D00016CD7BFB691A8",
                "5C9A54C49C200008DD4B9C7801E7",
                "ABAE59E2D1FA000C6DC941075DFA",
                "FD66575BCA6900040BEC1EEA8752",
                "55AE5A16A51100080F69E1D8405D",
                "7504598FAC170001A98003AA7369",
                "AAC5592BCF850007BD63633B6A74",
                "910F59B6591F0001689301A21AEB",
                "25E957667B5D000145F8300A2649",
                "281A5904AC870006986319FD5331",
                "743E5778A6B90006E5C3C3CED0BF",
                "985A5AAE112F00083A843A5B22F0",
                "9592594B41490002E07536803A5D",
                "043559F7E22A00023DDC0B576C1E",
                "414658A0F4980001AE38E0245802",
                "1E895875F05C000E9662E99456A1",
                "6F045A0F13D10005C1B83ADD68B8",
                "9A6156E27CAB0003150C79D10247",
                "26775954B1D8000F1752559E5BFD",
                "EE49599D205E0005C4FDAD586911",
                "95F5599E47D9000AE8E87C1A1865",
                "103E5938999B000C4B39D47E4D51",
                "25DE5635DC47000233D9145C00D9",
                "23B158F563C20000288CF4765C09",
                "C232591A3F070002CB0DBC4C1E08",
                "9C4E5774BA73000A35728B4F2C6B",
                "3E625A214EFB0002DF63F1C0481F",
                "C452583B9AEA0007019ED9607C06",
                "C6835A03E444000D1E8DF14C5C8D",
                "F22057576FFE00034131191BB228",
                "F0415A18F0380000DC38A60B4F27",
                "3CED56963171000B3211027D009A",
                "972E57CD4C8200060B30E108B25F",
                "DCBD57B84E68000729770A4BB434",
                "F30859C8E2AF000DB04A58DE7904",
                "48BD599A398200038CC5511F51B3",
                "7CD257217AF20002E0847CBE05CF",
                "614659422B2300060E2CE58303C9",
                "634059CC55A1000793FC48663F01",
                "D9205829606C0008CBE8D4E1A768",
                "74C2598EBBA300009C6E0D226E58",
                "E44D593E703A00057648529159E4",
                "C17A5A043BD60000AB9FD8C86649",
                "191258D24BE200030F567E5808DA",
                "2A365AC08B510005CE20DC8D21C7",
                "3F3D55D8333D00034E0135A601D0",
                "4CBE573EBCCD000923A2B46600BB",
                "6A4658DBE512000BFD73CDA2878D",
                "5EE555FA2E6000021FF091F50226",
                "18C658EC3D040005681DF297781D",
                "2DE2574D8D540002B737439A06B9",
                "9F020CA652EF711E709A66A5108C",
                "D943566CDCD700000466F34204D8",
                "094D5AF4E9130004BD86B4F00006",
                "9D155A2DEE0D00010FDF9619761C",
                "3F29566ADB9E000BA047D83D07FC",
                "41BA563816E70005A1F3A3C8028D"
        }));


        BufferedReader reader = new BufferedReader(new FileReader("D:\\tmp\\duba.txt"));

        String line = null;
        Map<String ,VO> map  = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("\t");
            String id ="others";
            if (fields.length > 3 && set.contains(fields[2])) {
                id=fields[2];
            }
            VO tmp = map.get(id);
            if(tmp==null){
                tmp= new VO(id);
                map.put(id,tmp);
            }
            tmp.write(line);
        }
        for (VO vo:map.values()
             ) {
            vo.close();
        }


    }
}

class VO{
    private long count=0;
    private BufferedWriter writer =null;
    private String id ;

    public VO(String id) {
        this.id = id;
    }


    public void write(String line) throws Exception{

        if(writer==null){
            writer = new BufferedWriter(new FileWriter("D:\\tmp\\test\\"+id+"_"+(count/100000)));
        }
        if(count%100000==0){
            writer.close();
            writer = new BufferedWriter(new FileWriter("D:\\tmp\\test\\"+id+"_"+(count/100000)));
        }
        writer.write(line);
        writer.newLine();
        count++;

    }
    public void close() throws Exception{
        writer.close();
    }
}
