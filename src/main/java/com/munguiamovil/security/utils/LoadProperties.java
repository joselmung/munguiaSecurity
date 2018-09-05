package com.munguiamovil.security.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;



/*
 * Clase de  lectura de archivo application.properties"
 * version 1.0
 */

public class LoadProperties {



	public static synchronized String getProperty(String fileProperties, String key) {
		
		String value = null;

		try {
			String catalina_home = System.getenv("CATALINA_HOME") + File.separator + "conf" + File.separator
					+ "munguia-movil" + File.separator;
			File file = new File(catalina_home);
			URL[] urls = new URL[] { file.toURI().toURL() };
			ClassLoader loader = new URLClassLoader(urls);

			ResourceBundle rb = ResourceBundle.getBundle(fileProperties, Locale.getDefault(), loader);
			value = rb.getString(key);

		} catch (IOException e) {
			
			e.printStackTrace();
		}

		
		return value;
	}

	public static synchronized String getRuta() {

		String catalina_home = System.getenv("CATALINA_HOME") + File.separator + "conf" + File.separator
				+ "munguia-movil" + File.separator;

		
		return catalina_home;
	}
}
