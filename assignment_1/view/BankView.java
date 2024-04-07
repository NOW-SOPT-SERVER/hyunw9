package org.sopt.view;

import java.util.List;
import org.sopt.model.BankInfo;
import org.sopt.model.BankProduct;

public class BankView {

  public void showAvailableBanks(List<BankInfo> bankInfoList) {
    System.out.println("**가입 가능한 은행 목록**");
    for (BankInfo bankInfo : bankInfoList) {
      System.out.println("은행명: " + bankInfo.getBankName());
      System.out.println("은행코드: " + bankInfo.getBankCode());
      System.out.println("웹사이트: " + bankInfo.getWebsite());
      System.out.println();
    }
  }

  public void showAvailableProducts(List<BankProduct> bankProductList) {
    for (BankProduct product : bankProductList) {
      System.out.println("----------------상품----------------");
      System.out.println("은행 명 : " + product.getBankName());
      System.out.println("상품 명 : " + product.getProductName());
      System.out.println("상품 타입 : " + product.getProductType());
      System.out.println("이율 : " + product.getInterestRate());
    }
  }
}
