package br.com.intersistemas.jasaas.api;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.intersistemas.jasaas.adapter.AdapterConnection;
import br.com.intersistemas.jasaas.entity.Payment;
import br.com.intersistemas.jasaas.entity.PixQrCode;
import br.com.intersistemas.jasaas.entity.filter.PaymentFilter;
import br.com.intersistemas.jasaas.entity.meta.DeletedEntityReturn;
import br.com.intersistemas.jasaas.entity.meta.MetaError;
import br.com.intersistemas.jasaas.entity.meta.MetaPayment;
import br.com.intersistemas.jasaas.exception.ConnectionException;
import br.com.intersistemas.jasaas.util.HttpParamsUtil;
import br.com.intersistemas.jasaas.util.JsonUtil;

/**
 *
 * @author bosco
 * @author fndcaique
 */
public class PaymentConnection extends AbstractConnection {

    private final AdapterConnection adapter;

    public PaymentConnection(AdapterConnection adapter, int abstractConnectionEndpoint) {
        super(abstractConnectionEndpoint);
        this.adapter = adapter;
    }

    public List<Payment> getAll() throws ConnectionException {
        return getAll(null, null, null);
    }

    public List<Payment> getAll(PaymentFilter paymentFilter) throws ConnectionException {
        return getAll(paymentFilter, null, null);
    }

    public List<Payment> getAll(PaymentFilter paymentFilter, Integer limit, Integer offset) throws ConnectionException {
        try {
            String url;

            if (limit == null) {
                limit = 10;
            }
            if (offset == null) {
                offset = 0;
            }

            String params = HttpParamsUtil.parse(paymentFilter);
            if (params != null) {
                url = (endpoint + "/payments" + params + "&limit=" + limit + "&offset=" + offset);
            } else {
                url = (endpoint + "/payments" + "?limit=" + limit + "&offset=" + offset);
            }

            lastResponseJson = adapter.get(url);

            MetaPayment meta = (MetaPayment) JsonUtil.parse(lastResponseJson, MetaPayment.class);

            setHasMore(meta.getHasMore());
            setLimit(meta.getLimit());
            setOffset(meta.getOffset());

            return meta.getData();

        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(PaymentConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConnectionException(500, ex.getMessage());
        }
        //return null;
    }

    public Payment getById(String id) throws ConnectionException {
        lastResponseJson = adapter.get(endpoint + "/payments/" + id);
        return (Payment) JsonUtil.parse(lastResponseJson, Payment.class);
    }

    public List<Payment> getByCustomer(String customer_id) throws ConnectionException {
        lastResponseJson = adapter.get(endpoint + "/customers/" + customer_id + "/payments");

        MetaPayment meta = (MetaPayment) JsonUtil.parse(lastResponseJson, MetaPayment.class);

        setHasMore(meta.getHasMore());
        setLimit(meta.getLimit());
        setOffset(meta.getOffset());
        return meta.getData();
    }

    public List<Payment> getByExternalReference(String externalReference) throws ConnectionException {
        String url = (endpoint + "/payments?externalReference=" + externalReference);
        lastResponseJson = adapter.get(url);
        MetaPayment meta = (MetaPayment) JsonUtil.parse(lastResponseJson, MetaPayment.class);
        return meta.getData();
    }

    public List<Payment> getBySubscriptions(String subscription_id) throws ConnectionException {
        lastResponseJson = adapter.get(endpoint + "/subscriptions/" + subscription_id + "/payments");
        MetaPayment meta = (MetaPayment) JsonUtil.parse(lastResponseJson, MetaPayment.class);

        setHasMore(meta.getHasMore());
        setLimit(meta.getLimit());
        setOffset(meta.getOffset());

        return meta.getData();
    }

    public List<Payment> getByInstallment(String installment) throws ConnectionException {
        lastResponseJson = adapter.get(endpoint + "/payments?installment=[" + installment + "]");
        MetaPayment meta = (MetaPayment) JsonUtil.parse(lastResponseJson, MetaPayment.class);

        setHasMore(meta.getHasMore());
        setLimit(meta.getLimit());
        setOffset(meta.getOffset());
        return meta.getData();
    }

    public Payment createPayment(Payment payment) throws ConnectionException {
        String paymentJSON = JsonUtil.toJSON(payment);
        if (payment.getId() == null) {
            try {
                System.out.println("createPayment");
                payment.validate();
                String data = adapter.post((endpoint + "/payments/"), paymentJSON);
                //System.out.println(data);
                Payment paymentCreated = (Payment) JsonUtil.parse(data, Payment.class);
                if (paymentCreated.getId() == null) {
                    MetaError error = (MetaError) JsonUtil.parse(data, MetaError.class);
                    //System.out.println(error);
                    throw new ConnectionException(500, error.toString());
                }
                return paymentCreated;
            } catch (Exception ex) {
                Logger.getLogger(PaymentConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw new ConnectionException(500, ex.getMessage());
            }
        } else {
            throw new ConnectionException(500, "You should not enter the id in the entity to create it.");
        }
    }

    public Payment updatePayment(Payment payment) throws ConnectionException {
        try {
            System.out.println("updatePayment");
            String paymentJSON = JsonUtil.toJSON(payment);
            payment.validate();
            String data = adapter.post((endpoint + "/payments/" + payment.getId()), paymentJSON);
            Payment paymentUpdated = (Payment) JsonUtil.parse(data, Payment.class);
            if (paymentUpdated.getId() == null) {
                MetaError error = (MetaError) JsonUtil.parse(data, MetaError.class);
                //System.out.println(error);
                throw new ConnectionException(500, error.toString());
            }
            return paymentUpdated;
        } catch (Exception ex) {
            Logger.getLogger(PaymentConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConnectionException(500, ex.getMessage());
        }
    }

    public boolean deletePayment(String id) throws ConnectionException {
        try {
            System.out.println("deletePayment");
            String data = adapter.delete((endpoint + "/payments/" + id));
            DeletedEntityReturn deleted = (DeletedEntityReturn) JsonUtil.parse(data, DeletedEntityReturn.class);
            return deleted.getDeleted();
        } catch (Exception ex) {
            Logger.getLogger(PaymentConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConnectionException(500, ex.getMessage());
        }
    }

    public PixQrCode getPixQrCodeByPaymentId(String paymentId) throws ConnectionException {
        lastResponseJson = adapter.get(endpoint + "/payments/" + paymentId + "/pixQrCode");
        return (PixQrCode) JsonUtil.parse(lastResponseJson, PixQrCode.class);
    }
}
