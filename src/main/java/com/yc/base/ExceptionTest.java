package com.yc.base;

/**
 * @Author yucheng
 * @Date 2020/12/8 16:54
 */
public class ExceptionTest {
    public static void main(String[] args) {
        try {
            throw new ExceptionB();
        } catch (ExceptionB exceptionB) {
            System.out.println("exceptionB");
        } catch (ExceptionA exceptionA) {
            System.out.println("exceptionA");
        } catch (Exception exception) {
            System.out.println("exception");
        }
    }
}

class ExceptionA extends Exception {

}

class ExceptionB extends ExceptionA {

}