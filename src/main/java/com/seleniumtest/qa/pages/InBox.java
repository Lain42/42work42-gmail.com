package com.seleniumtest.qa.pages;

import com.seleniumtest.qa.base.BaseTest;
import com.seleniumtest.qa.utils.ApplicationProperties;
import com.seleniumtest.qa.utils.ReadEmail;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import javax.mail.MessagingException;
import java.io.IOException;

public class InBox extends BaseTest {
    @FindBy(xpath = "//*[contains(@class,'search-panel-button')]//span[@class='search-panel-button__text']")
    protected WebElement searchPanel;
    @FindBy(xpath = "//*[contains(@class,'search-panel__layer')]//input")
    protected WebElement searchPanelInput;
    @FindBy(xpath = "//span[text()='Найти']")
    protected WebElement searchButton;
    @FindBy(xpath = "//span[contains(@class,'highlighter__item_light')]")
    protected WebElement foundEmail;
    @FindBy(xpath = "//span[contains(@class,'highlighter__item_light')]/ancestor::a")
    protected WebElement linkEmail;
    @FindBy(xpath = "//div[contains(@class, 'letter__author')]/span[contains(@class,'letter-contact')]")
    protected WebElement letterAuthor;
    @FindBy(xpath = "//h2[contains(@class, 'thread-subject')]")
    protected WebElement emailSubject;
    public InBox() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 25), this);
    }
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
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
        Object testEmailFrom =  ReadEmail.email().get("from");
        String testEmailFromAddress = StringUtils.substringBetween(String.valueOf(testEmailFrom), "<", ">");
        String testEmailFromTitle = StringUtils.substringBetween(String.valueOf(testEmailFrom), "\"", "\"");

        wait.until(ExpectedConditions.visibilityOf(letterAuthor));
        Assert.assertEquals(letterAuthor.getText(), testEmailFromTitle);
        Assert.assertEquals(letterAuthor.getAttribute("title"), testEmailFromAddress);
    }

    public void checkSubjectEmail() throws IOException, MessagingException {
        wait.until(ExpectedConditions.visibilityOf(emailSubject));
        Assert.assertEquals(emailSubject.getText() + " \uD83D\uDE42", ReadEmail.email().get("subject"));
    }

    public void logOut() {


    }

}
