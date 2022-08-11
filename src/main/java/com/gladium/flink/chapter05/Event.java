package com.gladium.flink.chapter05;

import java.sql.Timestamp;

/**
 * @author gladium
 * @version v1.0
 * @date 2022/8/11 21:16
 * @description TODO
 */
public class Event {


    public String user;
    public String url;
    public Long timestamp;

    public Event() {
    }

    public Event(String user, String url, Long timestamp) {
        this.user = user;
        this.url = url;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "user='" + user + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + new Timestamp(timestamp) +
                '}';
    }
}
