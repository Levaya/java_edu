package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("name", "surname", "mail@test"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptDeletion();
        int after = app.getContactHelper().getContactCount();

        Assert.assertEquals(after, before-1);
    }
}
