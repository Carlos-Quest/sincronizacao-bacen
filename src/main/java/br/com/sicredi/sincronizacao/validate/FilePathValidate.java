package br.com.sicredi.sincronizacao.validate;

import br.com.sicredi.sincronizacao.component.MessageComponent;
import br.com.sicredi.sincronizacao.enumeration.MessageKeyEnum;
import br.com.sicredi.sincronizacao.exception.EmptyParameterException;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class FilePathValidate {

  @Autowired
  private MessageComponent messageComponent;

  public void pathEmptyValidate(String filePath) {
    if (StringUtils.isEmpty(filePath)) {
      throw new EmptyParameterException(messageComponent.getMessage(MessageKeyEnum.EMPTY_FILE_PATH.getKey()));
    }
  }

}