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

public class SignInWebsite {
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
        //driver.quit();
    }

    @Parameters({"emailaddrs","passwordnya"})
    @Test
    public void signInvalid(String emailaddrs, String passwordnya){

        WebElement buttonLogin = driver.findElement(By.className("header_user_info"));
        buttonLogin.click();
        sleep(2000);
        System.out.println("Klik button ahref");

        String actualUrl = driver.getCurrentUrl();
        driver.get(actualUrl);
        sleep(2000);

        WebElement emailField = driver.findElement(By.id("email"));
        System.out.println("email : "+emailaddrs);
        emailField.sendKeys(emailaddrs);
        sleep(3000);

        WebElement passField = driver.findElement(By.id("passwd"));
        System.out.println("passwd : "+passwordnya);
        passField.sendKeys(passwordnya);
        sleep(3000);

        WebElement buttonSignin = driver.findElement(By.id("SubmitLogin"));
        buttonSignin.click();
        sleep(2000);

//        String actualUrlSignin = driver.getCurrentUrl();
//        driver.get(actualUrlSignin);

        WebElement toastMessage = driver.findElement(By.id("center_column"));
        String actualMsg = toastMessage.getText();
        System.out.println(actualMsg);
    }

    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }
}
