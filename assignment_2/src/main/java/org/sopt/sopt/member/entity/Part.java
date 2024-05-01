package org.sopt.sopt.member.entity;

public enum Part {

  ANDROID(1), IOS(2), SERVER(3);

  private final int part;

  Part(int part) {
    this.part = part;
  }

  public int getPartNum(){
    return part;
  }
}
