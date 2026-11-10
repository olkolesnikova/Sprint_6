import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionsAnswersTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox","--headless=new","--disable-dev-shm-usage");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.education-services.ru/");
    }

    @ParameterizedTest
    @MethodSource("questionsAnswersProvider")

    void questionsAnswersTest(int index, String answer) {

        MainPage mainPage = new MainPage(driver);
        WebElement firstAnswer = driver.findElement(By.className("accordion__button"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", firstAnswer);
        mainPage.clickQuestion(index);
        assertEquals(answer, mainPage.getAnswer(index));


    }

    private static Stream<Arguments> questionsAnswersProvider() {
        return Stream.of(
                Arguments.of(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of(5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
