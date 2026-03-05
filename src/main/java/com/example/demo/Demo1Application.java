package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * java doc class.
 */
@SpringBootApplication
public class Demo1Application {
  /**
   * java doc method.
   */
  public static void main(String[] args) {
//    String a = "hello\nworld";
//    String[] result = a.split("\n");
//    for(String string : result){
//      System.out.println(string);
//    }
    Object a = new Object();
    System.out.println(a.getClass().toString());
    SpringApplication.run(Demo1Application.class, args);
  }
}
