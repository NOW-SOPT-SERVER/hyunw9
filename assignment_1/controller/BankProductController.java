package org.sopt.controller;

import org.sopt.model.BankProduct;
import org.sopt.model.BankProductList;
import org.sopt.service.BankAccountService;
import org.sopt.service.BankService;
import org.sopt.view.BankView;

public class BankProductController {

  private final BankService bankService;
  private final BankAccountService bankAccountService;
  private final BankView bankView;
  private final BankProductList bankProductList;

  public BankProductController() {
    bankService = new BankService();
    bankAccountService = new BankAccountService();
    bankProductList = new BankProductList();
    bankView = new BankView();
  }

  public void showAvailableProducts() {
    BankProductList bankProductList = bankService.getAvailableProducts();
    bankView.showAvailableProducts(bankProductList.getBankProductList());
  }

  public void createProduct(String bankName, String productName, String productType) {
    BankProduct bankProduct = new BankProduct(bankName, productName, productType, 0.0);

    bankProductList.addBankProduct(bankProduct);

    System.out.println("**" + bankName + "** 은행 **" + productName + "** 상품 가입 완료되었습니다.");
  }


}
