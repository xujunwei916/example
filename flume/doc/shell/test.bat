set FLUME_HOME=D:\java\apache-flume-1.8.0-bin
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_171
set JAVA="%JAVA_HOME%\bin\java.exe"
set JAVA_OPTS=-Xmx1024m
set CONF_DIR=E:\flume\tail_conf
set CONF=%CONF_DIR%\flume-conf.properties
set AGENT=a1
%JAVA%  %JAVA_OPTS% -Dflume.monitoring.type=http -Dflume.monitoring.port=34545 -Dlog4j.configuration=file:\\\%CONF_DIR%\log4j.properties -cp %FLUME_HOME%\lib\* org.apache.flume.node.Application -f %CONF_DIR%\flume-conf.properties -n %AGENT%