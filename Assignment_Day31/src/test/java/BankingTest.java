import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BankingTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait
        System.out.println("Browser sudah dibuka");
    }

    @Test
    public void testDeposit() {
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/");
        System.out.println("Buka website Banking Project");

        // klik customer login
        WebElement customerLoginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Customer Login')]")));
        customerLoginBtn.click();
        System.out.println("Klik tombol Customer Login");

        // pilih nama dari dropdown
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSelect")));
        Select pilihNama = new Select(dropdown);
        pilihNama.selectByVisibleText("Harry Potter");
        System.out.println("Pilih nama Harry Potter");

        // klik login
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Login']")));
        loginBtn.click();
        System.out.println("Klik tombol Login");

        // klik deposit menu
        WebElement depositMenuBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@ng-class='btnClass2']")));
        depositMenuBtn.click();
        System.out.println("Klik tombol Deposit");

        // input jumlah deposit
        WebElement inputAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@ng-model='amount']")));
        inputAmount.sendKeys("1000");
        System.out.println("Input jumlah deposit 1000");

        // klik tombol deposit submit
        WebElement depositBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Deposit']")));
        depositBtn.click();
        System.out.println("Klik submit deposit");

        // cek pesan sukses
        WebElement pesanElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@ng-show='message']")));
        String pesan = pesanElement.getText();
        Assert.assertEquals(pesan, "Deposit Successful");
        System.out.println("Pesan sukses: " + pesan);

        // cek balance
        WebElement balanceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//strong[2]")));
        String balance = balanceElement.getText();
        System.out.println("Balance sekarang: " + balance);
        Assert.assertEquals(balance, "1000");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Browser ditutup");
    }
}