package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    //Вопрос

    private By questions = By.className("accordion__button");

    //Ответ

    private By answers = By.className("accordion__panel");

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
}
