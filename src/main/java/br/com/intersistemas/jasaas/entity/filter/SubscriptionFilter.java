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
     * @param customer String com o identificado único do cliente
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
     * @param customerGroupName String com o nome do grupo
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
     * @param billintType String com a forma de pagamento
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
     * @param status String com o Status da Subscription
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
     * @param externalReference o identificado do seu sistema
     */
    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getOrder() {
        return order;
    }

    /**
     * @param order Deve ser "asc" para ordem crescente ou "desc" para ordem decrescente
     */
    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    /**
     * 
     * @param sort Deve ser o nome de algum atributo do objeto Subscription por qual o resultado será ordenado
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getDeletedOnly() {
        return deletedOnly;
    }

    /**
     * 
     * @param deletedOnly Envie true para retornar somente as assinaturas removidas
     */
    public void setDeletedOnly(Boolean deletedOnly) {
        this.deletedOnly = deletedOnly;
    }

    public Boolean getIncludeDeleted() {
        return includeDeleted;
    }

    /**
     * 
     * @param includeDeleted Envie true para recuperar também as assinaturas removidas
     */
    public void setIncludeDeleted(Boolean includeDeleted) {
        this.includeDeleted = includeDeleted;
    }

}
