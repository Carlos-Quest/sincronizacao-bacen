package br.com.sicredi.sincronizacao.enumeration;

import lombok.Getter;

@Getter
public enum MessageKeyEnum {

  FILE_NOT_FOUND ("file-not-found"),
  READ_FILE_ERROR ("read-file-error"),
  CREATE_FILE_ERROR ("create-file-error"),
  EMPTY_FILE_PATH ("empty-file-path"),
  ACCOUNT_UPDATE_EXECUTED ("account-update-executed"),
  ACCOUNT_UPDATE_NOT_EXECUTED ("account-update-not-executed"),
  NEW_FILE_NAME ("new-file-name"),
  AGENCY_LABEL ("agency-label"),
  ACCOUNT_LABEL ("account-label"),
  BALANCE_LABEL ("balance-label"),
  STATUS_LABEL ("status-label");

  private String key;

  private MessageKeyEnum(String key) {
    this.key = key;
  }

}