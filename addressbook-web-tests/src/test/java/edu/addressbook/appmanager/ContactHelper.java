package edu.addressbook.appmanager;

import edu.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper {
    private WebDriver driver;

    public ContactHelper(WebDriver driver) {
        this.driver=driver;
    }

    public void fillContactForm(ContactData contactData) {
      driver.findElement(By.name("lastname")).click();
      driver.findElement(By.name("lastname")).clear();
      driver.findElement(By.name("lastname")).sendKeys(contactData.lastname());
      driver.findElement(By.name("email")).click();
      driver.findElement(By.name("email")).clear();
      driver.findElement(By.name("email")).sendKeys(contactData.email());
      driver.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillQuickAdd(String name) {
      driver.findElement(By.name("address")).clear();
      driver.findElement(By.name("address")).sendKeys(name);
    }
}
