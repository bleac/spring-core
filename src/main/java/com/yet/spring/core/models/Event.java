package com.yet.spring.core.models;

import lombok.Data;

import java.text.DateFormat;
import java.util.Date;

@Data
public class Event {

    private Integer id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
