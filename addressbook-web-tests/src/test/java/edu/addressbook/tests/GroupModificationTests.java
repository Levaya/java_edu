package edu.addressbook.tests;

import edu.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "group1", "group1"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().modifyGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
