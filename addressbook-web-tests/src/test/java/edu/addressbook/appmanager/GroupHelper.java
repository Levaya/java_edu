package edu.addressbook.appmanager;

import edu.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public void selectGroup(int index){
        driver.findElements(By.name("selected[]")).get(index).click();
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
        return !isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return  driver.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        List<GroupData> groups = new ArrayList<GroupData>();
        for(WebElement element: elements){
            String name = element.getText();
            GroupData group = new GroupData(name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
