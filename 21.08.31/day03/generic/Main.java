package com.koreait.day03.generic;

public class Main {
    public static void main(String[] args) {
        Box<Integer> box1 = Util.<Integer>boxing(100);
        int intValue = box1.getT();

        Box<String> box2 = Util.boxing("문자열");
        String stringValue = box2.getT();

        System.out.println("intValue : " + intValue);
        System.out.println("stringValue : " + stringValue);
    }
}
