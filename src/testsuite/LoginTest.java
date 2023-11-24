package testsuite;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test


    public void userShouldLoginSuccessfullyWithValidCredentials() {

        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        WebElement userEmail = driver.findElement(By.id("email"));
        userEmail.sendKeys("monal1289@yahoo.com");
        WebElement userPassword = driver.findElement(By.id("pass"));
        userPassword.sendKeys("Password123");
        WebElement userSignIn = driver.findElement(By.id("send2"));
        userSignIn.click();

        //Assert.assertEquals(driver.getTitle(), "Welcome");
        //Assert.assertTrue(driver.findElement(By.cssSelector("//li//span[@class='logged-in']")).isDisplayed());
    }

    @Test
    public void verifyTheErrorMessageWithInvalidCredentials() {
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        WebElement userEmail = driver.findElement(By.id("email"));
        userEmail.sendKeys("xyz@yahoo.com");
        WebElement userPassword = driver.findElement(By.id("pass"));
        userPassword.sendKeys("Pas");
        WebElement userSignIn = driver.findElement(By.id("send2"));
        userSignIn.click();

        String expectedText = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualText = driver.findElement(By.xpath("//div[text()='The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.']")).getText();
        Assert.assertEquals("Message is not displayed", expectedText, actualText);
    }
    @Test
    public void userShouldLogOutSuccessfully(){
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        WebElement userEmail = driver.findElement(By.id("email"));
        userEmail.sendKeys("monal1289@yahoo.com");
        WebElement userPassword = driver.findElement(By.id("pass"));
        userPassword.sendKeys("Password123");
        WebElement userSignIn = driver.findElement(By.id("send2"));
        userSignIn.click();

        WebElement downAero = driver.findElement(By.xpath("//span[@class='customer-name']"));
        downAero.click();

        WebElement signOut = driver.findElement(By.linkText("Sign Out"));
        signOut.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement signOutMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-title")));
        assert signOutMessage.getText().equals("You are signed out");
    }
    public void tearDown(){
        driver.quit();
    }

}


