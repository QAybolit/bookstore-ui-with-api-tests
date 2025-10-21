package tests;

import helpers.extensions.WithLogin;
import io.qameta.allure.Story;
import dto.AddBookRequest;
import dto.DeleteBookRequest;
import dto.Isbn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.List;

import static steps.ApiSteps.addBookToBookListIfNotPresent;
import static steps.ApiSteps.deleteBookFromBookList;

@Story("Профиль пользователя")
public class BookStoreTests extends BaseTest {

    @Test
    @WithLogin
    @DisplayName("Удаление книги из списка")
    public void deleteBookFromBookListTest() {
        ProfilePage profilePage = new ProfilePage();
        Isbn isbn = new Isbn("9781449365035");
        AddBookRequest addBookRequest = new AddBookRequest(loginResponse.getUserId(), List.of(isbn));
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(loginResponse.getUserId(), isbn.getIsbn());

        addBookToBookListIfNotPresent(loginResponse.getUserId(), loginResponse.getToken(), isbn.getIsbn(), addBookRequest);
        deleteBookFromBookList(loginResponse.getToken(), deleteBookRequest);
        profilePage.openProfilePage(loginResponse.getUserId(), loginResponse.getUsername(), loginResponse.getToken(),
                loginResponse.getExpires());
        profilePage.checkBookListIsEmpty();

    }
}
