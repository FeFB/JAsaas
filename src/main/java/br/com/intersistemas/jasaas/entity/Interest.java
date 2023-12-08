package br.com.intersistemas.jasaas.entity;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

import br.com.intersistemas.jasaas.util.TypeValue;

/**
 * Informações de juros para pagamento após o vencimento
 *
 * @author willian
 */
public class Interest {

    @Expose
    private BigDecimal value;
    @Expose(serialize = false)
    private TypeValue type;

    public Interest() {
    }

    public Interest(BigDecimal value) {
        this.value = value;
    }

    public Interest(BigDecimal value, TypeValue type) {
        this.value = value;
        this.type = type;
    }
    /**
     *
     * @return value Percentual de juros ao mês sobre o valor da cobrança para
     * pagamento após o vencimento
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     *
     * @param value Percentual de juros ao mês sobre o valor da cobrança para
     * pagamento após o vencimento
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
        return "Interest{" + "value=" + value + ", type=" + type + '}';
    }

}
