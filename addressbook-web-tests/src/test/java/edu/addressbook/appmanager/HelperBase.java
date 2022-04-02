package edu.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver=driver;
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text!=null) {
            String existingText=driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                click(locator);
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    protected boolean isElementPresent(By locator) {
        try{
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){return false;}
    }
}
