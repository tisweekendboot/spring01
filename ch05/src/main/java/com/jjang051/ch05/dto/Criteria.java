package com.jjang051.ch05.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Criteria {

  private int page; // 기준이 되는 페이지
  private int pageSize;

  public Criteria() {
    page = 1;
    pageSize = 3;
  }

  public Criteria(int page, int pageSize) {
    this.page = page;
    this.pageSize = pageSize;
  }

  public int getBeginPage() {
    return (page - 1) * pageSize + 1;
  }

  public int getEndPage() {
    return page * pageSize;
  }
}
