package edu.addressbook.appmanager;

import edu.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends HelperBase {


    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.name());
        type(By.name("group_header"), groupData.header());
        type(By.name("group_footer"), groupData.footer());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void selectGroup(){
        click(By.name("selected[]"));
    }

    public void deleteGroup(){
        click(By.name("delete"));
    }

    public void modifyGroup(){
        click(By.name("edit"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return  driver.findElements(By.name("selected[]")).size();
    }
}
