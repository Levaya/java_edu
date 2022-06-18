package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size()==0) {
            app.contact().create(new ContactData().withFirstname("name").withLastname("surname").withEmail("mail@test"));
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Adam").withLastname("Smith");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedContact);
        before.add(contact);
        Collections.sort(before.stream().map(ContactData::getFirstname).collect(Collectors.toList()));
        Collections.sort(after.stream().map(ContactData::getFirstname).collect(Collectors.toList()));
        Assert.assertEquals(before, after);
    }
}
