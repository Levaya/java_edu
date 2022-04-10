package edu.addressbook.appmanager;

import edu.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoEditContactPage() {
        click(By.cssSelector("a[href='edit.php']"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.firstname());
        type(By.name("lastname"), contactData.lastname());
        type(By.name("email"), contactData.email());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact(int index){
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptDeletion(){
        driver.switchTo().alert().accept();
    }

    public void modifyContact(int index){
        driver.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void createContact(ContactData contact) {
        gotoEditContactPage();
        fillContactForm(contact);
        click(By.linkText("home"));
    }

    public boolean isThereAContact() {
        try {
            driver.findElement(By.name("selected[]"));
            return true;
        } catch (Throwable th){return false;}
    }

    public int getContactCount() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("maintable")));
        return  driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("maintable")));

        List<WebElement> names = driver.findElements(By.cssSelector("tr>td:nth-child(2)"));
        List<WebElement> lastnames = driver.findElements(By.cssSelector("tr>td:nth-child(3)"));
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (WebElement name: names){
            String firstName = name.getText();
            int index = names.indexOf(name);
            String lastName = lastnames.get(index).getText();
            ContactData contact = new ContactData(firstName, lastName, null);
            contacts.add(contact);
            }
        return contacts;
        }
    }

