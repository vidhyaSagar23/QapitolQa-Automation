package com.facebook;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SourceCode{
    @Test
    public static void sourceCode(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        String htmlcode = driver.getPageSource();
        System.out.println(htmlcode);
        driver.close();
}
}
