package com.xxxx.springsecuritydemo.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xxxx.springsecuritydemo.entity.Schedule;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: SchedulesResponse
 * @date: 2021/9/22
 * @description:
 */
@Data
public class SchedulesResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date date;

    @JsonProperty("schedules")
    List<ScheduleResponse> scheduleList;

    public SchedulesResponse(Date date, List<Schedule> schedules) {
        this.date = date;
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();
        schedules.stream().forEach(v -> {
            ScheduleResponse scheduleResponse = new ScheduleResponse(v);
            scheduleResponses.add(scheduleResponse);
        });
        this.scheduleList = scheduleResponses;

    }

//    public SchedulesResponse(Date date ,List<ScheduleResponse> scheduleResponse) {
//        this.date=date;
//        this.scheduleResponse=scheduleResponse;
//
//
//    }
}
