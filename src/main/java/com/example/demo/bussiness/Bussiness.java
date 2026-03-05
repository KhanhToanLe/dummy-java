package com.example.demo.bussiness;

import tools.jackson.databind.ser.impl.FailingSerializer;

/**
 * java doc class.
 */
public class Bussiness {
  /**
   * java doc method.
   */
  public static void doSomething(String[] args) {
    String a = "hello world";
  }

  /**
   * java doc method.
   */
  public static String doSomething(String value) {
    return "hello world";
  }
  
  public static String doSomething(float value){
    return Float.toString(value);
  }

  /**
   * java doc method.
   */
  public static String doSomething(int value) {
    return Integer.toString(value);
  }

  /**
   * java doc method.
   */
  public static String doSomething(boolean value) {
    return Boolean.toString(value);
  }

  /**
   * java doc method.
   */
  public static String doSomething(long value) {
    return Long.toString(value);
  }

  /**
   * java doc method.
   */
  public static String doSomething(byte value) {
    return Byte.toString(value);
  }
}

