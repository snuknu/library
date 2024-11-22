package br.com.lawromm.library.enumeration;

import br.com.lawromm.library.config.MessageSourceConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public enum WorkType {
  BOOK("enum.typeWork.book"),
  ARTICLE("enum.typeWork.article"),
  WORK("enum.typeWork.conferenceWork"),
  DISSERTATION("enum.typeWork.dissertation"),
  THESIS("enum.typeWork.thesis"),
  OTHERS("enum.typeWork.others");

  private String descriptionCode;

  WorkType(String descriptionCode) {
    this.descriptionCode = descriptionCode;
  }

  public String getDescription() {
    MessageSource messageSource = MessageSourceConfiguration
      .getInstance()
      .getMessageSource();

    return messageSource.getMessage(
      this.descriptionCode,
      null,
      LocaleContextHolder.getLocale()
    );
  }
}
