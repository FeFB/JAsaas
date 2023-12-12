package br.com.intersistemas.jasaas.entity.filter;

/**
 *
 * @author bosco, fndcaique
 */
public class SubscriptionFilter {

    private String customer, customerGroupName, billintType, status, externalReference, order, sort;
    private Boolean deletedOnly, includeDeleted;

    public SubscriptionFilter() {
    }

    public String getCustomer() {
        return customer;
    }

    /**
     * Filtrar pelo Identificador único do cliente
     * 
     * @param customer
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    /**
     * Filtrar pelo nome do grupo de cliente
     * 
     * @param customerGroupName
     */
    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getBillintType() {
        return billintType;
    }

    /**
     * Filtrar por forma de pagamento
     * 
     * @param billintType
     */
    public void setBillintType(String billintType) {
        this.billintType = billintType;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Filtrar pelo status
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getExternalReference() {
        return externalReference;
    }

    /**
     * Filtrar pelo Identificador do seu sistema
     * 
     * @param externalReference
     */
    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getOrder() {
        return order;
    }

    /**
     * Ordem crescente ou decrescente
     * 
     * @param order
     */
    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    /**
     * Por qual campo será ordenado
     * 
     * @param sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getDeletedOnly() {
        return deletedOnly;
    }

    /**
     * Envie true para retornar somente as assinaturas removidas
     * 
     * @param deletedOnly
     */
    public void setDeletedOnly(Boolean deletedOnly) {
        this.deletedOnly = deletedOnly;
    }

    public Boolean getIncludeDeleted() {
        return includeDeleted;
    }

    /**
     * Envie true para recuperar também as assinaturas removidas
     * 
     * @param includeDeleted
     */
    public void setIncludeDeleted(Boolean includeDeleted) {
        this.includeDeleted = includeDeleted;
    }

}
