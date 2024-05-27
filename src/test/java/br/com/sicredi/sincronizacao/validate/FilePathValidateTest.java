package br.com.sicredi.sincronizacao.validate;

import br.com.sicredi.sincronizacao.component.MessageComponent;
import br.com.sicredi.sincronizacao.configuration.MesssageConfiguration;
import br.com.sicredi.sincronizacao.exception.EmptyParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {MesssageConfiguration.class, MessageComponent.class, FilePathValidate.class})
public class FilePathValidateTest {

  @Autowired
  private FilePathValidate filePathValidate;

  private static final String CORRECT_FILE_PATH = "src/test/resources/TEST-DATA-SUCCESS.csv";

  private static final String INCORRECT_FILE_PATH = "";

  @Test
  public void testValidateCorrectFilePath() {
    Assertions.assertDoesNotThrow(() -> filePathValidate.pathEmptyValidate(CORRECT_FILE_PATH));
  }

  @Test
  public void testValidateIncorrectFilePath() {
    String errorMessage = "O caminho do arquivo está vazio ou não foi informado";
    Exception exception = Assertions.assertThrows(EmptyParameterException.class, () -> {
      filePathValidate.pathEmptyValidate(INCORRECT_FILE_PATH);
    });
    Assertions.assertEquals(errorMessage, exception.getMessage());
  }

}