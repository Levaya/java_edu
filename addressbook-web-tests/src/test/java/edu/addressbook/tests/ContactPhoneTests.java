package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactPhoneTests extends TestBase{

    @Test
   public void testContactPhones(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
    }
}
