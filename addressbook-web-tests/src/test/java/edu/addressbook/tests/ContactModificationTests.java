package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getContactHelper().modifyContact();
        app.getContactHelper().fillContactForm(new ContactData("Adam", "Smith", "adamsmith@test.ru"));
    }
}
