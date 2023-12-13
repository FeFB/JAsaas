package br.com.intersistemas.jasaas.entity;

import com.google.gson.annotations.Expose;

import br.com.intersistemas.jasaas.util.ChargeBackReason;
import br.com.intersistemas.jasaas.util.ChargeBackStatus;

/**
 * Informações de chargeback caso possuir
 *
 * @author willian
 */
public class ChargeBack {

    @Expose(serialize = false)
    ChargeBackStatus status;
    @Expose(serialize = false)
    ChargeBackReason reason;
}
