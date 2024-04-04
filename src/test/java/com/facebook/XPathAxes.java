package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class XPathAxes {
    @Test
    public void demo(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.facebook.com/r.php?locale=en_GB&display=page");

//        Following sibling

       String color= driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).getCssValue("color");
       System.out.println(color);

//       child

        String iconSize=driver.findElement(By.xpath("//div[text()='Gender']/child::a/child::i")).getCssValue("font-size");
        System.out.println(iconSize);

//        Preceding
        String text1=driver.findElement(By.xpath("//input[@type='radio' and @value='1']/preceding::label")).getText();
        System.out.println(text1);

//        Parent
        String text2=driver.findElement(By.xpath("//span[@class='_5k_3']/following::div[3]/child::select/child::option[2][@value='1']")).getText();
        System.out.println(text2);

        driver.close();
    }
}
