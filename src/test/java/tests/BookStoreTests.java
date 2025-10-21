package tests;

import io.qameta.allure.Story;
import dto.AddBookRequest;
import dto.AuthData;
import dto.DeleteBookRequest;
import dto.Isbn;
import dto.LoginResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BookStoreSpecs.getResponseSpec;
import static specs.BookStoreSpecs.requestSpec;
import static steps.ApiSteps.addBookToBookListIfNotPresent;
import static steps.ApiSteps.deleteBookFromBookList;

@Story("Профиль пользователя")
public class BookStoreTests extends BaseTest {

    @Test
    @DisplayName("Удаление книги из списка")
    public void deleteBookFromBookListTest() {
        ProfilePage profilePage = new ProfilePage();
        AuthData authData = new AuthData("JohnDoe", "John12345!");
        Isbn isbn = new Isbn("9781449365035");

        LoginResponse loginResponse = step("Залогиниться под пользователем", () -> given(requestSpec)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(getResponseSpec(200))
                .extract().as(LoginResponse.class)
        );
        AddBookRequest addBookRequest = new AddBookRequest(loginResponse.getUserId(), List.of(isbn));
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(loginResponse.getUserId(), isbn.getIsbn());

        addBookToBookListIfNotPresent(loginResponse.getUserId(), loginResponse.getToken(),
                isbn.getIsbn(), addBookRequest);
        deleteBookFromBookList(loginResponse.getToken(), deleteBookRequest);

        profilePage.openProfilePage(loginResponse.getUserId(), loginResponse.getUsername(),
                loginResponse.getToken(), loginResponse.getExpires());
        profilePage.checkBookListIsEmpty();

    }
}
