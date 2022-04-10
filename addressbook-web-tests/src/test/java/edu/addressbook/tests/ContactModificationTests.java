package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("name", "surname", "mail@test"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Adam", "Smith", null);
        app.getContactHelper().modifyContact(before.size()-1);
        app.getContactHelper().fillContactForm(contact);
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size()-1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
