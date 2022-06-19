package edu.addressbook.appmanager;

import edu.addressbook.model.ContactData;
import edu.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoEditContactPage() {
        click(By.cssSelector("a[href='edit.php']"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        click(By.xpath("//input[@type='submit'][2]"));
    }

    public void deleteContact() {
        click(By.cssSelector("input[value='Delete']"));
    }

    public void acceptDeletion() {
        driver.switchTo().alert().accept();
    }

    public void modify(ContactData contact) {
        modifyContactById(contact.getId());
        fillContactForm(contact);
    }

    public void create(ContactData contact) {
        gotoEditContactPage();
        fillContactForm(contact);
        click(By.linkText("home page"));
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteContact();
        acceptDeletion();
    }

    private void selectContactById(int id) {
        driver
                .findElements(By.cssSelector("tr>td:nth-child(1)>input"))
                .stream()
                .filter((c)->c
                        .getAttribute("id")
                        .equals(Integer.toString(id)))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    private void modifyContactById(int id) {
        List<String> ids = driver
                .findElements(By.cssSelector("tr>td:nth-child(1)>input"))
                .stream()
                .map((c)->c.getAttribute("id"))
                .collect(Collectors.toList());
        int index = ids.indexOf(Integer.toString(id));
        driver.findElements(By.cssSelector("img[title='Edit']")).get(index).click();
    }

    public boolean isThereAContact() {
        try {
            driver.findElement(By.name("selected[]"));
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public int getContactCount() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("maintable")));
        return driver.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("maintable")));

        List<WebElement> elements = driver.findElements(By.cssSelector("tr>td:nth-child(1)"));
        List<WebElement> names = driver.findElements(By.cssSelector("tr>td:nth-child(3)"));
        List<WebElement> lastnames = driver.findElements(By.cssSelector("tr>td:nth-child(2)"));
        Contacts contacts = new Contacts();
        for (WebElement element : elements) {
            int index = elements.indexOf(element);
            String firstName = names.get(index).getText();
            String lastName = lastnames.get(index).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return contacts;
    }
}

