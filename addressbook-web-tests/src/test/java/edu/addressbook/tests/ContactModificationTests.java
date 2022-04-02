package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("name", "surname", "mail@test"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().modifyContact();
        app.getContactHelper().fillContactForm(new ContactData("Adam", "Smith", "adamsmith@test.ru"));
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());
    }
}
