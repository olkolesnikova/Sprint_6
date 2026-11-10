package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    //Вопрос
    private final By questions = By.className("accordion__button");

    //Ответ
    private final By answers = By.className("accordion__panel");

    //Кнопка Заказать в хэдере
    private final By orderButtonInHeader = By.className("Button_Button__ra12g");

    //Кнопка Заказать на главной странице внизу
    private final By orderButtonInMainPage = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickQuestion(int index) {
        driver.findElements(questions).get(index).click();
    }

    public String getAnswer(int index) {

        WebElement answerField = driver.findElements(answers).get(index);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(answerField));
        return answerField.getText();

    }

    public void clickOrderButtonInHeader() {
        driver.findElement(orderButtonInHeader).click();
    }

    public void clickOrderButtonInMainPage() {
        driver.findElement(orderButtonInMainPage).click();
    }
}
