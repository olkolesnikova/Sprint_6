package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {

    private final WebDriver driver;

    //Поле когда привезти
    private final By dateOfRent = By.cssSelector("input[placeholder='* Когда привезти самокат']");

    //Поле Срок аренды
    private final By periodOfRent = By.className("Dropdown-placeholder");

    //Поле Цвет черный
    private final By colorBlack = By.id("black");

    //Поле Цвет серый
    private final By colorGrey = By.id("grey");

    //Поле Комментарий
    private final By comment = By.cssSelector("input[placeholder='Комментарий для курьера']");

    //Кнопка Заказать

    private final By buttonOrder = By.xpath(
            "//button[normalize-space(.)='Заказать' and contains(@class,'Button_Middle')]"
    );

    //Кнопка Да
    private final By buttonConfirmOrder = By.xpath("//button[normalize-space(.)='Да']");

    private final By orderModal = By.cssSelector("div[class*='Order_Modal']");


    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFieldsOnRentPage() {

        WebElement dateOfRent = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='* Когда привезти самокат']")));
        dateOfRent.click();

        WebElement date = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='10']")));
        date.click();

        driver.findElement(periodOfRent).click();
        WebElement period = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'Dropdown-option') and text()='сутки']")));

        period.click();

        driver.findElement(colorBlack).click();

        driver.findElement(comment).click();
        driver.findElement(comment).sendKeys("Позвонить заранее");
    }

    public void clickOnButtonOrder() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(buttonOrder));

        button.click();
    }

    public void clickOnButtonConfirmOrder() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buttonYes = wait.until(ExpectedConditions.elementToBeClickable(buttonConfirmOrder));
        buttonYes.click();

    }
}
