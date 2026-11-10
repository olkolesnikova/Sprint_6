package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    WebDriver driver;

    //Поле Имя
    private final By name = By.cssSelector("input[placeholder='* Имя']");

    //Поле Фамилия
    private final By surname = By.cssSelector("input[placeholder='* Фамилия']");

    //Поле Адрес
    private final By adress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");

    //Поле Станция Метро
    private final By metroStation = By.cssSelector("input[placeholder='* Станция метро']");

    //Поле телефон
    private final By phoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее
    private final By buttonNext = By.xpath("//button[text()='Далее']");

    //Модальное окно об успешном заказе
    private final By orderSuccessModal = By.xpath("//*[contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectMetroStation(String metroName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(metroStation)).click();

        WebElement station = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[contains(@class,'select-search__select')]//*[normalize-space(text())='" + metroName + "']")
                )
        );
        station.click();

    }

    public void setFieldsInOrderForm(String firstName, String lastname, String address, String metro, String phone) {

        driver.findElement(name).sendKeys(firstName);

        driver.findElement(surname).sendKeys(lastname);

        driver.findElement(adress).sendKeys(address);

        selectMetroStation(metro);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(phoneNumber)).sendKeys(phone);

        }

    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    public boolean isOrderSuccessModalDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessModal));
        return modalWindow.isDisplayed();
    }
}
