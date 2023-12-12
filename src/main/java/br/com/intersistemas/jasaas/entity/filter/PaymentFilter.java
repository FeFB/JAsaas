package br.com.intersistemas.jasaas.entity.filter;

/**
 *
 * @author bosco, fndcaique
 *
 */
public class PaymentFilter {

    private String customer, customerGroupName, billingType, status, subscription, installment, externalReference, paymentDate, invoiceStatus, estimatedCreditDate, pixQrCodeId, anticipated, dateCreatedGE, dateCreatedLE, paymentDateGE, paymentDateLE, estimatedCreditDateGE, estimatedCreditDateLE, dueDateGE, dueDateLE, user;

    public String getCustomer() {
        return customer;
    }

    /**
     * Filtrar pelo Identificador único do cliente
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
     * @param customerGroupName 
     */
    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getBillingType() {
        return billingType;
    }

    /**
     * Filtrar por forma de pagamento
     * @param billingType 
     */
    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Filtrar por status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubscription() {
        return subscription;
    }

    /**
     * Filtrar pelo Identificador único da assinatura
     * @param subscription
     */
    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getInstallment() {
        return installment;
    }

    /**
     * Filtrar pelo Identificador único do parcelamento
     * @param installment
     */
    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getExternalReference() {
        return externalReference;
    }

    /**
     * Filtrar pelo Identificador do seu sistema
     * @param externalReference
     */
    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * Filtrar pela data de pagamento
     * @param paymentDate on format YYYY-MM-DD
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * Filtro para retornar cobranças que possuem ou não nota fiscal.
     * @param invoiceStatus
     */
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getEstimatedCreditDate() {
        return estimatedCreditDate;
    }

    /**
     * Filtrar pela data estimada de crédito.
     * @param estimatedCreditDate on format YYYY-MM-DD
     */
    public void setEstimatedCreditDate(String estimatedCreditDate) {
        this.estimatedCreditDate = estimatedCreditDate;
    }

    public String getPixQrCodeId() {
        return pixQrCodeId;
    }

    /**
     * Filtrar recebimentos originados de um QrCode estático utilizando o id gerado na hora da criação do QrCode.
     * @param pixQrCodeId
     */
    public void setPixQrCodeId(String pixQrCodeId) {
        this.pixQrCodeId = pixQrCodeId;
    }

    public String getAnticipated() {
        return anticipated;
    }

    /**
     * Filtrar registros antecipados ou não
     * @param anticipated
     */
    public void setAnticipated(String anticipated) {
        this.anticipated = anticipated;
    }

    public String getDateCreatedGE() {
        return dateCreatedGE;
    }

    /**
     * Filtrar a partir da data de criação inicial
     * @param dateCreatedGE on format YYYY-MM-DD
     */
    public void setDateCreatedGE(String dateCreatedGE) {
        this.dateCreatedGE = dateCreatedGE;
    }

    public String getDateCreatedLE() {
        return dateCreatedLE;
    }

    /**
     * Filtrar a partir da data de criação final
     * @param dateCreatedLE on format YYYY-MM-DD
     */
    public void setDateCreatedLE(String dateCreatedLE) {
        this.dateCreatedLE = dateCreatedLE;
    }

    public String getPaymentDateGE() {
        return paymentDateGE;
    }

    /**
     * Filtrar a partir da data de recebimento inicial
     * @param paymentDateGE on format YYYY-MM-DD
     */
    public void setPaymentDateGE(String paymentDateGE) {
        this.paymentDateGE = paymentDateGE;
    }

    public String getPaymentDateLE() {
        return paymentDateLE;
    }

    /**
     * Filtrar a partir da data de recebimento final
     * @param paymentDateLE on format YYYY-MM-DD
     */
    public void setPaymentDateLE(String paymentDateLE) {
        this.paymentDateLE = paymentDateLE;
    }

    public String getEstimatedCreditDateGE() {
        return estimatedCreditDateGE;
    }

    /**
     * Filtrar a partir da data de recebimento final
     * @param estimatedCreditDateGE on format YYYY-MM-DD
     */
    public void setEstimatedCreditDateGE(String estimatedCreditDateGE) {
        this.estimatedCreditDateGE = estimatedCreditDateGE;
    }

    public String getEstimatedCreditDateLE() {
        return estimatedCreditDateLE;
    }

    /**
     * Filtrar a partir da data estimada de crédito final
     * @param estimatedCreditDateLE on format YYYY-MM-DD
     */
    public void setEstimatedCreditDateLE(String estimatedCreditDateLE) {
        this.estimatedCreditDateLE = estimatedCreditDateLE;
    }

    public String getDueDateGE() {
        return dueDateGE;
    }

    /**
     * Filtrar a partir da data de vencimento inicial
     * @param dueDateGE on format YYYY-MM-DD
     */
    public void setDueDateGE(String dueDateGE) {
        this.dueDateGE = dueDateGE;
    }

    public String getDueDateLE() {
        return dueDateLE;
    }

    /**
     * Filtrar a partir da data de vencimento final
     * @param dueDateLE on format YYYY-MM-DD
     */
    public void setDueDateLE(String dueDateLE) {
        this.dueDateLE = dueDateLE;
    }

    public String getUser() {
        return user;
    }

    /**
     * Filtrar pelo endereço de e-mail do usuário que criou a cobrança.
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    public PaymentFilter() {
    }

    
}
