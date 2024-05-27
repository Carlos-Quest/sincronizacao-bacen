package br.com.sicredi.sincronizacao.exception;

public class CSVFileNotFoundException extends RuntimeException {

  public CSVFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}