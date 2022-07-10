package edu.addressbook.tests;

import edu.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size()==0) {
            app.contact().create(new ContactData().withFirstname("name").withLastname("surname").withEmail("mail@test"));
        }
    }

    @Test
   public void testContactPhones(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    @Test
    public void testAllContacts(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllEmails(), equalTo(contactInfoFromEditForm.getAllEmails()));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    @Test
    public void testContactsFromDetails(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetails = app.contact().infoFromDetails(contact);

        assertThat(contactInfoFromDetails.getAllInfo(), containsString(contact.getFirstname()));
        assertThat(contactInfoFromDetails.getAllInfo(), containsString(contact.getLastname()));
        assertThat(contactInfoFromDetails.getAllInfo(), containsString(contact.getAddress()));
        assertThat(contactInfoFromDetails.getAllInfo(), containsString(contact.getAllEmails()));
        assertThat(cleaned(contactInfoFromDetails.getAllInfo()), containsString(joined(contact.getAllPhones())));
    }


    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s)->!s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone){
        return phone.replaceAll("\\s", "")
                .replaceAll("[-()a-zA-z:.,@]", "");
    }

    private String joined (String text){
        return text.replaceAll("\n", "");
    }
}
