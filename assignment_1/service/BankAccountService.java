package org.sopt.service;

import java.util.Random;

public class BankAccountService {

  public static String generateAccountNumber() {
    // 랜덤 숫자 생성
    Random random = new Random();
    int randomNumber = random.nextInt(90000000) + 10000000;

    // 랜덤 문자 생성
    String randomAlphabet = "";
    for (int i = 0; i < 2; i++) {
      randomAlphabet += (char) (random.nextInt(26) + 'A');
    }

    // 랜덤 계좌번호 생성
    String accountNumber = randomNumber + randomAlphabet;

    return accountNumber;
  }
}
