package com.xxxx.springsecuritydemo.service;

import com.xxxx.springsecuritydemo.entity.Schedule;
import com.xxxx.springsecuritydemo.model.request.SchedulesRequest;
import com.xxxx.springsecuritydemo.model.response.SchedulesResponse;

import java.util.Date;
import java.util.List;

public interface IScheduleService {

    Schedule getOne(Integer Id);

    Schedule getAll();

    Schedule save(Schedule schedule);

    List<Schedule>  saveAll(List<Schedule> schedules,Date dateList);

    List<Schedule>  saveAll(SchedulesRequest schedulesRequest);

    List<Schedule> getSchedule(Date date);

    List<SchedulesResponse> getSchedules(Date startDate, Date endDate);


}
