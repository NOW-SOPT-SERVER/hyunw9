package org.sopt.model;

import java.util.ArrayList;
import java.util.List;

public class BankList {

  private List<BankInfo> bankInfoList;

  public BankList() {
    bankInfoList = new ArrayList<>();
  }

  public void addBankInfo(BankInfo bankInfo) {
    bankInfoList.add(bankInfo);
  }

  public List<BankInfo> getBankInfoList() {
    return bankInfoList;
  }

}
