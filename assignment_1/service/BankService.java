package org.sopt.service;

import org.sopt.model.BankInfo;
import org.sopt.model.BankList;
import org.sopt.model.BankProduct;
import org.sopt.model.BankProductList;

public class BankService {

  public BankList getAvailableBanks() {
    BankList bankList = new BankList();

    BankInfo bankInfo1 = new BankInfo("국민은행", "004", "https://www.kbstar.com/");
    BankInfo bankInfo2 = new BankInfo("신한은행", "088", "https://www.shinhan.com/");
    BankInfo bankInfo3 = new BankInfo("우리은행", "209", "https://wooribank.com/");

    bankList.addBankInfo(bankInfo1);
    bankList.addBankInfo(bankInfo2);
    bankList.addBankInfo(bankInfo3);

    return bankList;
  }

  public BankProductList getAvailableProducts() {
    BankProductList bankProductList = new BankProductList();

    BankProduct bankProduct1 = new BankProduct("국민은행", "KB 국민적금", "적금", 2.25);
    BankProduct bankProduct2 = new BankProduct("국민은행", "KB 예금상품", "예금", 0.1);

    bankProductList.addBankProduct(bankProduct1);
    bankProductList.addBankProduct(bankProduct2);

    return bankProductList;
  }
}
