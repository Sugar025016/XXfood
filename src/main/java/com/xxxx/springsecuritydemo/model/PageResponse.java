package com.xxxx.springsecuritydemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.Instant;

/**
 * @author: PageResponse
 * @date: 2021/10/1
 * @description:
 */
@Getter
@Setter
public class PageResponse {

    @JsonProperty("page")
    PageSet pageSet;

    Object content;

    public PageResponse(Page page) {
        this.pageSet=new PageSet(page);
        this.content = page.getContent();
    }

    @Getter
    @Setter
    public class PageSet {

        @JsonProperty("total_pages")
        int totalPages  ;  //    "total_pages": 1,
        @JsonProperty("current_page")
        int currentPage;//            "current_page": 1,
        @JsonProperty("has_pre")
        boolean hasPre;//            "has_pre": false,
        @JsonProperty("has_next")
        boolean hasNext;//            "has_next": false,
        //            "category": null


        public PageSet(Page page) {
            this.totalPages = page.getTotalPages();
            this.currentPage = page.getNumber()+1;
            this.hasPre = page.hasPrevious();
            this.hasNext = page.hasNext();
        }
    }
}
