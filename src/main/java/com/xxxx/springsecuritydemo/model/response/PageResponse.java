package com.xxxx.springsecuritydemo.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * @author: PageResponse
 * @date: 2021/4/20
 * @description:
 */
@Getter
@Setter
public class PageResponse {

    public long totalElements;

    public int totalPages;

    public int page;

    public Object object;




    public PageResponse() {
    }

    public PageResponse(Page page) {

        this.totalElements =page.getTotalElements();


        this.totalPages = page.getTotalPages();

        this.page = page.getNumber();

        this.object = page.getClass();

    }


}
