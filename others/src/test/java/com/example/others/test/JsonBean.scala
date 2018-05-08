package com.example.others.test

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import scala.beans.BeanProperty



@JsonIgnoreProperties(ignoreUnknown = true)
 class JsonBean(@BeanProperty var data:Data){
    def this(){
        this(null)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@BeanProperty
class Data(@BeanProperty var date:String,@BeanProperty  installedAppList:String,@BeanProperty  runningAppList:String){
    def this(){
        this(null,null,null)
    }
}
