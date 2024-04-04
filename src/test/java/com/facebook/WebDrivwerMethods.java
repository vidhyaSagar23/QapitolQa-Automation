package com.facebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Set;

public class WebDrivwerMethods {
    @Test
    public void methods(){

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.facebook.com");

        String title = driver.getTitle();
        System.out.println("Title: " + title);

        String pageSource = driver.getPageSource();
        System.out.println("Page Source: " + pageSource);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        String windowHandle = driver.getWindowHandle();
        System.out.println("Window Handle: " + windowHandle);

        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("Window Handles: " + windowHandles);

        WebDriver.Options options = driver.manage();
        System.out.println("Options: " + options);

        driver.navigate().refresh();

//        driver.quit();

        driver.close();

//        driver.switchTo().window("windowName");
    }
}
