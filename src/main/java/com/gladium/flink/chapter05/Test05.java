package com.gladium.flink.chapter05;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author gladium
 * @version v1.0
 * @date 2022/8/11 21:37
 * @description TODO
 */
public class Test05 {

    public static void main(String[] args) throws Exception{

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> streamSource = env.socketTextStream("localhost", 7777);


        streamSource.print("4");

        env.execute();

    }


}
