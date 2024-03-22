package com.shopper.autos.system.domain.entity;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainPage<?> that = (DomainPage<?>) o;
        return Objects.equals(content, that.content) && Objects.equals(page, that.page) && Objects.equals(size, that.size) && Objects.equals(totalResult, that.totalResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, page, size, totalResult);
    }
}