package com.example.others.java;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

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
            String c = kv[0];
            String p = kv[1];
            if (!nodes.containsKey(c)) {
                nodes.put(c, new Node(c, 2));
            }
            if (!nodes.containsKey(p)) {
                nodes.put(p, new Node(p, 2));
            }
            Node cNode = nodes.get(c);
            Node pNode = nodes.get(p);
            cNode.pNode.add(p);
            pNode.cNode.add(c);
        }


        Map<String, Set<String>> treeList = new HashMap<>();

        for (Node node : nodes.values()) {
            Set<String> set = new HashSet<>();
            if (node.pNode.isEmpty()) {
//                System.out.println("++++++++++++++++++++树的开始+++++++++++++++++++");
                printNode(node, set, nodes, 0);
                treeList.put(node.name, set);
//                System.out.println("++++++++++++++++++++树的结束+++++++++++++++++++");
            }
        }


//        System.out.println(jobs.size());
//        System.out.println(nodes.size());
//        System.out.println(relations.size());
//        System.out.println(treeList.size());


        List<Set<String>> result = new ArrayList<>();


        List<Set<String>> tmp = new ArrayList<>();

        for (Set<String> s : treeList.values()) {
            tmp.add(s);
        }

        while (!tmp.isEmpty()) {
            Set<String> tree = new HashSet<>();

            tree.addAll(tmp.remove(0));


            while (true) {
                int removeI = -1;
                boolean flag =false;
                for (String s: tree) {
                    for (int i = 0; i < tmp.size(); i++) {
                        if(tmp.get(i).contains(s)){
                            removeI = i;
                            break;
                        }
                    }
                    if(removeI!=-1){
                        flag =true;
                        break;
                    }
                }
                if(flag){
                    tree.addAll(tmp.remove(removeI));
                }else {break;}
            }
            result.add(tree);
        }


//        System.out.println(result.size());
        for (Set<String> s: result) {
            System.out.println("树的开始");

            System.out.println("\"任务名称\",\"状态\",\"父任务列表\"");

            for (String job: s                 ) {
                Node node = nodes.get(job);
                System.out.println("\""+job+"\",\""+node.status+"\",\""+StringUtils.join(node.pNode,"|")+"\"");
            }
            System.out.println("树的结束");
            System.out.println("******************************************************");
            System.out.println("******************************************************");
            System.out.println("******************************************************");

        }


    }

    public static void printNode(Node node, Set<String> set, Map<String, Node> nodes, int len) {
//        System.out.println(node.name + "(" + node.status + "," + len + ")");
        set.add(node.name);

        for (String c : node.cNode) {
            Node cNode = nodes.get(c);
            printNode(cNode, set, nodes, len + 1);

        }
    }
}


class Tree {
    Set<String> sets;
}

class Node {

    String name;
    int status;
    List<String> pNode = new ArrayList<>();
    List<String> cNode = new ArrayList<>();

    public Node(String name, int status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", pNode=" + pNode +
                ", cNode=" + cNode +
                '}';
    }
}
