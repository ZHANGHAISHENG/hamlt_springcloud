package com.hamlt.demo;

import com.google.common.hash.Hashing;

public class CommonTest {

    public static void main(String[] args) {
        int i = "abtooooooooooljhoooooooooooooooooooootc".hashCode();
        System.out.println(i);
        System.out.println(i >>> 16);
    }

}
