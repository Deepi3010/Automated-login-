package websiteTest;
/*import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class webtesting2 
{
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testValidLogin() 
    {
        // Open the OrangeHRM demo login page
        driver.get("https://opensource-demo.orangehrmlive.com/");
        System.out.println("Opened OrangeHRM demo login page.");

        // Wait for the username input field to be visible
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        System.out.println("Username field is visible.");
        usernameField.sendKeys("Admin");

        // Wait for the password input field to be visible
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        System.out.println("Password field is visible.");
        passwordField.sendKeys("admin123");

        // Wait for the login button to be clickable
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        System.out.println("Login button is clickable.");
        loginButton.click();

        // Wait for the Dashboard header to be visible to confirm successful login
        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='oxd-main-menu-item active']")));
        System.out.println("Dashboard header is visible.");
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Login test passed.");
        
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class webtesting2 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://opensource-demo.orangehrmlive.com/");
            
            // Perform login
           WebElement usernameField=driver.findElement(By.xpath("//input[@name='username']"));
           usernameField.sendKeys("Admin");
           WebElement passwordField=driver.findElement(By.xpath("//input[@name='password']"));
           passwordField.sendKeys("admin123");
           WebElement loginButton=driver.findElement(By.xpath("//button[@type='submit']"));
           loginButton.click();
           
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setup failed: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void testNavigateSections() {
        try {
            // Navigate to Dashboard
            driver.findElement(By.xpath("//span[text()='Dashboard']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Dashboard']")).isDisplayed());

            // Navigate to Admin
            driver.findElement(By.xpath("//span[text()='Admin']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Admin']")).isDisplayed());

            // Navigate to PIM
            driver.findElement(By.xpath("//span[text()='PIM']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='PIM']")).isDisplayed());

            // Navigate to Leave
            driver.findElement(By.xpath("//span[text()='Leave']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Leave']")).isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Navigation test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testAddEmployee() {
        try {
        	driver.findElement(By.xpath("//span[text()='PIM']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='PIM']")).isDisplayed());
        
			WebElement addButtonField=driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']"));
            addButtonField.click();
            driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")).click();
      
            driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Leela");
            driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Peterson");
            driver.findElement(By.xpath("//button[text()='Save']")).click();

            // Verify the new employee's details page
            Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("value").equals("Leela"));
            Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("value").equals("Peterson"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Add employee test failed: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testAssignLeave() {
        try {
        	driver.findElement(By.xpath("//span[text()='Leave']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Leave']")).isDisplayed());
            
            driver.findElement(By.xpath("//span[text()='More ']")).click();
            driver.findElement(By.xpath("//a[text()='Assign Leave ']")).click();

            driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("Leela Peterson");
            driver.findElement(By.xpath("//div[text()='-- Select --']")).click();
            driver.findElement(By.xpath("//div[text()='US - Vacation']")).click();
           // driver.findElement(By.xpath("//select[@id='assignleave_txtLeaveType']/option[text()='Vacation US']")).click();
        //    driver.findElement(By.id("assignleave_txtFromDate")).clear();
            driver.findElement(By.xpath("(//input[@placeholder='yyyy-dd-mm'])[1]")).sendKeys("2024-06-20");
          //  driver.findElement(By.id("assignleave_txtToDate")).clear();
            driver.findElement(By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]")).sendKeys("2024-06-25");
            driver.findElement(By.xpath("//button[text()=' Assign ']")).click();

            // Verify leave assignment
            WebElement confirmationMessage = driver.findElement(By.xpath(" //button[text()=' Ok ']"));
            Assert.assertTrue(confirmationMessage.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Assign leave test failed: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void testSearchEmployee() {
        try {
        	driver.findElement(By.xpath("//span[text()='PIM']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='PIM']")).isDisplayed());
            
        //    driver.findElement(By.id("menu_pim_viewPimModule")).click();
            driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("Leela Peterson");
            driver.findElement(By.xpath("//button[text()=' Search ']")).click();

            // Verify the search result
            WebElement resultName = driver.findElement(By.xpath("//div([text()='Leela ']  "));
            Assert.assertTrue(resultName.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Search employee test failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Teardown failed: " + e.getMessage());
        }
    }
}
