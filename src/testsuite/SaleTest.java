package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SaleTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyTheTotalItemsDisplayedOnTheWomensJacketsPage(){
        WebElement clickSale = driver.findElement(By.id("ui-id-8"));
        clickSale.click();
        driver.findElement(By.linkText("Jackets")).click();
        String expectedText = "Jackets";
        String actualText = driver.findElement(By.xpath("//span[@class='base']")).getText();
        Assert.assertEquals("Text is not displayed", expectedText, actualText);

        List<WebElement> items = new ArrayList<>();
        int actualItemCount = items.size();
        System.out.println("Total items displayed on page: " + actualItemCount);
        System.out.println("Name of all items on page:");
        List<WebElement> count = driver.findElements(By.xpath("//Strong[@class='product name product-item-name']"));
        int totalNoOfProductsOnPage = count.size();
        System.out.println("Total No of products displayed on page:" + totalNoOfProductsOnPage);
        for (WebElement products : count) {
            System.out.println("product :" + products.getText());

        }
        int expectedProductOnPage = 12;
        Assert.assertEquals("12 Products are displayed", expectedProductOnPage, totalNoOfProductsOnPage);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
    }
