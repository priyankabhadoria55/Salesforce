package utils;

import org.testng.annotations.DataProvider;

public class CucumberDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        // Fetch dynamic data from a database, CSV, or other source
        return new Object[][] {
                { "user12", "pass12" },
                { "user22", "pass22" },
                { "user32", "pass33" },
                { "user32", "pass33" }
        };
    }
}
