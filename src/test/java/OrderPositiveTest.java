import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import pages.OrderPage;
import pages.RentPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderPositiveTest {

    private WebDriver driver;

    @BeforeEach
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox","--headless=new","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.education-services.ru/");
    }

    @Test
    public void orderPositiveTestFromOrderButtonInHeader() {

        MainPage mainPage = new MainPage(driver);

        mainPage.clickOrderButtonInHeader();

        OrderPage order = new OrderPage(driver);
        order.setFieldsInOrderForm("Иван", "Иванов", "Тверская, 25", "Бульвар Рокоссовского", "+79121234567");
        order.clickButtonNext();

        //Заполнение второй страницы аренды

        RentPage rent = new RentPage(driver);
        rent.setFieldsOnRentPage();
        rent.clickOnButtonOrder();

        rent.clickOnButtonConfirmOrder();

        assertTrue(order.isOrderSuccessModalDisplayed(), "Модальное окно не появилось");
    }

    @Test
    public void orderPositiveTestFromOrderButtonInMainPage() {
        MainPage mainPage = new MainPage(driver);
        WebElement orderButton = driver.findElement(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderButton);
        mainPage.clickOrderButtonInMainPage();
        OrderPage order = new OrderPage(driver);
        order.setFieldsInOrderForm("Наталья", "Миргородская", "Ленина, 73", "Сокольники", "+79121234567");
        order.clickButtonNext();

        //Заполнение второй страницы аренды

        RentPage rent = new RentPage(driver);
        rent.setFieldsOnRentPage();
        rent.clickOnButtonOrder();
        rent.clickOnButtonConfirmOrder();
        assertTrue(order.isOrderSuccessModalDisplayed(), "Модальное окно не появилось");
    }



    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
