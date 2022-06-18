package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Adam").withLastname("Smith");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();

    Assert.assertEquals(after.size(), before.size()+1);
    contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
