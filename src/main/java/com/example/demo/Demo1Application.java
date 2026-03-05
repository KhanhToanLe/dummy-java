package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * java doc class.
 */
@SpringBootApplication
public class Demo1Application {
  /**
   * java doc method.
   */
  public static void main(String[] args) {
    System.out.println("changed");
    SpringApplication.run(Demo1Application.class, args);
  }

}
