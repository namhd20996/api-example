package com.example.assign.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class BaseResponse<T> {

    private Integer page;
    private Integer limit;
    private Integer totalPage;
    private Integer totalItem;
    private List<T> listResult;
}
