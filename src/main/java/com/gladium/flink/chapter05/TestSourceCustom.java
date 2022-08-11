package com.gladium.flink.chapter05;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.UUID;

/**
 * @author gladium
 * @version v1.0
 * @date 2022/8/11 22:01
 * @description TODO
 */
public class TestSourceCustom {

    private static boolean flag = true;

    public static void main(String[] args) throws Exception{

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);


        DataStreamSource<String> stringDataStreamSource = env.addSource(new SourceFunction<String>() {
            @Override
            public void run(SourceContext<String> sourceContext) throws Exception {
                while (flag) {
                    sourceContext.collect(UUID.randomUUID().toString());
                }
            }

            @Override
            public void cancel() {
                flag = false;
            }
        });

        stringDataStreamSource.print();

        env.execute();

    }


}
