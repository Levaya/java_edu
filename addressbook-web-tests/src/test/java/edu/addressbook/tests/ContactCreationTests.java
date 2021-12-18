package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().fillQuickAdd("NewContact");
    app.getNavigationHelper().gotoContactForm();
    app.getContactHelper().fillContactForm(new ContactData("NewLastname", "newcontact.newlastname@test.ru"));
  }

}
