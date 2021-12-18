package edu.addressbook.appmanager;

import edu.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("lastname"), contactData.lastname());
        type(By.name("email"), contactData.email());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillQuickAdd(String name) {
        type(By.name("address"), name);
    }
}
