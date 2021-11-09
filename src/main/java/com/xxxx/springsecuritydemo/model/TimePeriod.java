package com.xxxx.springsecuritydemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.Date;

/**
 * @author: TimePeriod
 * @date: 2021/9/27
 * @description:
 */
@Data
@NoArgsConstructor
public class TimePeriod {


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    java.sql.Time startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    java.sql.Time endTime;



    public void setStartTime(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        this.startTime = new java.sql.Time(date.getTime());
    }

    public void setEndTime(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {

        this.endTime = new java.sql.Time(date.getTime());
    }


}
