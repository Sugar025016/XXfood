package com.xxxx.springsecuritydemo.service.Impl;

import com.xxxx.springsecuritydemo.entity.Schedule;
import com.xxxx.springsecuritydemo.enums.RangeTypeEnums;
import com.xxxx.springsecuritydemo.model.request.SchedulesRequest;
import com.xxxx.springsecuritydemo.model.response.ScheduleResponse;
import com.xxxx.springsecuritydemo.model.response.SchedulesResponse;
import com.xxxx.springsecuritydemo.repository.IScheduleRepository;
import com.xxxx.springsecuritydemo.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: ScheduleService
 * @date: 2021/9/20
 * @description:
 */
@Service
@Transactional
public class ScheduleService implements IScheduleService {
    @Autowired
    private IScheduleRepository iScheduleRepository;

    @Override
    public Schedule getOne(Integer Id) {

        return null;
    }

    @Override
    public Schedule getAll() {
        return null;
    }

    @Override
    public Schedule save(Schedule schedule) {
        return null;
    }

    @Override
    public List<Schedule>  saveAll(List<Schedule> schedules,Date date) {
        iScheduleRepository.deleteByDate(date);
        List<Schedule> scheduleList = iScheduleRepository.saveAll(schedules);
        return scheduleList;
    }

    @Transactional
    @Override
    public List<Schedule> saveAll(SchedulesRequest schedulesRequest) {

        iScheduleRepository.deleteByDateBetweenAndType(schedulesRequest.startDate,schedulesRequest.endDate,schedulesRequest.getSchedulesType());
        List<Schedule> scheduleArrayList = new ArrayList<>();
        List<Date> dates = new ArrayList<Date>();
        for(long i=schedulesRequest.getStartDate().getTime();i<=schedulesRequest.getEndDate().getTime();i+=(1000 * 60 * 60 * 24)){
            if(schedulesRequest.getRangeType()== RangeTypeEnums.Week.getKey()){
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(new Date(i));
                Optional<Integer> any = schedulesRequest.getRangeNumber().stream().filter(v -> v == calendar.get(Calendar.DAY_OF_WEEK)-1).findAny();
                if(!any.isPresent()){
                    continue;
                }
                System.out.println("星期"+(calendar.get(Calendar.DAY_OF_WEEK)-1));
            }
            Date date = new Date(i);
            dates.add(date);
        }
        iScheduleRepository.deleteByDateInAndType(dates,schedulesRequest.getSchedulesType());
        dates.forEach(v1->{
            schedulesRequest.getTimes().stream().forEach(v2->scheduleArrayList.add(new Schedule(v1,v2,schedulesRequest.getSchedulesType())));
        });
        List<Schedule> scheduleList = iScheduleRepository.saveAll(scheduleArrayList);

        return scheduleList;
    }

    @Override
    public List<Schedule> getSchedule(Date date) {
        List<Schedule> byDate = iScheduleRepository.getByDate(date);

        return byDate;
    }

    @Override
    public List<SchedulesResponse> getSchedules(Date startDate, Date endDate) {
        List<Schedule> byDate = iScheduleRepository.getByDateBetween(startDate,endDate);

        List<Date> collect = byDate.stream().map(Schedule::getDate).distinct().collect(Collectors.toList());
        List<SchedulesResponse> schedulesResponses = new ArrayList<SchedulesResponse>();
        collect.stream().forEach(v1->{

            List<Schedule> collect1 = byDate.stream().filter(v2 -> v1.getTime() == v2.getDate().getTime()).collect(Collectors.toList());

            SchedulesResponse schedulesResponse = new SchedulesResponse(v1,collect1);

            schedulesResponses.add(schedulesResponse);

        });


        return schedulesResponses;
    }


}
