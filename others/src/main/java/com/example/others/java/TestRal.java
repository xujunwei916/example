package com.example.others.java;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestRal {
    public static void main(String[] args) throws Exception {

        List<String> jobs = IOUtils.readLines(new FileReader("D:\\tmp\\test\\job.list"));

        Map<String, Node> nodes = new HashMap<>();


        for (String job : jobs) {

            nodes.put(job, new Node(job, 1));

        }


        List<String> relations = IOUtils.readLines(new FileReader("D:\\tmp\\test\\relation.list"));


        for (String relation : relations) {
            String kv[] = relation.split("\t");
            if (!nodes.containsKey(kv[0])) {
                nodes.put(kv[0], new Node(kv[0], 2));
            }
            if (!nodes.containsKey(kv[1])) {
                nodes.put(kv[1], new Node(kv[1], 2));
            }
            Node cNode = nodes.get(kv[0]);
            Node pNode = nodes.get(kv[1]);
//            cNode.pNode.add(pNode);
            pNode.cNode.add(cNode);
        }


        Set<String> set = new HashSet<>();


        for (Node node : nodes.values()) {

            if (set.contains(node.name)) {
                continue;
            } else {
                System.out.println("++++++++++++++++++++树的开始+++++++++++++++++++");

                printNode(node, set,0);

                System.out.println("++++++++++++++++++++树的结束+++++++++++++++++++");
            }

        }


        System.out.println(jobs.size());
        System.out.println(nodes.size());
        System.out.println(relations.size());
        System.out.println(set.size());

//        for (Map.Entry<String,Node> entry :nodes.entrySet()) {
//            String []kv = entry.ge
//
//            nodes.get()
//
//        }


//        Map<String ,Node> tu =  new HashMap<>();


    }

    public static void printNode(Node node, Set<String> set, int len) {
//        for (Node p : node.pNode) {
//            printNode(p, set, len + 1);
//        }
        System.out.println(node.name + "(" + node.status + "," + len + ")");
        set.add(node.name);
        for (Node c : node.cNode) {
            printNode(c, set,len-1);
        }


    }
}


class Node {

    String name;
    int status;
//    List<Node> pNode = new ArrayList<>();
    List<Node> cNode = new ArrayList<>();

    public Node(String name, int status) {
        this.name = name;
        this.status = status;
    }
}
