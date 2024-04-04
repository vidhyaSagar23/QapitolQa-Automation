package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebElementMethods {
    @Test
    public static void demo() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.facebook.com/login/");

        // Find an input element
        WebElement inputElement = driver.findElement(By.id("email"));

        // Enter text into an input element
        inputElement.sendKeys("sagarelango13@gmail.com");

        WebElement inputPass = driver.findElement(By.id("pass"));

        inputPass.sendKeys("Sagarvp1323!");

        WebElement buttonElement = driver.findElement(By.id("loginbutton"));

        // Get the value of an attribute
        String attributeValue1 = inputElement.getAttribute("type");
        System.out.println("Attribute Value: " + attributeValue1);

        String attributeValue2 = inputPass.getAttribute("type");
        System.out.println("Attribute Value: " + attributeValue2);

        String attributeValue3 = buttonElement.getAttribute("type");
        System.out.println("Attribute Value: " + attributeValue3);

        // Get the value of a CSS property
        String cssValue = inputElement.getCssValue("color");
        System.out.println("CSS Value: " + cssValue);

        // Get the location of an element
        org.openqa.selenium.Point location = inputElement.getLocation();
        System.out.println("Location: " + location);

        // Get the size of an element
        org.openqa.selenium.Dimension size = inputElement.getSize();
        System.out.println("Size: " + size);

        // Get the tag name of an element
        String tagName = inputElement.getTagName();
        System.out.println("Tag Name: " + tagName);

        // Get the text of an element
        String text = buttonElement.getText();
        System.out.println("Text: " + text);

        // Check if an element is displayed
        boolean isDisplayed = inputElement.isDisplayed();
        System.out.println("Is Displayed: " + isDisplayed);

        // Check if an element is enabled
        boolean isEnabled = inputElement.isEnabled();
        System.out.println("Is Enabled: " + isEnabled);

        // Check if an element is selected
        boolean isSelected1 = inputElement.isSelected();
        System.out.println("Is Selected: " + isSelected1);

        boolean isSelected2 = inputPass.isSelected();
        System.out.println("Is Selected: " + isSelected2);

        boolean isSelected3 = buttonElement.isSelected();
        System.out.println("Is Selected: " + isSelected3);

        Thread.sleep(2000);

        // Click on a button
        buttonElement.click();

        // Submit a form
//        inputElement.submit();

        // Close the browser
        driver.quit();
    }
}
