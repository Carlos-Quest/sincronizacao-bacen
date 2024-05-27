package br.com.sicredi.sincronizacao.builder;

import br.com.sicredi.sincronizacao.component.MessageComponent;
import br.com.sicredi.sincronizacao.dto.ContaDTO;
import br.com.sicredi.sincronizacao.enumeration.MessageKeyEnum;
import br.com.sicredi.sincronizacao.exception.CSVFileNotFoundException;
import br.com.sicredi.sincronizacao.exception.ReadCSVFileException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ContaListBuilder {

  @Autowired
  private MessageComponent messageComponent;

  public List<ContaDTO> build(String filePath) {
    return readCSVFile(filePath);
  }

  private List<ContaDTO> readCSVFile(String filePath) throws ReadCSVFileException {
    try (FileReader reader = new FileReader(filePath)) {
      CsvToBean<ContaDTO> csvToContaDTO = new CsvToBeanBuilder<ContaDTO>(reader)
              .withType(ContaDTO.class)
              .withIgnoreLeadingWhiteSpace(true)
              .build();

      return csvToContaDTO.parse();
    } catch (FileNotFoundException ex) {
      String errorMessage = messageComponent.getMessage(MessageKeyEnum.FILE_NOT_FOUND.getKey());
      log.error("method=readCSVFile, message={}", errorMessage, ex);
      throw new CSVFileNotFoundException(errorMessage, ex);
    } catch (IOException ex) {
      String errorMessage = messageComponent.getMessage(MessageKeyEnum.READ_FILE_ERROR.getKey());
      log.error("method=readCSVFile, message={}", errorMessage, ex);
      throw new ReadCSVFileException(errorMessage, ex);
    }
  }

}