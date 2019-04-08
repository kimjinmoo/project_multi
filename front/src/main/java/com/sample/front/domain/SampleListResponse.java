package com.sample.front.domain;

import java.util.List;

public class SampleListResponse {

  private int total;
  private List<User> data;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<User> getData() {
    return data;
  }

  public void setData(List<User> data) {
    this.data = data;
  }
}
