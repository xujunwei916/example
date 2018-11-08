package com.example.spark.job.example.mllib

import org.json4s.JValue

case class Metadata(
      className: String,
      uid: String,
      timestamp: Long,
      sparkVersion: String,
      params: JValue,
      metadata: JValue,
      metadataJson: String) 