package org.sopt.model;

import java.util.ArrayList;
import java.util.List;

public class BankProductList {

  private List<BankProduct> bankProductList;

  public BankProductList() {
    bankProductList = new ArrayList<>();
  }

  public void addBankProduct(BankProduct bankProduct) {
    bankProductList.add(bankProduct);
  }

  public List<BankProduct> getBankProductList() {
    return bankProductList;
  }

}
