package br.com.intersistemas.jasaas.util;

/**
 * É possível utilizar webhook para que seu sistema seja notificado sobre
 * alterações que ocorram nas cobranças. Os eventos que o ASAAS notifica
 *
 * @author willian
 */
public enum PaymentEvent {
    PAYMENT_CREATED,// - Geração de nova cobrança.
    PAYMENT_AWAITING_RISK_ANALYSIS, // Pagamento em cartão aguardando aprovação pela análise manual de risco.
    PAYMENT_APPROVED_BY_RISK_ANALYSIS, // Pagamento em cartão aprovado pela análise manual de risco.
    PAYMENT_REPROVED_BY_RISK_ANALYSIS, // Pagamento em cartão reprovado pela análise manual de risco.
    PAYMENT_AUTHORIZED, // - Pagamento em cartão que foi autorizado e precisa ser capturado.
    PAYMENT_UPDATED,// - Alteração no vencimento ou valor de cobrança existente.
    PAYMENT_CONFIRMED,// - Cobrança confirmada (pagamento efetuado, porém o saldo ainda não foi disponibilizado).
    PAYMENT_RECEIVED, // - Cobrança recebida.
    PAYMENT_CREDIT_CARD_CAPTURE_REFUSED, // - Falha no pagamento de cartão de crédito
    PAYMENT_ANTICIPATED,// - Cobrança antecipada.
    PAYMENT_OVERDUE,// - Cobrança vencida.
    PAYMENT_DELETED,// - Cobrança removida.
    PAYMENT_RESTORED,// - Cobrança restaurada.
    PAYMENT_REFUNDED,// - Cobrança estornada.
    PAYMENT_REFUND_IN_PROGRESS, //Estorno em processamento (liquidação já está agendada, cobrança será estornada após executar a liquidação).
    PAYMENT_RECEIVED_IN_CASH_UNDONE,// - Recebimento em dinheiro desfeito.
    PAYMENT_CHARGEBACK_REQUESTED,// - Recebido chargeback.
    PAYMENT_CHARGEBACK_DISPUTE,// - Em disputa de chargeback (caso sejam apresentados documentos para contestação).
    PAYMENT_AWAITING_CHARGEBACK_REVERSAL,// - Disputa vencida, aguardando repasse da adquirente.
    PAYMENT_DUNNING_RECEIVED, // - Recebimento de negativação.
    PAYMENT_DUNNING_REQUESTED,// - Requisição de negativação.
    PAYMENT_BANK_SLIP_VIEWED,// - Boleto da cobrança visualizado pelo cliente.
    PAYMENT_CHECKOUT_VIEWED, // - Fatura da cobrança visualizada pelo cliente.
}
