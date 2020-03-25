package com.seleniumtest.qa.base;

import com.seleniumtest.qa.utils.ApplicationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

public class BaseTest {

    public static WebDriver driver;

    private static String chromeDriverPath = ApplicationProperties.INSTANCE.getChromeDriver();

    public void initialization() {

        String downloadFilepath = ApplicationProperties.INSTANCE.getCurrentTestEmailFolder();

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver(cap);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(ApplicationProperties.INSTANCE.getUrl());
    }

    public void quitDriver() {
        driver.quit();
    }

}
