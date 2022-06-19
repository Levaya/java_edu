package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import edu.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size()==0) {
            app.contact().create(new ContactData().withFirstname("name").withLastname("surname").withEmail("mail@test"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Adam").withLastname("Smith");
        app.contact().modify(contact);
        Contacts after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedContact);
        before.add(contact);
        assertThat(after, equalTo(before.withModified(modifiedContact, contact)));
    }
}
