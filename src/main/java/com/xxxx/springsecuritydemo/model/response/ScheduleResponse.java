package com.xxxx.springsecuritydemo.model.response;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.xxxx.springsecuritydemo.entity.Schedule;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import java.sql.Time;

/**
 * @author: ScheduleResponse
 * @date: 2021/9/22
 * @description:
 */
@Data
public class ScheduleResponse {


    private Time startTime;

    private Time endTime;

    private Integer type;


    public ScheduleResponse(Schedule schedule) {
        BeanUtils.copyProperties(schedule,this);
        

    }
}
