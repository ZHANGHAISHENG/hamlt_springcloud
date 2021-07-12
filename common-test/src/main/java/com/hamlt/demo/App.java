package com.hamlt.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.hamlt.demo")
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
