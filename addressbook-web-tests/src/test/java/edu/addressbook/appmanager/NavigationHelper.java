package edu.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                &&driver.findElement(By.tagName("h1")).getText().equals("Groups")
                &&isElementPresent(By.name("new"))){
            return;
        }
        click(By.cssSelector("a[href='group.php']"));
    }

    public void gotoHomePage(){
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

}
