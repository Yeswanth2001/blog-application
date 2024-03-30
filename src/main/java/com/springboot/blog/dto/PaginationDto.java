package com.springboot.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDto {

    private Integer pageNo;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalResult;
    private Boolean isLast;
    private List<?> listOfItems;
}
