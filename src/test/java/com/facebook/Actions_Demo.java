package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Actions_Demo {
    public WebDriver driver;
    @BeforeMethod
    public void Driver(){
        driver=new ChromeDriver();
    }

    @Test
    public void contextClick() throws InterruptedException {
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        WebElement pass= driver.findElement(By.xpath("//input[@id='pass']"));
        Actions actions=new Actions(driver);
        actions.moveToElement(pass).perform();
        Thread.sleep(2000);
        actions.contextClick(pass).perform();
        Thread.sleep(2000);
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.switchTo().frame(0);
        WebElement source=driver.findElement(By.id("draggable"));
        WebElement dest=driver.findElement(By.xpath("//div[@id='droppable']/child::p"));
        Thread.sleep(2000);
        Actions actions=new Actions(driver);
        actions.moveToElement(source).clickAndHold().perform();
        Thread.sleep(2000);
        actions.release(dest).perform();
        Thread.sleep(2000);
    }

//    @Test
//    public void actions()
//    {
//        driver.get("https://www.google.com");
//        Actions actions = new Actions(driver);
//
//        //Mouse Hover
//        WebElement elementToHover = driver.findElement(By.id("elementId"));
//        actions.moveToElement(elementToHover).perform();
//
//        //Drag and Drop
//        WebElement sourceElement = driver.findElement(By.id("sourceElementId"));
//        WebElement targetElement = driver.findElement(By.id("targetElementId"));
//        actions.dragAndDrop(sourceElement, targetElement).perform();
//
//        //Double-click
//        WebElement elementToDoubleClick = driver.findElement(By.id("doubleClickElementId"));
//        actions.doubleClick(elementToDoubleClick).perform();
//
//        driver.quit();
//}


    @AfterMethod
    public void close(){
        driver.close();
    }
}
