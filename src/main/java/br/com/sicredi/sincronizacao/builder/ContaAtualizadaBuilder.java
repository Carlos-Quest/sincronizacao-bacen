package br.com.sicredi.sincronizacao.builder;

import br.com.sicredi.sincronizacao.component.MessageComponent;
import br.com.sicredi.sincronizacao.dto.ContaAtualizadaDTO;
import br.com.sicredi.sincronizacao.dto.ContaDTO;
import br.com.sicredi.sincronizacao.enumeration.MessageKeyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContaAtualizadaBuilder {

  @Autowired
  private MessageComponent messageComponent;

  public ContaAtualizadaDTO build(Boolean status, ContaDTO contaDTO) {
    String statusConta = (
            status != null && status
            ? messageComponent.getMessage(MessageKeyEnum.ACCOUNT_UPDATE_EXECUTED.getKey())
            : messageComponent.getMessage(MessageKeyEnum.ACCOUNT_UPDATE_NOT_EXECUTED.getKey())
    );

    ContaAtualizadaDTO contaAtualizadaDTO = new ContaAtualizadaDTO();
    contaAtualizadaDTO.setAgencia(contaDTO.getAgencia());
    contaAtualizadaDTO.setConta(contaDTO.getConta());
    contaAtualizadaDTO.setSaldo(contaDTO.getSaldo());
    contaAtualizadaDTO.setStatus(statusConta);
    return contaAtualizadaDTO;
  }

}