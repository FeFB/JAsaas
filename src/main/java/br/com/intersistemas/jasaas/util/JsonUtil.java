package br.com.intersistemas.jasaas.util;

import java.text.DateFormat;
import java.time.LocalDate;

import com.google.gson.GsonBuilder;

/**
 * @author bosco
 */
public class JsonUtil {

	private static GsonBuilder gsonBuilder = null;

	public static Object parse(String json, Class clazz) {
		return parse(json, clazz, false);
	}

	public static Object parse(String json, Class clazz, Boolean showLog) {
		return parse(json, clazz, showLog, false);
	}

	public static Object parse(String json, Class clazz, Boolean showLog, Boolean verificaDataBR) {
		if (gsonBuilder == null) {
			gsonBuilder = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
					.setDateFormat(DateFormat.SHORT);
		}
		try {
			return gsonBuilder.create().fromJson(json, clazz);
		} catch (Exception e) {
			if (showLog) {
				System.out.println("Error parse gson.fromJson\n" + json);
				e.printStackTrace();
			}
			return null;
		}
	}

	public static String toJSON(Object obj) {
		if (gsonBuilder == null) {
			gsonBuilder = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
					.setDateFormat(DateFormat.SHORT);
		}
		return gsonBuilder.create().toJson(obj);
	}
}
