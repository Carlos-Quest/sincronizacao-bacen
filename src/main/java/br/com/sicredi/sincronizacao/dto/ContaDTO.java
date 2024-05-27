package br.com.sicredi.sincronizacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

  private String agencia;

  private String conta;

  private Double saldo;

}