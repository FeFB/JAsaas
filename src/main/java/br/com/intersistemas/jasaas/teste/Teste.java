package br.com.intersistemas.jasaas.teste;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.LocalDate;

import br.com.intersistemas.jasaas.adapter.ApacheHttpClientAdapter;
import br.com.intersistemas.jasaas.api.Asaas;
import br.com.intersistemas.jasaas.api.CustomerConnection;
import br.com.intersistemas.jasaas.api.PaymentConnection;
import br.com.intersistemas.jasaas.api.SubscriptionConnection;
import br.com.intersistemas.jasaas.api.TokenizeConnection;
import br.com.intersistemas.jasaas.entity.CreditCard;
import br.com.intersistemas.jasaas.entity.CreditCardHolderInfo;
import br.com.intersistemas.jasaas.entity.Customer;
import br.com.intersistemas.jasaas.entity.Discount;
import br.com.intersistemas.jasaas.entity.LinhaBoleto;
import br.com.intersistemas.jasaas.entity.Payment;
import br.com.intersistemas.jasaas.entity.QRCodePix;
import br.com.intersistemas.jasaas.entity.Subscription;
import br.com.intersistemas.jasaas.entity.Tokenize;
import br.com.intersistemas.jasaas.entity.TokenizeResponse;
import br.com.intersistemas.jasaas.util.BillingType;
import br.com.intersistemas.jasaas.util.Cycle;
import br.com.intersistemas.jasaas.util.DiscountType;

/**
 * @author bosco
 */
public class Teste {

    public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException,
            ClassNotFoundException, KeyStoreException, NoSuchAlgorithmException, CertificateException {

        String acessToken = "";
        Asaas asaas = new Asaas(new ApacheHttpClientAdapter(acessToken), Asaas.AMBIENTE_HOMOLOGACAO);
        PaymentConnection connPayment = asaas.payment();
        CustomerConnection connCustomer = asaas.customer();
        SubscriptionConnection connSubscription = asaas.subscription();

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

        try {
            Customer customer = connCustomer.getByExternalReference("550e8400e29b41d4a716446655440000");
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                p.setCustomer("cus_000005173845");
                p.setBillingType(BillingType.BOLETO);
                p.setValue(new BigDecimal("100.00"));
                p.setDueDate(LocalDate.now());
                p.setDescription("Teste boleto com desconto 20.0");
                p.setDiscount(new Discount(new BigDecimal("20"), 0, DiscountType.FIXED));
                p.setExternalReference("bol_2020");
                try {
                    Payment pCreated = connPayment.createPayment(p);

                    LinhaBoleto linhaBoleto = connPayment.getLinhaBoletoByIdBoleto(pCreated.getId());
                    QRCodePix qrCodePix = connPayment.getQRCodeByIdBoleto(pCreated.getId());

                    System.out.println(pCreated);
                    System.out.println(linhaBoleto);
                    System.out.println(qrCodePix);
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
                Tokenize tokenize = new Tokenize();
                tokenize.setCustomer("cus_000072931275");
                tokenize.setCreditCard(
                        new CreditCard(
                                "Joao B",
                                "151515415",
                                "52",
                                "58",
                                "888"));
                tokenize.setCreditCardHolderInfo(
                        new CreditCardHolderInfo(
                                "Joao Bosco",
                                "bos@gmail.com",
                                "01354555321",
                                "58585855",
                                "12515",
                                "4848484849"));
                tokenize.setRemoteIp("189.124.215.62");
                TokenizeConnection tokenizeConn = asaas.tokenize();
                TokenizeResponse response = tokenizeConn.createTokenize(tokenize);
                System.out.println(response);
                break;
            case 5:

                boolean retorno = connSubscription.deleteSubscription("huiasdhguijhuisa");
                System.out.println(retorno);

            case 6:
                Subscription subscription = new Subscription();
                subscription.setCustomer("cus_000072931275");
                subscription.setBillingType(BillingType.CREDIT_CARD);
                subscription.setValue(new BigDecimal(5));
                subscription.setNextDueDate(LocalDate.now().plusDays(20));
                subscription.setCycle(Cycle.MONTHLY);
                subscription.setCreditCard(
                        new CreditCard(
                                "Joao B",
                                "151515415",
                                "52",
                                "58",
                                "888"));
                subscription.setCreditCardHolderInfo(
                        new CreditCardHolderInfo(
                                "Joao Bosco",
                                "bos@gmail.com",
                                "01354555321",
                                "58585855",
                                "12515",
                                "4848484849"));
                subscription.setRemoteIp("4848484848");

                Subscription sCreated = connSubscription.createSubscription(subscription);
                System.out.println(sCreated);
                break;
            default:
                break;
        }

    }

}
