package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("name", "surname", "mail@test"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptDeletion();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size()-1);
    }
}
