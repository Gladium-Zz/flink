package com.gladium.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author gladium
 * @version v1.0
 * @date 2022/7/31 23:29
 * @description TODO
 */
public class BatchWordCount {


    public static void main(String[] args) {
        // 1.创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 2.从文件中读取
        DataSource<String> lineDataSource = env.readTextFile("input/words.text");

        //3.将每行数据进行分词，转换成二元组类型
        FlatMapOperator<String, Tuple2<String, Long>> wordAndOneTuple = lineDataSource.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
                    // 将一行文本进行拆分
                    String[] words = line.split(" ");
                    // 将每个单词转换成二元组
                    for (String word : words) {
                        out.collect(Tuple2.of(word, 1L));
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.LONG));

    }

}
