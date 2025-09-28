public final class AppConfig {
    private static volatile AppConfig instance;
    private final String appName;
    private AppConfig(String appName) { this.appName = appName; }
    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) instance = new AppConfig("PatternDemoApp");
            }
        }
        return instance;
    }
    public String getAppName() { return appName; }
}