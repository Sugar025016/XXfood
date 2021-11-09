package com.xxxx.springsecuritydemo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xxxx.springsecuritydemo.enums.Rcode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;

/**
 * @author: Response
 * @date: 2021/2/28
 * @description:
 */
@Getter
@Setter
@JsonInclude
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success;
    private int code;
    private String message;
    private T data;

    //不能沒有空建構子，redis反序列畫會出錯
    public Response() {
        this.success = true;
        this.code = Rcode.Success.getKey();
    }

    public Response(Rcode r) {
        this.success = (r == Rcode.Success);
        this.code = r.getKey();
        this.message = r.getChinese();
    }

    public Response(Rcode r, T data) {
        this.success = (r == Rcode.Success);
        this.code = r.getKey();

        if (data instanceof Page) {
            PageResponse objectPageResponse = new PageResponse((Page<T>)data);
            this.data = (T)objectPageResponse;
        }else{

            this.data = data;
        }
    }


}
