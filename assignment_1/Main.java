package org.sopt;

import org.sopt.controller.BankController;
import org.sopt.controller.BankProductController;
import org.sopt.view.BankInput;

public class Main {

  public static void main(String[] args) {

    BankController bankController = new BankController();
    BankProductController bankProductController = new BankProductController();
    BankInput bankInput = new BankInput();
    // 사용자 입력 받아 은행 가입
    String bankName = bankInput.getBankName();
    String accountType = bankInput.getAccountType();
    int initialBalance = bankInput.getInitialBalance();

    bankController.createAccount(bankName, accountType, initialBalance);

    // 가입된 은행의 상품 목록 출력
    bankProductController.showAvailableProducts();

    // 사용자 입력 받아 은행 상품 가입
    String productName = bankInput.getProductName();
    String productType = bankInput.getProductType();

    bankProductController.createProduct(bankName, productName, productType);


  }

}
