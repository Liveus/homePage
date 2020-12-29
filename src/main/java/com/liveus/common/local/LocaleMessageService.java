package com.liveus.common.local;

import java.util.Locale;

/**
 * @Package: com.liveus.common.local
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/3 13:58
 */
public interface LocaleMessageService {
    /**
     * Gets message.
     *
     * @param code the code
     * @return the message
     */
    String getMessage(String code);

    /**
     * Gets message.
     *
     * @param code           the code
     * @param args           the args
     * @param defaultMessage the default message
     * @param locale         the locale
     * @return the message
     */
    String getMessage(String code, Object[] args, String defaultMessage, Locale locale);

}
