package br.com.intersistemas.jasaas.teste;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;

import br.com.intersistemas.jasaas.adapter.ApacheHttpClientAdapter;
import br.com.intersistemas.jasaas.api.Asaas;
import br.com.intersistemas.jasaas.api.CustomerConnection;
import br.com.intersistemas.jasaas.api.PaymentConnection;
import br.com.intersistemas.jasaas.api.SubscriptionConnection;
import br.com.intersistemas.jasaas.entity.Customer;
import br.com.intersistemas.jasaas.entity.Discount;
import br.com.intersistemas.jasaas.entity.Payment;
import br.com.intersistemas.jasaas.entity.PixQrCode;
import br.com.intersistemas.jasaas.entity.Subscription;
import br.com.intersistemas.jasaas.entity.filter.PaymentFilter;
import br.com.intersistemas.jasaas.entity.filter.SubscriptionFilter;
import br.com.intersistemas.jasaas.util.BillingType;
import br.com.intersistemas.jasaas.util.DiscountType;

/**
 * @author bosco
 */
public class Teste {

    public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException,
            ClassNotFoundException, KeyStoreException, NoSuchAlgorithmException, CertificateException {

        String acessToken = "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwMDIyNTc6OiRhYWNoXzEwYzk5NjJjLTcwMjMtNDM5My1iNjI1LTVlYTUxYzg0OGE1Mw==";
        Asaas asaas = new Asaas(new ApacheHttpClientAdapter(acessToken), Asaas.AMBIENTE_HOMOLOGACAO);
        PaymentConnection connPayment = asaas.payment();
        CustomerConnection connCustomer = asaas.customer();
        SubscriptionConnection connSubscription = asaas.subscription();

        Gson gson = new Gson();

        // tipos:
        // 0 getpayment,
        // 1 payment create,
        // 2 get customer,
        // 3 creat customer,
        // 4 create pix charge,
        // 5 create pix key
        // 6 get qrcode from payment
        // 7 list subscriptions
        // 8 list customers
        // 9 list payments
        int tipo = 9;

        switch (tipo) {
            case 0:
                try {
                    Payment pay = connPayment.getById("pay_2622034647487640");
                    System.out.println(pay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                Payment p = new Payment();

                p.setCustomer("cus_000005031717");
                p.setBillingType(BillingType.BOLETO);
                p.setValue(new BigDecimal("100.00"));
                Calendar calendar = Calendar.getInstance();
                // calendar.set(Calendar.HOUR_OF_DAY, 0);
                // calendar.set(Calendar.MINUTE, 0);
                // calendar.set(Calendar.SECOND, 0);
                // calendar.set(Calendar.MILLISECOND, 0);
                // calendar.set(Calendar.DAY_OF_MONTH, 04);
                p.setDueDate(calendar.getTime());
                p.setDescription("Teste boleto com desconto 20.0");
                p.setDiscount(new Discount(new BigDecimal("20"), 0, DiscountType.FIXED));
                p.setExternalReference("bol_2020");
                try {
                    Payment pCreated = connPayment.createPayment(p);
                    System.out.println(pCreated);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    Customer custo = connCustomer.getById("cus_000005031717");
                    System.out.println(custo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                Customer customer = new Customer();
                customer.setName("J Willian");
                customer.setCpfCnpj("99999999999");
                customer.setEmail("jwillian@liamg.moc.rb");
                Customer cCreated = connCustomer.createCustomer(customer);
                System.out.println("##################################");
                System.out.println(cCreated);
                System.out.println("##################################");
                break;
            case 4:
                // create pix charge
                Payment p2 = new Payment();
                p2.setCustomer("cus_000005031717");
                p2.setBillingType(BillingType.PIX);
                p2.setValue(new BigDecimal("100.00"));
                Calendar calendar2 = Calendar.getInstance();
                // calendar2.set(Calendar.HOUR_OF_DAY, 0);
                // calendar2.set(Calendar.MINUTE, 0);
                // calendar2.set(Calendar.SECOND, 0);
                // calendar2.set(Calendar.MILLISECOND, 0);
                // calendar2.set(Calendar.DAY_OF_MONTH, 04);
                p2.setDueDate(calendar2.getTime());
                p2.setDescription("Teste boleto com desconto 20.0");
                p2.setDiscount(new Discount(new BigDecimal("20"), 0, DiscountType.FIXED));
                p2.setExternalReference("bol_2020");
                try {
                    Payment pCreated = connPayment.createPayment(p2);
                    System.out.println(pCreated);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                // create pix key
                break;
            case 6:
                // get pix qrcode
                PixQrCode pqc = connPayment.getPixQrCodeByPaymentId("pay_9592606227139178");
                break;

            case 7:
                // list subscriptions
                SubscriptionFilter subscriptionFilter = new SubscriptionFilter();
                subscriptionFilter.setOrder("asc");
                subscriptionFilter.setSort("nextDueDate");
                subscriptionFilter.setStatus("ACTIVE");
                List<Subscription> listSubscriptions = connSubscription.getAll(null, 20, 0);
                System.out.println(gson.toJson(listSubscriptions));
                break;

            case 8:
                // list customers
                List<Customer> listCustomer = connCustomer.getAll(null, 20, 0);
                System.out.println(gson.toJson(listCustomer));
                break;

            case 9:
                // list payments

                PaymentFilter paymentFilter = new PaymentFilter();
                paymentFilter.setDueDateGE("2023-12-14");
                paymentFilter.setDueDateLE("2023-12-16");
                List<Payment> listPayment = connPayment.getAll(paymentFilter, 20, 0);
                System.out.println("connPayment.getOffset(): "+connPayment.getOffset());
                System.out.println("connPayment.getLimit(): "+connPayment.getLimit());
                System.out.println("connPayment.getHasMore(): "+connPayment.getHasMore());
                System.out.println("connPayment.getTotalCount(): "+connPayment.getTotalCount());
                System.out.println(gson.toJson(listPayment));
                break;
            default:
                break;
        }
    }
}
