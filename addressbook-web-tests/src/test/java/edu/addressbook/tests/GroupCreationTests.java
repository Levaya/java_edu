package edu.addressbook.tests;

import edu.addressbook.model.GroupData;
import edu.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation(){
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        Groups after = app.group().all();

        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(before.withAdded(group
                .withId(after.stream().mapToInt(GroupData::getId).max().getAsInt())), equalTo(after));
    }
}
