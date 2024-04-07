package org.sopt.model;

import java.util.ArrayList;
import java.util.List;

public class AccountList {

  private final List<Account> accountList;

  public AccountList() {
    accountList = new ArrayList<>();
  }

  public void addAccount(Account account) {
    accountList.add(account);
  }

}
