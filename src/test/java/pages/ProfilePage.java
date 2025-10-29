package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    private final SelenideElement userName = $("#userName-value");
    private final SelenideElement noDataTitle = $(".rt-noData");
    private final SelenideElement deleteAllBooksButton = $(byText("Delete All Books"));
    private final SelenideElement modalWindow = $(".modal-content");
    private final SelenideElement okButton = $("#closeSmallModal-ok");

    private final ElementsCollection bookTitles = $$("[id*='see-book']");

    @Step("Открыть страницу профиля")
    public ProfilePage openProfilePage(String userId, String userName, String token, String expires) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("userName", userName));
        getWebDriver().manage().addCookie(new Cookie("token", token));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        open("/profile");
        this.userName.shouldBe(visible).shouldHave(text(userName));
        return this;
    }

    @Step("Проверить, что книга присутсвует вв списке")
    public ProfilePage checkBookIsPresent(String book) {
        this.bookTitles.findBy(text(book)).shouldBe(visible);
        return this;
    }

    @Step("Удалить книгу из списка")
    public ProfilePage deleteBookFromList(String book) {
        this.deleteAllBooksButton.shouldBe(visible).click();
        this.modalWindow.shouldBe(visible);
        this.okButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что список книг пустой")
    public ProfilePage checkBookListIsEmpty() {
        noDataTitle.shouldBe(visible).shouldHave(text("No rows found"));
        return this;
    }
}
