package br.com.sicredi.sincronizacao.util;

public class StringUtils {

  private StringUtils() {
  }

  private static String getDirectoryPath(String filePath) {
    Integer lastindex = filePath.lastIndexOf("/");
    return filePath.substring(0, lastindex);
  }

  public static String getResultFilePath(String filePath, String newFileName) {
    String directoryPath = getDirectoryPath(filePath);
    return directoryPath.concat("/").concat(newFileName);
  }

}