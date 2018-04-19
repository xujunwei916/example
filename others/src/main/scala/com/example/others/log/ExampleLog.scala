package com.example.others.log

import org.apache.log4j.Logger


trait ExampleLog {
val logger= Logger.getLogger(this.getClass)

}
