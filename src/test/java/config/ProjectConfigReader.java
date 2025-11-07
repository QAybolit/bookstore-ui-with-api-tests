package config;

import org.aeonbits.owner.ConfigFactory;

public enum ProjectConfigReader {
    INSTANCE;

    private static final ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    public ProjectConfig projectConfig() {
        return projectConfig;
    }

}
