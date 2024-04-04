package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Table {
    public WebDriver driver;
    @BeforeMethod
    public  void getDriver(){
        driver=new ChromeDriver();
    }

    @Test
    public void demo() throws InterruptedException {
        driver.get("file:///C:/Users/sagar/Desktop/table.html");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        List<WebElement> rows=driver.findElements(By.xpath("//tr"));
        int totalRows=rows.size();
        System.out.println("total rows : "+totalRows);

        List<WebElement> col=driver.findElements(By.xpath("//th"));
        int totalCol=col.size();
        System.out.println("total cols : "+totalCol);

        List<WebElement> allCells = driver.findElements(By.xpath("//th|//td"));
        int totalCells = allCells.size();
        System.out.println("Total number of cells present in the table is :" + totalCells);

        int countNumberValue = 0;
        int sum=0;
        for (WebElement cell : allCells) {
            String cellValue = cell.getText();
            try{
                int number = Integer.parseInt(cellValue);
//                System.out.print(“ ”+number);
                System.out.print(" "+number);
                countNumberValue++;
                sum = sum+number;
            }catch (Exception e) {
            }
        }
        System.out.println("\nTotal count of numeric values is :"+countNumberValue);
        System.out.println("Total sum of all the numeric values is :"+sum);


    }

    @AfterMethod
    public void close(){
        driver.close();
    }
}
