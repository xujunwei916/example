#!/bin/bash

FLUME_HOME=/opt/app/apache-flume-1.8.0-bin
CONF_DIR=/opt/case/xujw/run/flume/conf
AGENT_NAME=a1


${FLUME_HOME}/bin/flume-ng agent -n ${AGENT_NAME} -c ${CONF_DIR} -f ${CONF_DIR}/flume-conf.properties