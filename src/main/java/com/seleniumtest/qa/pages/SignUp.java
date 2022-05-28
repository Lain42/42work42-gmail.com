package com.seleniumtest.qa.pages;

import com.seleniumtest.qa.base.BaseTest;
import com.seleniumtest.qa.utils.ApplicationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class SignUp extends BaseTest {
    @FindBy(xpath = "//iframe[@class='ag-popup__frame__layout__iframe']")
    protected WebElement loginFrame;
    @FindBy(xpath = "//input[@name='username']")
    protected WebElement login;
    @FindBy(xpath = "//input[@name='password']")
    protected WebElement password;
    @FindBy(xpath = "//button[@data-test-id='submit-button']")
    protected WebElement signUpButton;
    @FindBy(xpath = "//button[@data-test-id='next-button']")
    protected WebElement nextButton;
    @FindBy(xpath = "//button[@data-testid='enter-mail-primary']")
    protected WebElement signUpPrimaryButton;
    @FindBy(xpath = "//span[@class='ph-project__user-name svelte-1hiqrvn']")
    protected WebElement userAddress;
    @FindBy(xpath="//*[text()='Выйти']")
    protected WebElement logOut;
    public SignUp() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 25), this);
    }
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    public void signUp() {
        signUpPrimaryButton.click();
        wait.until(ExpectedConditions.visibilityOf(loginFrame));
        driver.switchTo().frame(loginFrame);
        wait.until(ExpectedConditions.visibilityOf(login));
        login.sendKeys(ApplicationProperties.INSTANCE.getLogin());
        nextButton.click();
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(ApplicationProperties.INSTANCE.getPassword());
        signUpButton.click();
        wait.until(ExpectedConditions.visibilityOf(userAddress));
        Assert.assertEquals(userAddress.getText(), ApplicationProperties.INSTANCE.getLogin());
    }

    public void logOut(){
        userAddress.click();
        logOut.click();
        wait.until(ExpectedConditions.invisibilityOf(userAddress));
    }
}

