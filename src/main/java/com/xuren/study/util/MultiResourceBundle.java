
package com.xuren.study.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class MultiResourceBundle extends ResourceBundle {


    private Properties properties;

    public MultiResourceBundle(String baseName) {
        setParent(ResourceBundle.getBundle(baseName, SpringContextUtil.getBean("multiResourceBundleControl",
                MultiResourceBundleControl.class)));
    }

    protected MultiResourceBundle(Properties properties) {
        this.properties = properties;
    }

    @Override
    protected Object handleGetObject(String key) {
        return properties != null ? properties.get(key) : parent.getObject(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration<String> getKeys() {
        return properties != null ? (Enumeration<String>) properties.propertyNames() : parent.getKeys();
    }



}
