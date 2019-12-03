package com.example.hive.hook.analyzer;

import org.apache.hadoop.hive.ql.Context;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.hooks.ReadEntity;
import org.apache.hadoop.hive.ql.hooks.WriteEntity;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

public class TestHiveSemanticAnalyzerHook implements HiveSemanticAnalyzerHook {

    private static final Logger log = LoggerFactory.getLogger(TestHiveSemanticAnalyzerHook.class);

    @Override
    public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context, ASTNode ast) throws SemanticException {


        log.info("Pre Analyze UserName:{} , IP:{},Command:{}", context.getUserName(), context.getIpAddress(), context.getCommand());
        return ast;
    }

    @Override
    public void postAnalyze(HiveSemanticAnalyzerHookContext context, List<Task<? extends Serializable>> rootTasks) throws SemanticException {

        log.info("Post Analyze UserName:{} , IP:{},Command:{}", context.getUserName(), context.getIpAddress(), context.getCommand());

        for (ReadEntity readEntity : context.getInputs()) {
            log.info("Post Analyze UserName:{} , readEntity Table : {}", readEntity.getName());
        }

        for (WriteEntity writeEntity : context.getOutputs()) {
            log.info("Post Analyze UserName:{} , WriteEntity Table : {}", writeEntity.getName());

        }


    }
}
