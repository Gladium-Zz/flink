package com.gladium.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.apache.flink.util.Collector;

/**
 * @author gladium
 * @version v1.0
 * @date 2022/8/7 17:49
 * @description 有界流
 */
public class BoundedStreamWordCount {

    public static void main(String[] args) throws Exception {
        // 1.创建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //2.读取文件
        DataStreamSource<String> lineDateStreamSource = env.readTextFile("input/words.text");

        //3.转换计算
        SingleOutputStreamOperator<Tuple2<String, Long>> wordAndOneTuple = lineDateStreamSource.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }

        })
        .returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 分组
        KeyedStream<Tuple2<String, Long>, String> wordAndOneKeyedStream = wordAndOneTuple.keyBy(data -> data.f0);

        //求和
        SingleOutputStreamOperator<Tuple2<String, Long>> sum = wordAndOneKeyedStream.sum(1);

        sum.print();

        // 启动执行
        env.execute();
        

    }


}