package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:project.properties"
})
public interface ProjectConfig extends Config {

    ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class);

    @Key("base.url")
    String baseUrl();

    @Key("browser")
    String browser();

    @Key("browser.version")
    String browserVersion();

    @Key("browser.size")
    String browserSize();

    @Key("page.load.strategy")
    String pageLoadStrategy();

    @Key("timeout")
    long timeout();

    @Key("remote.url")
    String remoteUrl();

    @Key("enable.vnc")
    boolean enableVnc();

    @Key("enable.video")
    boolean enableVideo();
}
