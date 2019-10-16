package com.example.others.yml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class TestYml {

    public static void main(String[] args) throws Exception {

        Yaml yaml = new Yaml();
        TestBean testBean = yaml.loadAs(TestYml.class.getClassLoader().getResourceAsStream("test.yml"), TestBean.class);
        System.out.println(testBean);


    }


}

