package com.shopper.autos.system.domain.entity;

import java.util.List;

public class DomainPage<T> {

    private final List<T> content;
    private final Integer page;
    private final Integer size;
    private final Long totalResult;

    public DomainPage(List<T> content, Integer page, Integer size, Long totalResult) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalResult = totalResult;
    }

    public List<T> getContent() {
        return content;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public Long getTotalResult() {
        return totalResult;
    }


}