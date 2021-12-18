package edu.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver driver;

    public NavigationHelper(WebDriver driver) {
        this.driver=driver;
    }

    public void gotoGroupPage() {
        driver.findElement(By.cssSelector("a[href='group.php']")).click();
    }

    public void gotoContactPage() {
        driver.findElement(By.cssSelector("a[href='edit.php']")).click();
    }

    public void gotoContactForm() {
        driver.findElement(By.xpath("//input[@name='quickadd']")).click();
    }
}
