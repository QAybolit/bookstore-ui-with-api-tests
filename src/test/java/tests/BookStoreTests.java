package tests;

import helpers.extensions.WithLogin;
import io.qameta.allure.Story;
import dto.AddBookRequest;
import dto.Isbn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.List;

import static steps.ApiSteps.addBookToBookListIfNotPresent;

@Story("Профиль пользователя")
public class BookStoreTests extends BaseTest {

    @Test
    @WithLogin
    @DisplayName("Удаление книги из списка")
    public void deleteBookFromBookListTest() {
        Isbn isbn = new Isbn("9781449365035");
        String bookTitle = "Speaking JavaScript";

        ProfilePage profilePage = new ProfilePage();
        AddBookRequest addBookRequest = new AddBookRequest(loginResponse.getUserId(), List.of(isbn));

        addBookToBookListIfNotPresent(loginResponse.getUserId(), loginResponse.getToken(), isbn.getIsbn(), addBookRequest);
        profilePage.openProfilePage(loginResponse.getUserId(), loginResponse.getUsername(),
                        loginResponse.getToken(), loginResponse.getExpires())
                .closeBanners()
                .checkBookIsPresent(bookTitle)
                .deleteAllBooksFromList(bookTitle)
                .checkBookListIsEmpty();
    }
}