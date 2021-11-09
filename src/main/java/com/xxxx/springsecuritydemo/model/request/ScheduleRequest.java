package com.xxxx.springsecuritydemo.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxxx.springsecuritydemo.model.TimePeriod;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * @author: ScheduleRequest
 * @date: 2021/9/26
 * @description:
 */
@Data
public class ScheduleRequest {


    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date date;

    List<TimePeriod> openTime;

    List<TimePeriod> outTime;


    public void setOpenTime(List<TimePeriod> openTime) {
        this.openTime = openTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOutTime(List<TimePeriod> outTime) {
        this.outTime = outTime;
    }
}


