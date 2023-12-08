package br.com.intersistemas.jasaas.entity;

import java.time.LocalDateTime;

import com.google.gson.annotations.Expose;

import br.com.intersistemas.jasaas.util.RefundStatus;

/**
 *
 * Informações de extorno realizado
 *
 * @author gabriel caetano
 */
public class Refund {

    @Expose
    private LocalDateTime dateCreated;
    @Expose
    private RefundStatus status;
    @Expose
    private String value;
    @Expose
    private String description;
    @Expose
    private String transactionReceiptUrl;

    public Refund() {
    }
    
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public RefundStatus getStatus() {
        return status;
    }

    public void setStatus(RefundStatus status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionReceiptUrl() {
        return transactionReceiptUrl;
    }

    public void setTransactionReceiptUrl(String transactionReceiptUrl) {
        this.transactionReceiptUrl = transactionReceiptUrl;
    }

    @Override
    public String toString() {
        return "Refund{" + "dateCreated=" + dateCreated + ", status=" + status + ", value=" + value + ", description=" + description + ", transactionReceiptUrl=" + transactionReceiptUrl + '}';
    }
}
