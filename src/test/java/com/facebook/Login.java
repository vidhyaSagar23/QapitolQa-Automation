package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Login {
    public WebDriver driver;

    @Test
    public void login() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/login/");
        Thread.sleep(2000);
        driver.manage().window().maximize();

        driver.findElement(By.id("email")).sendKeys("sagarelango13@gmail.com");
        Thread.sleep(2000);

        driver.findElement(By.id("pass")).sendKeys("Sagarvp231!");
        Thread.sleep(2000);

        driver.findElement(By.id("loginbutton")).click();
    }

    @Test
    public void forgotPassword() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/login/");
        Thread.sleep(2000);
        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.linkText("Forgotten account?")).click();

        Thread.sleep(2000);
        driver.findElement(By.id("identify_email")).sendKeys("sagarelango13@gmail.com");

        Thread.sleep(2000);
        driver.findElement(By.name("did_submit")).click();
    }

    @Test
    public void signUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/login/");

        Thread.sleep(2000);
        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.linkText("Sign up for Facebook")).click();

        Thread.sleep(2000);
        driver.findElement(By.name("firstname")).sendKeys("Vidhya");

        Thread.sleep(2000);
        driver.findElement(By.name("lastname")).sendKeys("Sagar");

        Thread.sleep(2000);
        driver.findElement(By.name("reg_email__")).sendKeys("sagarelango13@gmail.com");

        driver.findElement(By.name("reg_email_confirmation__")).sendKeys("sagarelango13@gmail.com");

        Thread.sleep(2000);
        driver.findElement(By.id("password_step_input")).sendKeys("Sagarvp23@");

        Thread.sleep(2000);
        driver.findElement(By.name("birthday_day")).sendKeys("13");

        Thread.sleep(2000);
        driver.findElement(By.name("birthday_month")).sendKeys("Aug");

        Thread.sleep(2000);
        driver.findElement(By.name("birthday_year")).sendKeys("2001");

        Thread.sleep(2000);
       driver.findElement(By.xpath("//input[@name='sex' and @value='1']")).click();

        Thread.sleep(2000);
         driver.findElement(By.xpath("//input[@name='sex' and @value='2']")).click();

        Thread.sleep(2000);
        driver.findElement(By.name("websubmit")).click();
    }

    @Test
    public void alreadyHaveAnAccount() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/login/");

        Thread.sleep(2000);
        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.linkText("Sign up for Facebook")).click();

        driver.findElement(By.linkText("Already have an account?")).click();
    }

    @Test
    public void forgotPasswordLogin() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/login/identify/?ctx=recover&ars=facebook_login&from_login_screen=0");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='email' and @type = 'email']")).sendKeys("sagarelango13@gmail.com");

        driver.findElement(By.xpath("//input[@name='pass' and @type = 'password']")).sendKeys("Sagarvp1323!");

        driver.findElement(By.xpath("//div[@role='button' and @aria-label='Accessible login button']")).click();
    }
}
