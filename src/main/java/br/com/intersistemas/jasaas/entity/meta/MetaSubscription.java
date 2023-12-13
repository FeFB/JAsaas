package br.com.intersistemas.jasaas.entity.meta;

import java.util.List;

import com.google.gson.annotations.Expose;

import br.com.intersistemas.jasaas.entity.Subscription;

/**
 *
 * @author bosco
 * @author fndcaique
 */
public class MetaSubscription {

    @Expose private Integer limit;
    @Expose private Integer offset;
    @Expose private Integer totalCount;
    @Expose private Boolean hasMore;

    @Expose private List<Subscription> data;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<Subscription> getData() {
        return data;
    }

    public void setData(List<Subscription> data) {
        this.data = data;
    }
    
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
