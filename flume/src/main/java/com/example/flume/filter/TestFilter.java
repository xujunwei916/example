package com.example.flume.filter;

import com.google.gson.Gson;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFilter implements Interceptor {

    private Gson GSON = new Gson();

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {


        Map<String, String> header = new HashMap<String, String>();
        Map<String, String> map = event.getHeaders();
        String key = map.get("key");
        String topic = map.get("topic");
        String partition = map.get("partition");
        String timestamp = map.get("timestamp");
        String body = new String(event.getBody());
        String fields[] = body.split("\t");
        if(fields==null|| fields.length<3){
            return null;
        }
        String time = fields[0];
        String ip = fields[1];
        String context = fields[2];
        long longTime = TestUtils.getTimeStampByStr(time);
        header.put("timestamp", longTime == 0 ? timestamp : longTime + "");
        header.put("data_type", topic.contains("dspView") ? "view" : "click");
        header.put("ip", ip);
        header.put("time", time);
        Map<String, String> contextMap = TestUtils.parseQueryString(context);
        header.putAll(contextMap);
        Data data = new Data(time, "**********" + body + "**********", contextMap);
        SimpleEvent event1 = new SimpleEvent();
        event1.setBody(GSON.toJson(data).getBytes(Charset.forName("utf8")));
        event1.setHeaders(header);
        System.out.println("header = "+ event1.getHeaders());
        System.out.println("body = "+ new String(event1.getBody()));
        return event1;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        List<Event> events1= new ArrayList<Event>();
        for (Event event : events) {
            Event e= intercept(event);
            if(e!=null){
                events1.add(e);
            }
        }
        return events1;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new TestFilter();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
