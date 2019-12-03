package com.example.hive.hook.redactor;


import org.apache.hadoop.hive.ql.hooks.Redactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRedactor extends Redactor {
    private static final Logger log = LoggerFactory.getLogger(TestRedactor.class);

    @Override
    public String redactQuery(String query) {
        log.info(" TestRedactor  query: {} ", query);
        return super.redactQuery(query);
    }
}
