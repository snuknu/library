package br.com.lawromm.library.config;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public final class MessageSourceConfiguration {
  private static MessageSourceConfiguration instance;
  private ReloadableResourceBundleMessageSource messageSource;

  private MessageSourceConfiguration() {
    this.messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
  }

  public static MessageSourceConfiguration getInstance() {
    if (instance == null) {
      instance = new MessageSourceConfiguration();
    }
    return instance;
  }

  public MessageSource getMessageSource() {
    return messageSource;
  }
}
