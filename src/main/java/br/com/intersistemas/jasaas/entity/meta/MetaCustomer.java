package br.com.intersistemas.jasaas.entity.meta;

import java.util.List;

import com.google.gson.annotations.Expose;

import br.com.intersistemas.jasaas.entity.Customer;

/**
 *
 * @author bosco
 * @author fndcaique
 */
public class MetaCustomer {

    @Expose private Integer limit;
    @Expose private Integer offset;
    @Expose private Boolean hasMore;

    @Expose private List<Customer> data;

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

    public List<Customer> getData() {
        return data;
    }

    public void setData(List<Customer> data) {
        this.data = data;
    }

}
