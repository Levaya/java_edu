package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getContactHelper().gotoEditContactPage();
    app.getContactHelper().fillContactForm(new ContactData("NewFirstname", "NewLastname", "newcontact.newlastname@test.ru"));
    app.getNavigationHelper().gotoHomePage();
  }

}
