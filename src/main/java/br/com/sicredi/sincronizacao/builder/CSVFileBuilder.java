package br.com.sicredi.sincronizacao.builder;

import br.com.sicredi.sincronizacao.component.MessageComponent;
import br.com.sicredi.sincronizacao.dto.ContaAtualizadaDTO;
import br.com.sicredi.sincronizacao.enumeration.MessageKeyEnum;
import br.com.sicredi.sincronizacao.exception.CreateCSVFileException;
import br.com.sicredi.sincronizacao.util.StringUtils;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class CSVFileBuilder {

  @Autowired
  private MessageComponent messageComponent;

  public void build(List<ContaAtualizadaDTO> contaAtualizadaDTOList,
                    String filePath) {
    createCSVFile(contaAtualizadaDTOList, filePath);
  }

  public void createCSVFile(
          List<ContaAtualizadaDTO> contaAtualizadaDTOList,
          String filePath) {
    String newFilePath = StringUtils.getResultFilePath(
            filePath, messageComponent.getMessage(MessageKeyEnum.NEW_FILE_NAME.getKey())
    );

    try (CSVWriter writer = new CSVWriter(new FileWriter(newFilePath))) {
      writer.writeNext(buildCSVFileHeader());

      contaAtualizadaDTOList.forEach(c -> {
        writer.writeNext(buildCSVFileline(c));
      });
    } catch (IOException ex) {
      String errorMessage = messageComponent.getMessage(MessageKeyEnum.CREATE_FILE_ERROR.getKey());
      log.error("method=createCSVFile, message={}", errorMessage, ex);
      throw new CreateCSVFileException(errorMessage, ex);
    }
  }

  private String[] buildCSVFileHeader() {
    return new String[] {
            messageComponent.getMessage(MessageKeyEnum.AGENCY_LABEL.getKey()),
            messageComponent.getMessage(MessageKeyEnum.ACCOUNT_UPDATE_EXECUTED.getKey()),
            messageComponent.getMessage(MessageKeyEnum.BALANCE_LABEL.getKey()),
            messageComponent.getMessage(MessageKeyEnum.STATUS_LABEL.getKey())
    };
  }

  private String[] buildCSVFileline(ContaAtualizadaDTO contaAtualizadaDTO) {
    return new String[] {
            contaAtualizadaDTO.getAgencia(),
            contaAtualizadaDTO.getConta(),
            String.valueOf(contaAtualizadaDTO.getSaldo()),
            contaAtualizadaDTO.getStatus()
    };
  }

}