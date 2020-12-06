
package com.xuren.study.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 消息配置文件
 * 
 * @author HuangXin
 */
public class Messages {

    private static final String BUNDLE_NAME = "message";

    private static final ResourceBundle RESOURCE_BUNDLE = new MultiResourceBundle(BUNDLE_NAME);

    private Messages() {}

    public static String get(String key) {
        return get(key, "");
    }

    public static String get(String key, String... args) {

        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), (Object[]) args);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public static MessageCode getMessageCode(String key) {
        return getMessageCode(key, "");
    }

    public static MessageCode getMessageCode(String key, String... args) {
        MessageCode messageCode = new MessageCode();
        try {
            messageCode.setCode(RESOURCE_BUNDLE.getString(key + ".code"));
        } catch (MissingResourceException e) {
            messageCode.setCode(RESOURCE_BUNDLE.getString("common.code"));
        }
        try {
            messageCode.setMsg(MessageFormat.format(RESOURCE_BUNDLE.getString(key), (Object[]) args));
        } catch (MissingResourceException e) {
            messageCode.setCode("NO_KEY_FOUND");
            messageCode.setMsg("在" + BUNDLE_NAME + ".properties中找不到key为：" + key + "的消息");
        }
        return messageCode;
    }
}
