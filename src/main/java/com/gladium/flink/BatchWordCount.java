package com.gladium.flink;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

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
        DataSource<String> stringDataSource = env.readTextFile("input/words.text");


    }

}
