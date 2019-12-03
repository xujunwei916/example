package com.example.hive.hook.execution;


import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestExcuteWithHookContent implements ExecuteWithHookContext {

    private static final Logger log = LoggerFactory.getLogger(TestExcuteWithHookContent.class);

    @Override
    public void run(HookContext hookContext) throws Exception {

        log.info("TestExcuteWithHookContent:{}",hookContext.getQueryPlan().getQuery().getQueryType());

    }
}
