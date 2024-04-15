package org.sopt.model;

public class BankProduct {

  private String bankName;
  private String productName;
  private String productType;
  private double interestRate;

  public BankProduct(String bankName, String productName, String productType, double interestRate) {
    this.bankName = bankName;
    this.productName = productName;
    this.productType = productType;
    this.interestRate = interestRate;
  }

  public String getBankName() {
    return bankName;
  }

  public String getProductName() {
    return productName;
  }

  public String getProductType() {
    return productType;
  }

  public double getInterestRate() {
    return interestRate;
  }
}
