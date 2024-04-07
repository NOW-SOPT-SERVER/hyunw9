package org.sopt.controller;

import org.sopt.model.Account;
import org.sopt.model.AccountList;
import org.sopt.model.BankList;
import org.sopt.service.BankAccountService;
import org.sopt.service.BankService;
import org.sopt.view.BankView;

public class BankController {

  private final BankService bankService;
  private final BankView bankView;
  private final AccountList accountList;

  public BankController() {
    bankService = new BankService();
    BankAccountService bankAccountService = new BankAccountService();
    bankView = new BankView();
    accountList = new AccountList();
  }

  public void showAvailableBanks() {
    BankList bankList = bankService.getAvailableBanks();
    bankView.showAvailableBanks(bankList.getBankInfoList());
  }

  public void createAccount(String bankName, String accountType, int initialBalance) {
    String accountNumber = BankAccountService.generateAccountNumber();
    Account account = new Account(bankName, accountNumber, accountType, initialBalance);

    accountList.addAccount(account);

    System.out.println(
        "**" + bankName + "** 은행에 **" + accountType + "** 계좌 (" + accountNumber + ")가 생성되었습니다.");
    System.out.println("초기 잔고: " + initialBalance + "원");
  }
}
