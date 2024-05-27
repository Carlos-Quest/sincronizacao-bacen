package br.com.sicredi.sincronizacao.service;

import br.com.sicredi.sincronizacao.builder.CSVFileBuilder;
import br.com.sicredi.sincronizacao.builder.ContaAtualizadaBuilder;
import br.com.sicredi.sincronizacao.builder.ContaListBuilder;
import br.com.sicredi.sincronizacao.component.ParameterComponent;
import br.com.sicredi.sincronizacao.dto.ContaAtualizadaDTO;
import br.com.sicredi.sincronizacao.dto.ContaDTO;
import br.com.sicredi.sincronizacao.timer.MeasuredExecutionTime;
import br.com.sicredi.sincronizacao.validate.FilePathValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SincronizacaoService {

  @Autowired
  private BancoCentralService bancoCentralService;

  @Autowired
  private FilePathValidate filePathValidate;

  @Autowired
  private ContaListBuilder contaListBuilder;

  @Autowired
  private ContaAtualizadaBuilder contaAtualizadaBuilder;

  @Autowired
  private CSVFileBuilder csvFileBuilder;

  @MeasuredExecutionTime
  public void syncAccounts() {
    String filePath = ParameterComponent.getFilePath();
    filePathValidate.pathEmptyValidate(filePath);

    List<ContaDTO> contaDTOList = contaListBuilder.build(filePath);

    List<ContaAtualizadaDTO> contaAtualizadaDTOList = contaDTOList
            .stream()
            .map(c -> {
              ContaAtualizadaDTO contaAtualizadaDTO = null;
              try {
                contaAtualizadaDTO = contaAtualizadaBuilder.build(bancoCentralService.atualizaConta(c), c);
              } catch (Exception ex) {
                contaAtualizadaDTO = contaAtualizadaBuilder.build(Boolean.FALSE, c);
              }
              return contaAtualizadaDTO;
            })
            .collect(Collectors.toList());

    csvFileBuilder.build(contaAtualizadaDTOList, filePath);
  }

}