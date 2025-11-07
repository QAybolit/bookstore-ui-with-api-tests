package config;

import org.aeonbits.owner.Config;

//@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:${env}.properties",
        "classpath:local.properties"
})
public interface ProjectConfig extends Config {

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

    @Key("isRemote")
    boolean isRemote();

    @Key("remote.url")
    String remoteUrl();

    @Key("enable.vnc")
    boolean enableVnc();

    @Key("enable.video")
    boolean enableVideo();
}
