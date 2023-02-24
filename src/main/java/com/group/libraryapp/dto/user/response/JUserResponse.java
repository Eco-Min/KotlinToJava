package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.JUser;

public class JUserResponse {

  private final long id;
  private final String name;
  private final Integer age;

  public JUserResponse(JUser user) {
    this.id = user.getId();
    this.name = user.getName();
    this.age = user.getAge();
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

}
