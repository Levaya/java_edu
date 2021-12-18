package edu.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoGroupPage() {
        click(By.cssSelector("a[href='group.php']"));
    }

    public void gotoContactPage() {
        click(By.cssSelector("a[href='edit.php']"));
    }

    public void gotoContactForm() {
        click(By.xpath("//input[@name='quickadd']"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }
}
