package com.seleniumtest.qa.pages;

import com.seleniumtest.qa.base.BaseTest;
import com.seleniumtest.qa.utils.ApplicationProperties;
import com.seleniumtest.qa.utils.ReadEmail;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.mail.MessagingException;
import java.io.IOException;


public class InBox extends BaseTest {
    @FindBy(xpath = "//*[contains(@class,'search-panel-button')]//span[@class='search-panel-button__text']")
    private WebElement searchPanel;

    @FindBy(xpath = "//*[contains(@class,'search-panel__layer')]//input")
    private WebElement searchPanelInput;

    @FindBy(xpath = "//span[text()='Найти']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[contains(@class, 'llc__item_correspondent')]//span")
    private WebElement correspondentLetterInList;

    @FindBy(xpath = "//span[contains(@class,'highlighter__item_light')]")
    private WebElement foundEmail;

    @FindBy(xpath = "//span[contains(@class,'highlighter__item_light')]/ancestor::a")
    private WebElement linkEmail;

    @FindBy(xpath = "//div[contains(@class, 'letter__author')]/span[contains(@class,'letter-contact')]")
    private WebElement letterAuthor;

    @FindBy(xpath = "//h2[contains(@class, 'thread__subject')]")
    private WebElement emailSubject;

    @FindBy(xpath = "//div[contains(@class, 'letter__header-row')]/span[contains(@class, 'button2')]")
    private WebElement More;

    public InBox() {
        PageFactory.initElements(driver, this);
    }

    private WebDriverWait wait = new WebDriverWait(driver, 10000);

    public void searchLetter() {
        wait.until(ExpectedConditions.visibilityOf(searchPanel));
        searchPanel.click();
        searchPanelInput.sendKeys(ApplicationProperties.INSTANCE.getSearchword());
        searchButton.click();
    }

    public void checkBodyEmailInList() {
        wait.until(ExpectedConditions.visibilityOf(foundEmail));
        Assert.assertEquals(foundEmail.getText(), ApplicationProperties.INSTANCE.getSearchword());
    }

    public void openEmail() {
        linkEmail.click();
        wait.until(ExpectedConditions.visibilityOf(letterAuthor));
    }

    public void checkSenderEmail() throws Exception {
        String testEmailFrom = (String) ReadEmail.email().get("from");
        String testEmailFromAddress = StringUtils.substringBetween(testEmailFrom, "<", ">");
        String testEmailFromTitle = StringUtils.substringBetween(testEmailFrom, "\"", "\"");

        wait.until(ExpectedConditions.visibilityOf(letterAuthor));
        Assert.assertEquals(letterAuthor.getText(), testEmailFromTitle);
        Assert.assertEquals(letterAuthor.getAttribute("title"), testEmailFromAddress);
    }

    public void checkSubjectEmail() throws IOException, MessagingException {
        wait.until(ExpectedConditions.visibilityOf(emailSubject));
        Assert.assertEquals(emailSubject.getText(), ReadEmail.email().get("subject"));
    }

    public void logOut() {


    }

}
