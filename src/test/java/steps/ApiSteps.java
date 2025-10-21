package steps;

import dto.AddBookRequest;
import dto.DeleteBookRequest;
import dto.UserInfo;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static specs.BookStoreSpecs.getAuthRequestSpec;
import static specs.BookStoreSpecs.getResponseSpec;

public class ApiSteps {

    @Step("Получить информацию о пользователе")
    public static UserInfo getUserInfo(String userId, String token) {
        return given(getAuthRequestSpec(token))
                .when()
                .get("/Account/v1/User/" + userId)
                .then()
                .spec(getResponseSpec(200))
                .extract().as(UserInfo.class);
    }

    @Step("Добавить книгу в список книг пользователя")
    public static void addBookToBookList(String token, AddBookRequest addBookRequest) {
        given(getAuthRequestSpec(token))
                .body(addBookRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(getResponseSpec(201));
    }

    @Step("Добавить книгу в список книг пользователя, если она отсутствует")
    public static void addBookToBookListIfNotPresent(String userId, String token, String isbn, AddBookRequest addBookRequest) {
        UserInfo userInfo = getUserInfo(userId, token);
        boolean bookIsNotPresent = userInfo.getBooks().stream().noneMatch(book -> book.getIsbn().equals(isbn));
        if (userInfo.getBooks().isEmpty() || bookIsNotPresent) {
            addBookToBookList(token, addBookRequest);
        }
    }

    @Step("Удалить книгу из списка книг пользователя")
    public static void deleteBookFromBookList(String token, DeleteBookRequest deleteBookRequest) {
        given(getAuthRequestSpec(token))
                .body(deleteBookRequest)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(getResponseSpec(204));
    }
}
