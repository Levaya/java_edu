package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("surname", "name", "mail@test"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Smith", "Adam", null);
        app.getContactHelper().modifyContact(before.size() - 1);
        app.getContactHelper().fillContactForm(contact);
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Collections.sort(before.stream().map(ContactData::firstname).collect(Collectors.toList()));
        Collections.sort(after.stream().map(ContactData::firstname).collect(Collectors.toList()));
        Assert.assertEquals(before, after);
    }
}
