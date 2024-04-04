package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IFrame {
    public WebDriver driver;
    @BeforeMethod
    public  void getDriver(){
        driver=new ChromeDriver();
    }

    @Test
    public void frames() throws InterruptedException {
        driver.get("file:///C:/Users/sagar/Desktop/page2.html");
        driver.manage().window().maximize();
        driver.switchTo().frame(0);
        driver.findElement(By.id("t1")).sendKeys("sagar");
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        driver.findElement(By.id("t2")).sendKeys("vp");
        Thread.sleep(2000);
    }

    @AfterMethod
    public void close(){
        driver.close();
    }
}
