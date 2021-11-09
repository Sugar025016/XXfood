package com.xxxx.springsecuritydemo.repository;

import com.xxxx.springsecuritydemo.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

//import java.util.Optional;
//
@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {
//
    @Override
    Schedule getOne(Integer id);



    List<Schedule> getByDate(Date date);

    List<Schedule> getByDateBetween(Date startDate,Date endDate);

    @Modifying
    @Transactional
    long deleteByDate(Date date);

    @Modifying
    long deleteByDateBetweenAndType(Date startDate,Date endDate,int type);

    @Modifying
    long deleteByDateInAndType(List<Date> dateList,int type);



}
