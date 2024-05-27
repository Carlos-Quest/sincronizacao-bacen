package br.com.sicredi.sincronizacao.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

  private static final String FILE_PATH = "src/test/resources/TEST-DATA-SUCCESS.csv";

  private static final String NEW_FILE_NAME = "DATA-RESULT.csv";

  @Test
  public void testCreateNewFilePathText() {
    String newFilePathText = "src/test/resources/DATA-RESULT.csv";
    String filePath = StringUtils.getResultFilePath(FILE_PATH, NEW_FILE_NAME);
    Assertions.assertEquals(newFilePathText, filePath);
  }

}