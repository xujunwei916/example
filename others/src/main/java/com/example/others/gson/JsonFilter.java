package com.example.others.gson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class JsonFilter {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String body = "{\"timestamp\":\"2019-12-20T09:21:02.010713+0800\",\"flow_id\":89503385851249,\"in_iface\":\"em2\",\"event_type\":\"flow\",\"vlan\":[9],\"src_ip\":\"a1b0bqGgwa9vzvfaAW==\",\"src_port\":40720,\"dest_ip\":\"bvDOgX4brXzwFfi=\",\"dest_port\":5672,\"proto\":\"TCP\",\"app_proto\":\"failed\",\"flow\":{\"pkts_toserver\":14,\"pkts_toclient\":11,\"bytes_toserver\":1425,\"bytes_toclient\":1204,\"start\":\"2019-12-20T09:19:01.857457+0800\",\"end\":\"2019-12-20T09:20:01.940475+0800\",\"age\":60,\"state\":\"closed\",\"reason\":\"timeout\",\"alerted\":false},\"tcp\":{\"tcp_flags\":\"1f\",\"tcp_flags_ts\":\"1b\",\"tcp_flags_tc\":\"1e\",\"syn\":true,\"fin\":true,\"rst\":true,\"psh\":true,\"ack\":true,\"state\":\"closed\"},\"trackinfo\":{\"byteoffset\":\"495400\",\"file\":\"/opt/logs/flow/flow-log-2019-12-20-09-21.json\",\"agent_id\":\"4_1\",\"project_id\":\"4\",\"host\":\"172.17.19.63\"}}";


        JsonNode top = mapper.readTree(body);
        ObjectNode nodes = null;
        if (top instanceof ObjectNode) {
            nodes = (ObjectNode) top;
        } else {
            return ;
        }

        ObjectNode objectNode = mapper.createObjectNode();


        List<List<String>>fieldsList = new ArrayList<>();
        fieldsList.add(Arrays.asList("in_iface".split("\\.")));
        fieldsList.add(Arrays.asList("flow.pkts_toserver".split("\\.")));
        fieldsList.add(Arrays.asList("vlan".split("\\.")));

        for (List<String> field : fieldsList) {
            JsonNode tmpNode = nodes;
            JsonNode tmpResultNode = null;
            String lastField=null;
            for (String f : field) {
                JsonNode value = tmpNode == null ? null : tmpNode.get(f);
                JsonNode resultValue =  tmpResultNode==null? objectNode:tmpResultNode.get(lastField);

                if (resultValue == null) {
                    resultValue = mapper.createObjectNode();
                }
                if(lastField!=null){
                    ((ObjectNode) tmpResultNode).set(lastField, resultValue);
                }

                tmpNode = value;
                tmpResultNode = resultValue;
                lastField =f;
            }
            ((ObjectNode) tmpResultNode).set(lastField, tmpNode);
        }

        System.out.println(objectNode.toString());
    }
}
