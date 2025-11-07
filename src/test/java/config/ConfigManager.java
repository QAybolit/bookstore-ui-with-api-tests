package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ConfigManager {

    private final ProjectConfig projectConfig;

    public ConfigManager(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }

    public void setApiConfig() {
        RestAssured.baseURI = projectConfig.baseUrl();
    }

    public void setWebConfig() {
        Configuration.baseUrl = projectConfig.baseUrl();
        Configuration.pageLoadStrategy = projectConfig.pageLoadStrategy();
        Configuration.timeout = projectConfig.timeout();
        Configuration.browser = projectConfig.browser();
        Configuration.browserVersion = projectConfig.browserVersion();
        Configuration.browserSize = projectConfig.browserSize();

        if (projectConfig.isRemote()) {
            Configuration.remote = projectConfig.remoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", projectConfig.enableVnc(),
                    "enableVideo", projectConfig.enableVideo()
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}
