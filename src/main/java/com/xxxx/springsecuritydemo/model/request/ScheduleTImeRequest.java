package com.xxxx.springsecuritydemo.model.request;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;

/**
 * @author: ScheduleTImeRequest
 * @date: 2021/9/26
 * @description:
 */

@Data
public class ScheduleTImeRequest {
    @DateTimeFormat(pattern = "hh:mm")
    Time startTime;
    @NotNull
    @DateTimeFormat(pattern = "hh:mm")
    Time endTime;
    int type;
}
