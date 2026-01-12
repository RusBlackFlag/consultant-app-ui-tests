package config;

import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {

    public static final String BASE_URL;
    public static final String ADMIN_LOGIN;
    public static final String ADMIN_PASSWORD;

    public static final boolean HEADLESS;
    public static final int SLOW_MO;

    static {
        try (InputStream is = TestConfig.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (is == null) {
                throw new RuntimeException("application.properties not found");
            }

            Properties props = new Properties();
            props.load(is);

            BASE_URL = props.getProperty("base.url");
            ADMIN_LOGIN = props.getProperty("user.admin.login");
            ADMIN_PASSWORD = props.getProperty("user.admin.password");

            HEADLESS = Boolean.parseBoolean(
                    props.getProperty("browser.headless", "true")
            );

            SLOW_MO = Integer.parseInt(
                    props.getProperty("browser.slowMo", "0")
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to load test configuration", e);
        }
    }

    private TestConfig() {
    }
}

