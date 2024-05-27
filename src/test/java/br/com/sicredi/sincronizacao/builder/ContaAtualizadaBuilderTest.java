package br.com.sicredi.sincronizacao.builder;

import br.com.sicredi.sincronizacao.component.MessageComponent;
import br.com.sicredi.sincronizacao.configuration.MesssageConfiguration;
import br.com.sicredi.sincronizacao.dto.ContaAtualizadaDTO;
import br.com.sicredi.sincronizacao.dto.ContaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(
        classes = {MesssageConfiguration.class, MessageComponent.class, ContaAtualizadaBuilder.class}
)
public class ContaAtualizadaBuilderTest {

  @Autowired
  private ContaAtualizadaBuilder contaAtualizadaBuilder;

  @Test
  public void testBuildUpdatedAccount() {
    Boolean status = true;
    String statusConta = "Atualizada";
    ContaDTO contaDTO = createContaDTO("54587", "58741-2", 200.32);
    ContaAtualizadaDTO contaAtualizadaDTO = contaAtualizadaBuilder.build(status, contaDTO);
    Assertions.assertNotNull(contaAtualizadaDTO);
    Assertions.assertEquals(statusConta, contaAtualizadaDTO.getStatus());
  }

  @Test
  public void testBuildAccountNotUpdated() {
    Boolean status = false;
    String statusConta = "Não atualizada";
    ContaDTO contaDTO = createContaDTO("54587", "58741-2", 200.32);
    ContaAtualizadaDTO contaAtualizadaDTO = contaAtualizadaBuilder.build(status, contaDTO);
    Assertions.assertNotNull(contaAtualizadaDTO);
    Assertions.assertEquals(statusConta, contaAtualizadaDTO.getStatus());
  }

  private ContaDTO createContaDTO(
          String agency,
          String account,
          Double balance) {
    ContaDTO contaDTO = new ContaDTO();
    contaDTO.setConta(agency);
    contaDTO.setConta(account);
    contaDTO.setSaldo(balance);
    return contaDTO;
  }

}