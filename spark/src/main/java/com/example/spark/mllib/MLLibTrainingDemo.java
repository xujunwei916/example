package com.example.spark.mllib;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.GBTClassifier;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.tuning.CrossValidator;
import org.apache.spark.ml.tuning.CrossValidatorModel;
import org.apache.spark.ml.tuning.ParamGridBuilder;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.util.Arrays;

public class MLLibTrainingDemo {

    private static void setLrPipelineAndParams(RFormulaModel formula,Pipeline pipeline,ParamGridBuilder paramGridBuilder){
        LogisticRegression mlr = new LogisticRegression()
                .setFamily("multinomial")
                .setFeaturesCol("features")
                .setLabelCol("label");
        pipeline.setStages(new PipelineStage[]{formula,mlr});
        paramGridBuilder.addGrid(mlr.regParam(), new double[] {0,0.3,0.8,1})
                .addGrid(mlr.elasticNetParam(), new double[] {0,0.3,0.8,1});
    }

    private static void setGbdtPipelineAndParams(RFormulaModel formula,Pipeline pipeline,ParamGridBuilder paramGridBuilder){
        GBTClassifier gbt = new GBTClassifier()
                .setFeaturesCol("features")
                .setLabelCol("label");
        pipeline.setStages(new PipelineStage[]{formula,gbt});
        paramGridBuilder.addGrid(gbt.maxIter(), new int[] {250})
                .addGrid(gbt.maxDepth(), new int[] {8})
                .addGrid(gbt.minInstancesPerNode(), new int[] {60})
                .addGrid(gbt.subsamplingRate(),new double[]{0.8});
    }

    public static void genderModelTrain(SparkSession spark,String isCV,String modelType,String trainDataPath,String modelSavePath){
        DataFrameReader reader = spark.read()
                .format("csv")
                .option("header", "true")
                .option("inferSchema", "true");
        Dataset<Row> trainingData = reader.load(trainDataPath);
        trainingData.cache();

        RFormulaModel formula = new RFormula()
                .setFormula("label ~ .-uid")
                .setFeaturesCol("features").fit(trainingData);

        /*
          创建管道组合数据转换和模型组件
         */
        Pipeline pipeline = new Pipeline();
        /*
          创建网格搜索组件
         */
        ParamGridBuilder paramGridBuilder = new ParamGridBuilder();
        switch(modelType){
            case "LR":
                setLrPipelineAndParams(formula,pipeline,paramGridBuilder);
                break;
            case "GBDT":
                setGbdtPipelineAndParams(formula,pipeline,paramGridBuilder);
                break;
            default:
                setLrPipelineAndParams(formula,pipeline,paramGridBuilder);
                break;
        }
        ParamMap[] paramGrid = paramGridBuilder.build();

        MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator()
                .setLabelCol("label")
                .setPredictionCol("prediction")
                .setMetricName("accuracy");

        if(isCV.equals("1")){
            //交叉验证
            CrossValidator cv = new CrossValidator()
                    .setEstimator(pipeline)
                    .setSeed(0)
                    .setNumFolds(5)
                    .setEvaluator(evaluator)
                    .setEstimatorParamMaps(paramGrid);
             /*
              训练
             */
            CrossValidatorModel cvModel = cv.fit(trainingData);

            Dataset<Row> predictions = cvModel.transform(trainingData);
            //predictions.select("probability","prediction").show(2);
            double accuracy = evaluator.evaluate(predictions);
            System.out.println("accuracy = " + accuracy);
            System.out.println("best_model:"+cvModel.bestModel());
            System.out.println("best_model param map:"+cvModel.getEstimatorParamMaps().toString());
            System.out.println("avg metrics:"+Arrays.toString(cvModel.avgMetrics()));
            PipelineModel pipBestModel = (PipelineModel)cvModel.bestModel();
            try {
                pipBestModel.save(modelSavePath);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            PipelineModel model = pipeline.fit(trainingData);
            Dataset<Row> predictions = model.transform(trainingData);
            double accuracy = evaluator.evaluate(predictions);
            System.out.println("accuracy = " + accuracy);
            try {
                model.save(modelSavePath);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]){
        SparkSession spark = SparkSession
                .builder()
                .appName("MLLibTrainingDemo")
                .getOrCreate();
        genderModelTrain(spark,args[0],args[1],args[2],args[3]);
        spark.close();
    }
}
