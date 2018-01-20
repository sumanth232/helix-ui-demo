package com.example.helixdemo;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author sumanth on 09/11/17
 */
public class MyClient {

    public static int getNumberOfNodes() {
        return ThreadLocalRandom.current().nextInt(1,5);
    }

    public static Map<String,Map<Integer,Set<String>>> getServersVsPartitionsExternalView2() {
        Map<String,Map<Integer,Set<String>>> serverVsPartitions = new TreeMap<>();
        serverVsPartitions.put("localhost:8085", ImmutableMap.of(
                1, Sets.newHashSet("key1", "key2"),
                2, Sets.newHashSet("key3", "key4"),
                3, Sets.newHashSet("key5", "key6")
                )
        );

        serverVsPartitions.put("localhost:8087", ImmutableMap.of(
                2, Sets.newHashSet("key3", "key4"),
                3, Sets.newHashSet("key5", "key6"),
                4, Sets.newHashSet("key7", "key8")
                )
        );
        return serverVsPartitions;
    }

    public static Map<String,Map<String,String>> getInstanceVsPartitionsExternalView() {
        return getInstanceVsPartitionsExternalViewMock();
    }

    public static Map<String,Map<String,String>> getInstanceVsPartitionsIdealState() {
        return getInstanceVsPartitionsIdealStateMock();
    }

    public static Set<String> getIdealStatePartitionSet() {
        HashSet<String> allPartitions = Sets.newHashSet("R1_0", "R1_1", "R1_2", "R1_3", "R1_4", "R1_5");
        TreeSet<String> sortedPartitions = new TreeSet<>(allPartitions);
        return sortedPartitions;
    }

    public static void main(String[] args) {
        Set<String> partitionSet = getIdealStatePartitionSet();
        System.out.println(partitionSet);
    }


    private static Map<String,Map<String,String>> getInstanceVsPartitionsExternalViewMock() {
        Map<String,Map<String,String>> serverVsPartitions = new TreeMap<>();
        serverVsPartitions.put("localhost:8085", ImmutableMap.of("R1_1", "SLAVE", "R1_2", "SLAVE"));
        serverVsPartitions.put("localhost:8088", ImmutableMap.of("R1_1", "SLAVE", "R1_2", "SLAVE"));
        return serverVsPartitions;
    }

    private static Map<String,Map<String,String>> getInstanceVsPartitionsIdealStateMock() {
        Map<String,Map<String,String>> serverVsPartitions = new TreeMap<>();
        serverVsPartitions.put("localhost:8085", ImmutableMap.of("R1_1", "MASTER", "R1_2", "SLAVE"));
        serverVsPartitions.put("localhost:8088", ImmutableMap.of("R1_1", "SLAVE", "R1_2", "MASTER"));
        return serverVsPartitions;
    }
}
