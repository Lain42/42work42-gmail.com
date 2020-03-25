package tests;

import com.seleniumtest.qa.base.BaseTest;
import com.seleniumtest.qa.pages.InBox;
import com.seleniumtest.qa.pages.SignUp;
import com.seleniumtest.qa.utils.ApplicationProperties;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class CheckEmail extends BaseTest {

    @BeforeTest(alwaysRun = true)
    public void setUp() throws InterruptedException {
        File dir = new File(System.getProperty("user.dir") + ApplicationProperties.INSTANCE.getCurrentTestEmailFolder());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            file.delete();
        }
        initialization();
        SignUp loginForm = new SignUp();
        loginForm
                .signUp();
    }

    @Test
    public void checkEmailSenderAndSubject() throws Exception {
        InBox inBox = new InBox();
        inBox.searchLetter();
        inBox.checkBodyEmailInList();
        inBox.openEmail();
        inBox.checkSenderEmail();
        inBox.checkSubjectEmail();
    }

    @Test
    public void compareEmaillWithTestFile() throws IOException, InterruptedException {
        InBox inBox = new InBox();
        inBox.searchLetter();
        inBox.checkBodyEmailInList();
        inBox.openEmail();

        String url = driver.getCurrentUrl();
        URL aURL = new URL(url);

        String[] segments = aURL.getPath().split("/");
        String idStr = segments[segments.length - 1];

        driver.get("https://e.mail.ru/message/" + idStr + "/download/");
        File testFile = new File(ApplicationProperties.INSTANCE.getReferenceEmail());
        File incomingFileForTest = new File(System.getProperty("user.dir") + ApplicationProperties.INSTANCE.getCurrentTestEmailFolder() + "/Message" + idStr + ".eml");
        Thread.sleep(10000);
        boolean isTwoEqual = FileUtils.contentEquals(testFile, incomingFileForTest);
        Assert.assertEquals(isTwoEqual, true);
    }

    @AfterTest
    public void SetQuit() {
        SignUp loginForm = new SignUp();
        loginForm
                .logOut();
        quitDriver();
    }
}
