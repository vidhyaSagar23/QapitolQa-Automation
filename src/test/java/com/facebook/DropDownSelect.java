package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class DropDownSelect {
    WebDriver driver;
    @Test
    public void demo() throws InterruptedException {
        driver = new ChromeDriver();
//        driver.get("https://www.facebook.com/r.php?locale=en_GB&display=page");
//
//        Thread.sleep(2000);
//        driver.manage().window().maximize();
//
//        WebElement dropDown = driver.findElement(By.id("day"));
//
//        Select select=new Select(dropDown);
//
//        Thread.sleep(2000);
//        select.selectByIndex(0);
//
//        Thread.sleep(2000);
//        select.selectByIndex(1);
//
//        List<WebElement> l=select.getAllSelectedOptions();
//        System.out.println(l);
//        Thread.sleep(2000);
//
        driver.get("https://demo.mobiscroll.com/select/multiple-select");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement dd=driver.findElement(By.xpath("//select[@id='multiple-select-select']"));
        Thread.sleep(2000);

       Select select1=new Select(dd);
        Thread.sleep(5000);
       select1.selectByIndex(2);

       Thread.sleep(2000);
       select1.deselectByIndex(2);

       Thread.sleep(2000);
       select1.selectByValue("1");

       Thread.sleep(2000);
       select1.selectByVisibleText("Movies, Music & Games");

        Thread.sleep(2000);
        select1.deselectAll();

        Thread.sleep(4000);
        driver.close();
    }
}
