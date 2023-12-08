package br.com.intersistemas.jasaas.api;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.intersistemas.jasaas.adapter.AdapterConnection;
import br.com.intersistemas.jasaas.entity.Subscription;
import br.com.intersistemas.jasaas.entity.filter.SubscriptionFilter;
import br.com.intersistemas.jasaas.entity.meta.DeletedEntityReturn;
import br.com.intersistemas.jasaas.entity.meta.MetaSubscription;
import br.com.intersistemas.jasaas.exception.ConnectionException;
import br.com.intersistemas.jasaas.util.HttpParamsUtil;
import br.com.intersistemas.jasaas.util.JsonUtil;

/**
 *
 * @author bosco
 * @author fndcaique
 */
public class SubscriptionConnection extends AbstractConnection {

    private final AdapterConnection adapter;

    public SubscriptionConnection(AdapterConnection adapter, int abstractConnectionEndpoint) {
        super(abstractConnectionEndpoint);
        this.adapter = adapter;
    }

    public List<Subscription> getAll() throws ConnectionException {
        return getAll(null, null, null);
    }

    public List<Subscription> getAll(SubscriptionFilter subscriptionFilter) throws ConnectionException {
        return getAll(subscriptionFilter, null, null);
    }

    public List<Subscription> getAll(SubscriptionFilter subscriptionFilter, Integer limit, Integer offset) throws ConnectionException {
        try {
            String url;

            if (limit == null) {
                limit = 10;
            }
            if (offset == null) {
                offset = 0;
            }

            String params = HttpParamsUtil.parse(subscriptionFilter);
            if (params != null) {
                url = (endpoint + "/subscriptions" + params + "&limit=" + limit + "&offset=" + offset);
            } else {
                url = (endpoint + "/subscriptions" + "?limit=" + limit + "&offset=" + offset);
            }

            lastResponseJson = adapter.get(url);

            MetaSubscription meta = (MetaSubscription) JsonUtil.parse(lastResponseJson, MetaSubscription.class);

            setHasMore(meta.getHasMore());
            setLimit(meta.getLimit());
            setOffset(meta.getOffset());

            return meta.getData();
        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SubscriptionConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Subscription getById(String id) throws ConnectionException {
        lastResponseJson = adapter.get((endpoint + "/subscriptions/" + id));
        return (Subscription) JsonUtil.parse(lastResponseJson, Subscription.class);
    }

    public List<Subscription> getByCustomer(String customer_id) throws ConnectionException {
        lastResponseJson = adapter.get((endpoint + "/customers/" + customer_id + "/subscriptions"));
        MetaSubscription meta = (MetaSubscription) JsonUtil.parse(lastResponseJson, MetaSubscription.class);

        setHasMore(meta.getHasMore());
        setLimit(meta.getLimit());
        setOffset(meta.getOffset());

        return meta.getData();
    }

    public Subscription createSubscription(Subscription subscription) throws ConnectionException {
        String subscriptionJSON = JsonUtil.toJSON(subscription);
        if (subscription.getId() == null) {
            try {
                System.out.println("createSubscription");
                String data = adapter.post((endpoint + "/subscriptions/"), subscriptionJSON);
                Subscription subscriptionsCreated = (Subscription) JsonUtil.parse(data, Subscription.class);
                return subscriptionsCreated;
            } catch (Exception ex) {
                Logger.getLogger(SubscriptionConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw new ConnectionException(500, ex.getMessage());
            }
        } else {
            throw new ConnectionException(500, "You should not enter the id in the entity to create it.");
        }
    }

    public Subscription updateSubscription(Subscription subscription) throws ConnectionException {
        try {
            System.out.println("updateSubscription");
            String subscriptionJSON = JsonUtil.toJSON(subscription);
            String data = adapter.post((endpoint + "/subscriptions/" + subscription.getId()), subscriptionJSON);
            Subscription subscriptionUpdated = (Subscription) JsonUtil.parse(data, Subscription.class);
            return subscriptionUpdated;
        } catch (Exception ex) {
            Logger.getLogger(SubscriptionConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConnectionException(500, ex.getMessage());
        }
    }

    public boolean deleteSubscription(String id) throws ConnectionException {
        try {
            System.out.println("deleteSubscriptions");
            String data = adapter.delete((endpoint + "/subscriptions/" + id));
            DeletedEntityReturn deleted = (DeletedEntityReturn) JsonUtil.parse(data, DeletedEntityReturn.class);
            return deleted.getDeleted();
        } catch (Exception ex) {
            Logger.getLogger(SubscriptionConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConnectionException(500, ex.getMessage());
        }
    }

}
