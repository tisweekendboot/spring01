package com.jjang051.ch05.utils;

import com.jjang051.ch05.dto.Criteria;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PageMaker {

  // 이게 pagination만들어주는 util
  private Criteria criteria;
  private int totalCount;
  private int beginPage;
  private int endPage;
  private int paginationSize = 2; // 밑에꺼 한번에 보여지는 갯수
  private boolean isPrev;
  private boolean isNext;
  private int count;

  // 전체 게시물 수를 알게되면 만들 수 있다.
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
    makePage();
  }

  private void makePage() {
    endPage = (int) Math.ceil((criteria.getPage() / (double) paginationSize)) * paginationSize;
    beginPage = (endPage - paginationSize) + 1;
    count = totalCount - (criteria.getPage() - 1) * criteria.getPageSize();
    if (beginPage <= 0) beginPage = 1;
    // totalCount = 55
    // criteria.getPageSize() = 10
    //
    int lastPage = (int) Math.ceil(totalCount / (double) criteria.getPageSize());
    if (endPage > lastPage) endPage = lastPage;
    isPrev = beginPage == 1 ? false : true;
    isNext = lastPage > endPage ? true : false;
  }
}
