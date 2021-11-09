package com.xxxx.springsecuritydemo.entity;

import com.xxxx.springsecuritydemo.model.TimePeriod;
import com.xxxx.springsecuritydemo.model.request.ScheduleRequest;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * @author: ScheduleType
 * @date: 2021/9/17
 * @description:
 */
@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "schedule_id",nullable=false)
    private Integer id;

    @Column(name = "date",nullable=false)
    private Date date;

    @Column(name = "start_time",nullable=false)
    private Time startTime;

    @Column(name = "end_time",nullable=false)
    private Time endTime;

    @Column(name = "type",nullable=false)
    private Integer type;


    public Schedule() {
    }

    public Schedule(Date date,TimePeriod timePeriod ,int type){
        BeanUtils.copyProperties(timePeriod,this);
        this.type=type;
        this.date=date;
    }
}
