package core;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.windows.options.WindowsOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.zaproxy.clientapi.core.ClientApi;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.windows.WindowsDriver;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import java.util.logging.Level;
import org.apache.http.params.CoreConnectionPNames;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.restassured.specification.RequestSpecification;

import static org.openqa.selenium.chrome.ChromeOptions.LOGGING_PREFS;

public class baseDriver implements apiDriver, webDriver,desktopDriver,mobileDriver
{
	public   ThreadLocal<ClientApi> ZapScanner=new ThreadLocal<ClientApi>();
	public static Process p;
	public WebDriver webinit(String browser, String BaseURL, Boolean Grid, Boolean proxyRequired) throws Exception {
		WebDriver dr = null;
		System.out.println("In Web Initiator");
//New Changes By priyanka
    if (browser == null || browser.trim().isEmpty()) {
            browser = "chrome";
        }

		if(!Grid) {
			if (browser.equalsIgnoreCase("chrome")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				ChromeOptions options = new ChromeOptions();
				if (proxyRequired == true) {
//					Proxy proxy = new Proxy();
//					proxy.setAutodetect(false);
//					proxy.setHttpProxy("localhost:8081");
//					proxy.setSslProxy("localhost:8081");
//					final String ZAP_PROXYHOST = "localhost";
//					final int ZAP_PROXYPORT = 8081;
//					final String[] policyNames = {"directory-browsing", "cross-site-scripting", "sql-injection",
//							"path-traversal", "remote-file-inclusion", "server-side-include", "script-active-scan-rules",
//							"server-side-code-injection", "external-redirect", "crlf-injection"};
//					int currentScanID;
//					if (ZapScanner.get() == null) {
//						ZapScanner.set(new ClientApi(ZAP_PROXYHOST, ZAP_PROXYPORT,"7ast5q3osjonr6f0nrk070l9fh" ));
//					}
////					c_options.add_experimental_option("debuggerAddress", "localhost:8989")
//					System.out.println("Zap Scanner" + ZapScanner);
//					options.setCapability(CapabilityType.PROXY, proxy);
//					options.addArguments("--ignore-certificate-errors");
//					options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
				}
				Map<String, Object> prefs = new HashMap<String, Object>();
//				options.addArguments("--user-data-dir=C:\\Users\\niteshd\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
//				options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

//                options.addArguments("--remote-debugging-port=9222");
				//				options.addArguments("--no-startup-window");
//				options.addArguments("--flag-switches-begin");
//				options.addArguments("--flag-switches-end");
//				options.addArguments("prefetch");
//
//				 /prefetch:5 --flag-switches-begin
				//
//				options.setExperimentalOption("debuggerAddress", "127.0.0.1:8989");
	//			options.addArguments("user-data-dir", "C:\\Users\\niteshd\\AppData\\Local\\Google\\Chrome\\User Data");
	//		options.addArguments("--user-data-dir=C:\\Users\\niteshd\\AppData\\Local\\Google\\Chrome\\User Data");
//
                //handle authentication issue
			   options.addArguments("--profile-directory=Profile 9");
				options.addArguments("--user-data-dir=D:\\Profiles");

                // Only add profile if explicitly needed
                String profileName = System.getProperty("ProfileName");
                String profilePath = System.getProperty("ProfilePath");

                if (profileName != null && profilePath != null && !profilePath.isEmpty()) {
                    File profileDir = new File(profilePath);
                    if (profileDir.exists()) {
                        System.out.println("Using Chrome profile: " + profileName + " from " + profilePath);
                        options.addArguments("--user-data-dir=" + profilePath);
                        options.addArguments("--profile-directory=" + profileName);
                    } else {
                        System.out.println("Profile directory doesn't exist, using default Chrome");
                    }
                } else {
                    System.out.println("No profile specified, using default Chrome");
                }
//				options.addArguments("profile-directory=Default");  // Use correct profile name
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
//				options.addArguments("--remote-debugging-port=9222");
//				options.addArguments("--user-data-dir=" + profilePath); // Set the user data directory
//				options.addArguments("--profile-directory=Profile 1");
				prefs.put("profile.default_content_setting_values.notifications", 2);
//				prefs.put("profile.default_content_setting_values.popups", 1);
//				prefs.put("download.default_directory", System.getProperty("user.dir") + "\\src\\Data\\Downloads");
////				prefs.put("profile.default_content_setting_values.notifications", 2);

                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
//
				options.setExperimentalOption("prefs", prefs);
//				options.addArguments("--start-maximized");
//				options.addArguments("disable-infobars");
//				options.addArguments("--disable-popup-blocking");
//				options.addArguments("--disable-dev-shm-usage");
//				options.addArguments("--disable-extensions");
//				options.addArguments("--disable-gpu");
//				options.addArguments("--no-sandbox");
//				options.addArguments("--disable-notifications");
//				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
////			options.setBinary("C:/Program Files/Google/Chrome/Application/chrome.exe");
//				capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
//				LoggingPreferences logs = new LoggingPreferences();
//				logs.enable(LogType.DRIVER, Level.ALL);
//				capabilities.setCapability(LOGGING_PREFS, logs);
//				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//				options.merge(capabilities);
//				System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
//				WebDriverManager.chromedriver().setup();
//				WebDriverManager.edgedriver().setup();
//				options.addArguments("--remote-allow-origins=*");

				try {
//				WebDriverManager.chromedriver().setup();
//					if("Sel")
					dr = new ChromeDriver(options);
//					else
//					{Playwrite code}



				}
				catch (WebDriverException e)
				{
					System.out.println(e.getAdditionalInformation());
					System.out.println(e.getMessage());
					System.out.println(e.getRawMessage());
				}
				dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				dr.manage().deleteAllCookies();
				dr.manage().window().maximize();
//				dr.get("https://prioritytest.liveu.tv/#");
//				new WebDriverWait(dr, Duration.ofSeconds(10))
//						.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
////				((JavascriptExecutor) dr).executeScript("window.open('');");
//				System.out.println("New tab opened");

				// Switch to the newly opened tab
//				for (String handle : dr.getWindowHandles()) {
//					dr.switchTo().window(handle);
//				}
//				System.out.println("Switched to new tab");

				dr.get(BaseURL);
//				dr.switchTo().defaultContent();
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
//				WebDriverManager.edgedriver().setup();
                dr = new EdgeDriver();
				dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				dr.manage().deleteAllCookies();
				dr.manage().window().maximize();
//				dr.get(BaseURL);
			}
			else {
				System.out.println("For IE Browser");
			}

		}
		
	  return dr;
	}

	public WebDriver webinit(String browser, String BaseURL, Boolean Grid, Boolean proxyRequired,Boolean headless) throws Exception {
		WebDriver dr = null;
		System.out.println("In Web Initiator");

    if (browser == null || browser.trim().isEmpty()) {
            browser = "chrome";
        }


		if(!Grid) {
			if(browser.equalsIgnoreCase("chrome"))
			{
	//			WebDriverManager.chromedriver().setup();
				//System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
				System.setProperty("logback.configurationFile", System.getProperty("user.dir") + "/logback.xml");
				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.setBinary("C:/Program Files/Google/Chrome/Application/chrome.exe");
                 System.out.println("In Headless Browser mode init methode" );
				chromeOptions.addArguments(Arrays.asList("--log-level=OFF", "--silent", "--headless=true"));
				dr = new ChromeDriver(chromeOptions);
				dr.manage().window().fullscreen();
			}
			else
			{
				System.out.println("For IE Browser");
			}


		}

		return dr;
	}

	public WindowsDriver desktopinit(String Application) throws Exception {

		WindowsDriver desktopSession = null;
		WindowsOptions capabilities = new WindowsOptions();
		capabilities.setApp(Application);
		capabilities.setCapability("app",Application);
		capabilities.setPlatformName("Windows");
		capabilities.setCapability("deviceName", "WindowsPC");
		capabilities.setWaitForAppLaunch(Duration.ofSeconds(20));
		capabilities.setExperimentalWebDriver(false);
		desktopSession = new WindowsDriver(new URL("http://127.0.0.1:4783"), capabilities);
		Thread.sleep(10000);
		desktopSession.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		return desktopSession;
	}

	public AppiumDriver mobileinit(String Device, String appPackage,String AppActivity,String Platform,String Application) throws Exception {

		AppiumDriver mobileDriver;
//		DesiredCapabilities cap = new DesiredCapabilities();
		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setPlatformName(Platform);
		cap.setDeviceName(Device);
		cap.setNewCommandTimeout(Duration.ofSeconds(60));
		cap.setAutomationName("UiAutomator2");
		cap.setPlatformVersion("14");
		cap.setNoReset(false);
		cap.setCapability("appium:autoLaunch",true);
		cap.setCapability("appium:autoGrantPermissions",true);
		cap.setAppWaitActivity(AppActivity);
		if(!Application.equalsIgnoreCase("")) {
			cap.setApp(Application);
		}
		else
		{
			cap.setAppPackage(appPackage);
			cap.setAppActivity(AppActivity);
		}

		mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"),cap);
		mobileDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		((InteractsWithApps) mobileDriver).activateApp("com.nimbuspost.app");

		return mobileDriver;
	}
	public RequestSpecification apiinit(String baseurl) {
		// TODO Auto-generated method stub
		RequestSpecification httpRequest;
//		System.out.println("In API Initiator");
//		RestAssured.baseURI = baseurl;
		@SuppressWarnings("deprecation")
		RestAssuredConfig config = RestAssured.config()
		        .httpClient(HttpClientConfig.httpClientConfig()
		                .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 100000)
		                .setParam(CoreConnectionPNames.SO_TIMEOUT, 100000));
		//System.out.println(RestAssured.baseURI);
		httpRequest = RestAssured.given().config(config);
		return httpRequest;
	}

	public ClientApi ReturnZapScanner() {
		System.out.println("Zap Scanner Return method"+ZapScanner.get());
		return ZapScanner.get();
	}

	public Process ReturncurrentProcess() {
		System.out.println("Zap Scanner Return method"+ZapScanner);
		return p;
	}



}