package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    private final SelenideElement noDataTitle = $(".rt-noData");

    @Step("Открыть страницу профиля")
    public void openProfilePage(String userId, String userName, String token, String expires) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("userName", userName));
        getWebDriver().manage().addCookie(new Cookie("token", token));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        open("/profile");
    }

    @Step("Проверить, что список книг пустой")
    public void checkBookListIsEmpty() {
        noDataTitle.shouldBe(visible).shouldHave(text("No rows found"));
    }
}
