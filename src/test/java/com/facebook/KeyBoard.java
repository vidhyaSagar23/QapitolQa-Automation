package com.facebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class KeyBoard {
    public WebDriver driver;

    @Test
    public void keyBoardOperations() throws AWTException, InterruptedException {
        driver=new ChromeDriver();
        driver.get("https://www.facebook.com/login/");

        driver.manage().window().maximize();

        Robot robot=new Robot();

        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
    }

    @Test
    public void mouse() throws Exception {

        driver=new ChromeDriver();
        driver.get("https://www.facebook.com/login/");

        driver.manage().window().maximize();
        Robot robot = new Robot();

        int x = 500;
        int y = 300;
        Thread.sleep(2000);
        robot.mouseMove(x, y);

//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        Thread.sleep(1000);

        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);

        Thread.sleep(2000);
        driver.close();
    }
}
