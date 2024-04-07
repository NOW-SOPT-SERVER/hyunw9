package org.sopt.model;

public class BankInfo {

  private final String bankName;
  private final String bankCode;
  private final String website;

  public BankInfo(String bankName, String bankCode, String website) {
    this.bankName = bankName;
    this.bankCode = bankCode;
    this.website = website;
  }

  public String getBankName() {
    return bankName;
  }

  public String getBankCode() {
    return bankCode;
  }

  public String getWebsite() {
    return website;
  }


}
