package com.zheng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

  private Integer id;
  private String name;
  private Double salary;
  private Integer age;

  public Emp(String name, Double salary, Integer age) {
    this.name = name;
    this.salary = salary;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Emp{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", salary=" + salary +
            ", age=" + age +
            '}';
  }
}
