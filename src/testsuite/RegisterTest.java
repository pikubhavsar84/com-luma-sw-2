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

public class RegisterTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyThatSignInPageDisplay() {

        WebElement crateNewPage = driver.findElement(By.xpath("//a[contains(text(),'Create an Account')]"));
        crateNewPage.click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void userSholdRegisterAccountSuccessfully() {

        WebElement crateNewPage = driver.findElement(By.linkText("Create an Account"));
        crateNewPage.click();

        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("mona");
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("lisa");
        // WebElement checkBox = driver.findElement(By.id("isNewsletterSubscriber"));
        //checkBox.click();
        WebElement userEmail = driver.findElement(By.name("email"));
        userEmail.sendKeys("monal3298@yahoo.com");
        WebElement userPassword = driver.findElement(By.id("password"));
        userPassword.sendKeys("Password123");
        WebElement userConfirmPassword = driver.findElement(By.id("password-confirmation"));
        userConfirmPassword.sendKeys("Password123");
        WebElement submit = driver.findElement(By.xpath("//button[@title='Create an Account']"));
        submit.click();

        String expectedText = "Thank you for registering with Main Website Store.";
        String actualText = driver.findElement(By.xpath("//div[@class='message-success success message']")).getText();//Find actual text and get it with get method
        Assert.assertEquals("User is not on main website store", expectedText, actualText);// Checking Actual text with Expected text

        driver.findElement(By.xpath("//span[@class='customer-name']")).click();//Find down aero and click on it
        driver.findElement(By.linkText("Sign Out")).click();//Find Sign out link and click on it.
        // Verify the 'You are signed out' text
        WebDriverWait web = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement signOutMessage = web.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-title")));
        assert signOutMessage.getText().equals("You are signed out");
    }

    public void tearDown() {
        driver.close();
    }
}

