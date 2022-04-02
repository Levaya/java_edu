package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import edu.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoEditContactPage();
    app.getContactHelper().fillContactForm(new ContactData("NewFirstname", "NewLastname", "newcontact.newlastname@test.ru"));
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size()+1);
  }

}
