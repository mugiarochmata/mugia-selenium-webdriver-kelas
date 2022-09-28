package com.practice.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchProductNoresult {

    WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod
    public void BeforeMethod(String browser){
        System.out.println("Test Before Method");

        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();

        } else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        // Open page
        String url = "http://automationpractice.com/index.php";
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void AfterMethod(){
        System.out.println("Test After Method");
        driver.quit();
    }

    @Parameters({"searching"})
    @Test
    public void searchProduct(String searching){
        WebElement usernameField = driver.findElement(By.id("search_query_top"));
        System.out.println("search_query_top : "+searching);
        usernameField.sendKeys(searching);
        sleep(3000);

        WebElement buttonLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonLogin.click();
        sleep(2000);

        String UrlExpected = "http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=No+Product+Found&submit_search=";
        String actualUrl = driver.getCurrentUrl();

        WebElement toastMessage = driver.findElement(By.id("center_column"));
        String actualMsg = toastMessage.getText();
        System.out.println(actualMsg);
        driver.get(actualUrl);

    }

    @Parameters({"searching"})
    @Test
    public void searchProductResult(String searching){
        WebElement usernameField = driver.findElement(By.id("search_query_top"));
        System.out.println("search_query_top : "+searching);
        usernameField.sendKeys(searching);
        sleep(3000);

        WebElement buttonLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonLogin.click();
        sleep(2000);

        String UrlExpected = "http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=No+Product+Found&submit_search=";
        String actualUrl = driver.getCurrentUrl();

        WebElement toastMessage = driver.findElement(By.id("center_column"));
        String actualMsg = toastMessage.getText();
        System.out.println(actualMsg);
        driver.get(actualUrl);
    }

    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }
}
