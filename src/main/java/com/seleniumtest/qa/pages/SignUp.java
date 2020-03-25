package com.seleniumtest.qa.pages;

import com.seleniumtest.qa.base.BaseTest;
import com.seleniumtest.qa.utils.ApplicationProperties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignUp extends BaseTest {

    @FindBy(xpath = "//input[@id='mailbox:login']")
    private WebElement login;

    @FindBy(xpath = "//input[@id='mailbox:password']")
    private WebElement password;

    @FindBy(xpath = "//form[@id='auth']//input[@type='submit']")
    private WebElement signUpButton;

    @FindBy(xpath = "//i[@id='PH_user-email']")
    private WebElement userAddress;

    @FindBy(xpath="//a[@id='PH_logoutLink']")
    private  WebElement logOut;

    public SignUp() {
        PageFactory.initElements(driver, this);
    }
    private WebDriverWait wait = new WebDriverWait(driver, 3000);

    public void signUp() throws InterruptedException {
        login.sendKeys(ApplicationProperties.INSTANCE.getLogin());
        signUpButton.click();
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(ApplicationProperties.INSTANCE.getPassword());
        signUpButton.click();
        wait.until(ExpectedConditions.visibilityOf(userAddress));
        Assert.assertEquals(userAddress.getText(), ApplicationProperties.INSTANCE.getLogin());
    }

    public void logOut(){
        logOut.click();
        wait.until(ExpectedConditions.invisibilityOf(userAddress));
    }

}

