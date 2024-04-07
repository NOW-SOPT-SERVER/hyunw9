package org.sopt.view;

import java.util.Scanner;

public class BankInput {

  private final Scanner scanner;

  public BankInput() {
    scanner = new Scanner(System.in);
  }

  public String getBankName() {
    System.out.print("가입하고 싶은 은행 이름을 입력하세요: ");
    return scanner.nextLine();
  }

  public String getAccountType() {
    System.out.print("가입하고 싶은 계좌 종류를 입력하세요 (예: 저축예금, 정기예금): ");
    return scanner.nextLine();
  }

  public int getInitialBalance() {
    System.out.print("초기 설정 금액을 입력하세요 : ");
    return Integer.parseInt(scanner.nextLine());
  }

  public String getProductName(){
    System.out.print("가입 상품 명을 입력하세요. : ");
    return scanner.nextLine();
  }

  public String getProductType() {
    System.out.print("가입 상품 타입을 입력하세요. : ");
    return scanner.nextLine();
  }

  public double getProductRate() {
    System.out.print("가입 상품 이율을 입력하세요. : ");
    return Double.parseDouble(scanner.nextLine());
  }
}
