package org.sopt.model;

public class Account {

  private final String bankName;
  private final String accountNumber;
  private final String accountType;
  private final int balance;

  public Account(String bankName, String accountNumber, String accountType, int balance) {
    this.bankName = bankName;
    this.accountNumber = accountNumber;
    this.accountType = accountType;
    this.balance = balance;
  }

}
