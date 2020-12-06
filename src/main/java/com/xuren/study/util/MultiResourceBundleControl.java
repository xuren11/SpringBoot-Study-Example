/* 
 * All rights Reserved, Designed By 农金圈
 * 2017/12/21 15:37.
 */

package com.xuren.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author: Yu.ling
 */
@Component
public class MultiResourceBundleControl extends ResourceBundle.Control {

	private  final Logger log = LoggerFactory.getLogger(MultiResourceBundleControl.class);

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		Properties properties = load(baseName, loader);
		return new MultiResourceBundle(properties);
	}

	@Autowired
	private ResourceLoader resourceLoader;

	public Properties load(String baseName, ClassLoader loader) {
		log.debug("baseName:{}, class loader: {}", baseName, loader);
		if (baseName.indexOf(".") != -1) {
			baseName = "/" + baseName.replaceAll("\\.", "/");
			log.debug("baseName after:{}", baseName);
		}
		Properties properties = new Properties();
		String propertiesFileName = "classpath*:/" + baseName + "*.properties";
		try {
			Resource[] resourceArr = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(propertiesFileName);
			for (Resource res : resourceArr) {
				InputStream resourceAsStream = res.getInputStream();
				if (resourceAsStream == null) {
					log.error("can't find properties file {}", propertiesFileName);
					continue;
				}
				properties.load(new InputStreamReader(resourceAsStream, "utf8"));
				log.debug("loading message from :{}", res.getDescription());
			}
		} catch (IOException e) {
			log.error("Exception:", e);
		}
		return properties;
	}
}