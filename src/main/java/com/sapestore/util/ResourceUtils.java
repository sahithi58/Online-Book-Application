package com.sapestore.util;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * utility class to read properties file
 * Properties file contains key value pairs.
 * 
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public final class ResourceUtils {


	private static final String SUFFIX = ".properties";

	private ResourceUtils() {
	}

	public static Properties getProperties(String s) {
		String fileName = s.endsWith(SUFFIX) ? s : s.concat(SUFFIX);
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = ResourceUtils.class.getClassLoader().getResourceAsStream(fileName);
			if (in == null) {
				throw new RuntimeException("couldn't find properties file '" + fileName + "'");
			}
			props.load(in);
		} catch (IOException e) {
			String msg = "couldn't load properties file '" + fileName + "'";
			//log.error(msg, e);
			throw new RuntimeException(msg +" "+e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// nothing to do
				}
			}
		}
		return props;
	}

}

