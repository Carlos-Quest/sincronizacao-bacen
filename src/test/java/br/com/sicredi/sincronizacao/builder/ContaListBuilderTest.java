package br.com.sicredi.sincronizacao.builder;

import br.com.sicredi.sincronizacao.component.MessageComponent;
import br.com.sicredi.sincronizacao.configuration.MesssageConfiguration;
import br.com.sicredi.sincronizacao.dto.ContaDTO;
import br.com.sicredi.sincronizacao.exception.CSVFileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(
        classes = {MesssageConfiguration.class, MessageComponent.class, ContaListBuilder.class}
)
public class ContaListBuilderTest {

  @Autowired
  private ContaListBuilder contaListBuilder;

  private static final String SUCCESS_FILE_PATH = "src/test/resources/TEST-DATA-SUCCESS.csv";

  private static final String ERROR_FILE_PATH = "src/test/resources/TEST-SUCCESS.csv";

  @Test
  public void testBuildSuccess() {
    List<ContaDTO> contaDTOList = contaListBuilder.build(SUCCESS_FILE_PATH);
    Assertions.assertNotNull(contaDTOList);
    Assertions.assertEquals(5, contaDTOList.size());
  }

  @Test
  public void testBuildFileNotFoundError() {
    String errorMessage = "Arquivo não encontrado";
    Exception exception = Assertions.assertThrows(CSVFileNotFoundException.class, () -> {
      contaListBuilder.build(ERROR_FILE_PATH);
    });
    Assertions.assertEquals(errorMessage, exception.getMessage());
  }

}