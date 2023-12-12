package br.com.intersistemas.jasaas.util;

import java.lang.reflect.Field;

import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author bosco, fndcaique
 */
public class HttpParamsUtil {

    public static String parse(Object obj)
            throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
        if (obj == null)
            return null;

        URIBuilder uriBuilder = new URIBuilder();

        Class c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object valueObject = field.get(obj);
            if (valueObject != null) {
                String fieldName = field.getName();
                if (fieldName.endsWith("GE")) {
                    fieldName = fieldName.replaceAll("^(.*)(GE)$", "$1[ge]");
                } else if (fieldName.endsWith("LE")) {
                    fieldName = fieldName.replaceAll("^(.*)(LE)$", "$1[le]");
                }
                uriBuilder.addParameter(fieldName, valueObject.toString());
            }
        }

        return uriBuilder.toString();
    }
}
