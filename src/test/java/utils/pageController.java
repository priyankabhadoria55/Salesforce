//package utils;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.windows.WindowsDriver;
//import org.openqa.selenium.WebDriver;
//
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import pageHelper.web.*;
//
//
//public class pageController {
//
//	public static final ThreadLocal<homePageHelperBDD> HomePage= new InheritableThreadLocal<>();
//
//
//
////	public static final ThreadLocal<VideoAudioHelperMobile> VideoAudioMob= new InheritableThreadLocal<>();
////	public static final ThreadLocal<VideoAudioHelperDesktop> VideoAudioDesk= new InheritableThreadLocal<>();
//
//
//	public void initPage(WebDriver driver){
//		DriverController DriverControler=new DriverController();
//		DriverControler.setDriver(driver);
//		homePageHelperBDD FP= new homePageHelperBDD(DriverControler);
//		HomePage.set(FP);
//	}
//
//
//}