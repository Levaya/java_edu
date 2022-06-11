package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Smith", "Adam", null);
    app.getContactHelper().gotoEditContactPage();
    app.getContactHelper().fillContactForm(contact);
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size()+1);
    before.add(contact);
    Collections.sort(before.stream().map(ContactData::firstname).collect(Collectors.toList()));
    Collections.sort(after.stream().map(ContactData::firstname).collect(Collectors.toList()));
    Assert.assertEquals(before, after);
  }
}
