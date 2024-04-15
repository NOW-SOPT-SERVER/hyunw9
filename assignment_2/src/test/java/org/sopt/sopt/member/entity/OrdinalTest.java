package org.sopt.sopt.member.entity;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class OrdinalTest {

  @Test
  @DisplayName("Ordinal 은 순서를 반환한다.")
  public void Ordinal_반환_테스트(){

    //given
   Part IOS = Part.IOS;

    //when
    int iosOrdinal = IOS.ordinal();

    //then
    Assertions.assertThat(iosOrdinal).isEqualTo(1);
  }



}
