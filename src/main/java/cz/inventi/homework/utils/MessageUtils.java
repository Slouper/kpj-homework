package cz.inventi.homework.utils;

import org.apache.commons.lang3.StringUtils;

public class MessageUtils {

    public static String getServiceName(String serviceMessage) {
        return StringUtils.substringBefore(serviceMessage, ";");
    }

    public static String getServicePort(String serviceMessage) {
        return StringUtils.substringAfter(serviceMessage, ";");
    }
}
