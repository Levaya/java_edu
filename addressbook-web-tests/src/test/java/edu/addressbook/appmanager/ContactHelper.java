package edu.addressbook.appmanager;

import edu.addressbook.model.ContactData;
import edu.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
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
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        contactCache = null;
    }

    public void create(ContactData contact) {
        gotoEditContactPage();
        fillContactForm(contact);
        contactCache = null;
        click(By.linkText("home page"));
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteContact();
        acceptDeletion();
        contactCache = null;
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

    private void initContactModificationById(int id) {
        driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    private void goToContactDetailsWithId(int id) {
        driver.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    public boolean isThereAContact() {
        try {
            driver.findElement(By.name("selected[]"));
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public int count() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("maintable")));
        return driver.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("maintable")));

        if (contactCache != null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> rows = driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(1).getText();
            String lastName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return contactCache;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String allEmails = driver.findElements(By.cssSelector("input[name*='email'")).stream()
                .map((e)->e.getAttribute("value")).filter((s)->!s.isEmpty()).collect(Collectors.joining("\n"));
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName)
                .withAddress(address).withAllEmails(allEmails).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public ContactData infoFromDetails(ContactData contact) {
        goToContactDetailsWithId(contact.getId());
        String allInfo = driver.findElement(By.id("content")).getText();
        driver.navigate().back();
        return new ContactData().withAllInfo(allInfo);
    }
}

