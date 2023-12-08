package br.com.intersistemas.jasaas.entity;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

import br.com.intersistemas.jasaas.util.TypeValue;

/**
 * Informações de multa para pagamento após o vencimento
 *
 * @author willian
 */
public class Fine {

    @Expose
    private BigDecimal value;
    @Expose(serialize = false)
    private TypeValue type;

    public Fine() {
    }

    public Fine(BigDecimal value) {
        this.value = value;
    }

    public Fine(BigDecimal value, TypeValue type) {
        this.value = value;
        this.type = type;
    }

    /**
     *
     * @return value Percentual de multa sobre o valor da cobrança para
     * pagamento após o vencimento
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     *
     * @param value Percentual de multa sobre o valor da cobrança para pagamento
     * após o vencimento
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TypeValue getType() {
        return type;
    }

    public void setType(TypeValue type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Fine{" + "value=" + value + ", type=" + type + '}';
    }

}
