package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigManager;
import config.ProjectConfig;
import config.ProjectConfigReader;
import dto.LoginResponse;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    private static final ProjectConfig projectConfig = ProjectConfigReader.INSTANCE.projectConfig();
    public LoginResponse loginResponse;

    @BeforeAll
    public static void setUp() {
        ConfigManager configManager = new ConfigManager(projectConfig);
        configManager.setApiConfig();
        configManager.setWebConfig();
    }

    @BeforeEach
    public void addAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    public void setLoginResponse(LoginResponse response) {
        this.loginResponse = response;
    }
}
