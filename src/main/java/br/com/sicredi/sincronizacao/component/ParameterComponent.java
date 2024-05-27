package br.com.sicredi.sincronizacao.component;

import lombok.Synchronized;

public class ParameterComponent {

  private static String filePath;

  private ParameterComponent() {
  }

  @Synchronized
  public static String getFilePath() {
    return filePath;
  }

  @Synchronized
  public static void setFilePath(String filePath) {
    ParameterComponent.filePath = filePath;
  }

}