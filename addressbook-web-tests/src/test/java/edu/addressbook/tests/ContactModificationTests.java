package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("name", "surname", "mail@test"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().modifyContact();
        app.getContactHelper().fillContactForm(new ContactData("Adam", "Smith", "adamsmith@test.ru"));
        int after = app.getContactHelper().getContactCount();

        Assert.assertEquals(before, after);
    }
}
