package pageHelper;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import core.*;
import io.cucumber.java.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.plugin.event.*;
import io.github.sridharbandi.Accessibility;
import io.github.sridharbandi.AccessibilityRunner;
import io.github.sridharbandi.util.Standard;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.openqa.selenium.*;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;
import org.zaproxy.clientapi.core.ClientApi;
import utils.*;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.xml.DOMConfigurator;

//import cucumber.Reporter;


import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.commons.io.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class bddDriver {
	int TotalTest = 0;
	int TotalFailed = 0;
	public static int itr;
	public static boolean Accessibilityflag;
	PropertyReader pr = new PropertyReader();
	public static AccessibilityRunner accessibilityRunner;
	PdfReader pd = new PdfReader();
	public static ThreadLocal<HashMap<String, String>> Variables = new InheritableThreadLocal<>();
	public static ThreadLocal<String> TestName = new InheritableThreadLocal<>();
	public static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static final ThreadLocal<RequestSpecification> API_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static final ThreadLocal<Response> API_RESPONCE_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static final ThreadLocal<String> Message = new InheritableThreadLocal<>();
	public static ThreadLocal<String> order_id = new InheritableThreadLocal<>();
	public static ThreadLocal<String> dateprovided = new InheritableThreadLocal<>();
	public static ThreadLocal<String> timestamp = new InheritableThreadLocal<>();
	public static ThreadLocal<String> OrderLatestTransactionID = new InheritableThreadLocal<>();
	public static final ThreadLocal<WindowsDriver> DESKTOP_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static final ThreadLocal<AppiumDriver> MOBILE_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static final ThreadLocal<ClientApi> ZAP_CANNER_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static ThreadLocal<String> amounttotransfer = new InheritableThreadLocal<>();
	public static ThreadLocal<String> amounttoRecieve = new InheritableThreadLocal<>();
	public static ThreadLocal<String> transferFees = new InheritableThreadLocal<>();
	public static ThreadLocal<String> OriginCurrency = new InheritableThreadLocal<>();
	public static ThreadLocal<String> recivedCurrency = new InheritableThreadLocal<>();
	public static ThreadLocal<String> userGroup = new InheritableThreadLocal<>();
	public static ThreadLocal<HashMap<String, String>> TransactionData = ThreadLocal.withInitial(HashMap::new);
	public static ThreadLocal<String> Record_id = new InheritableThreadLocal<>();
	public static ThreadLocal<String> petid=new InheritableThreadLocal<>();
	public static ThreadLocal<String> petname=new InheritableThreadLocal<>();
	public static long endTimeWebStep;
	public static long startTimeWebStep;
	public static long executionTimeWebStep;
	Date date1;
	Date date2;
    String Test;

//	@Before
//	public void startTime(Scenario s) throws IOException {
//
//        String ScenarioName = s.getName();
//        if(!propertyreader.readproperty("CurrentScenario").equalsIgnoreCase(ScenarioName))
//        {
//            propertyreader.updateproprty("CurrentScenario",ScenarioName);
//            propertyreader.updateproprty("CurrentTest","1");
//        }
//        String path = s.getUri().toString().split("/")[s.getUri().toString().split("/").length - 1];
//        int Total_test=countExamples1(System.getProperty("user.dir") + "/src/test/resources/FeatureFile/"+ path, ScenarioName);
//        Test = propertyreader.readproperty("CurrentTest")+"/"+ Total_test;
//
//        if(propertyreader.readproperty("CurrentScenario").equalsIgnoreCase(ScenarioName))
//        {
//            int number = Integer.parseInt(propertyreader.readproperty("CurrentTest"))+1;
//            propertyreader.updateproprty("CurrentTest",String.valueOf(number));
//        }
//	}


	@Before("@API")
	public void APIsetup(Scenario s) {
		apiDriver ApiDriver = new baseDriver();
		API_DRIVER_THREAD_LOCAL.set(ApiDriver.apiinit(""));
		API_RESPONCE_THREAD_LOCAL.set(null);
		date1 = new Date();

	}

	@Before("@WEB")
	public void Websetup(Scenario s) throws Exception {
		String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Browser");
//		String browser = "chrome";
//		List<Map<String, String>> examplesData = dataTable.asMaps(String.class, String.class);

		// Determine the current scenario line to pick the correct example


//		Map<String, String> currentExampleData = s.
//				// Adjust if needed
		System.out.println(s.getId());

		System.out.println(browser);
		webDriver webDriver = new baseDriver();
		boolean SecuryScan = Boolean.valueOf(pr.readproperty("SecurityScan" + browser));
		//String app = pr.readproperty("Cyncly_application_url");
        String app = pr.readproperty("xFilesPro_application_url");
		WEB_DRIVER_THREAD_LOCAL.set(webDriver.webinit(browser, app, false, SecuryScan));
//		try {
//			ZAP_CANNER_THREAD_LOCAL.set(webDriver.ReturnZapScanner());
//			System.out.println("Zap Scanner in Driver.java" + ZAP_CANNER_THREAD_LOCAL.get());
//
//			ZAP_CANNER_THREAD_LOCAL.get().spider.scan(app, null, null, null, null);
//		} catch (Exception e) {
//			System.out.println("No ZapCanner is not setup");
//
//		}
//		Accessibility.REPORT_PATH = System.getProperty("user.dir") + "/Reports/Accessibility/Report";
//
//		setcompliences();
//		System.out.println(Accessibility.REPORT_PATH + Accessibility.STANDARD);
//		accessibilityRunner = new AccessibilityRunner(WEB_DRIVER_THREAD_LOCAL.get());
//		date1 = new Date();
////		String fieldname=WEB_DRIVER_THREAD_LOCAL.get().findElement(By.xpath("//label[text()='Username']")).getAttribute("for");
////		WebElement element=WEB_DRIVER_THREAD_LOCAL.get().findElement(By.xpath("//*[@id='"+fieldname+"']"));
////		element.sendKeys("adminashwani@unite3.com");
////		fieldname=WEB_DRIVER_THREAD_LOCAL.get().findElement(By.xpath("//label[text()='Password']")).getAttribute("for");
////		element=WEB_DRIVER_THREAD_LOCAL.get().findElement(By.xpath("//*[@id='"+fieldname+"']"));
////		element.sendKeys("Admin@123456");
////		WEB_DRIVER_THREAD_LOCAL.get().findElement(By.xpath("//*[@value='Log In']")).click();
////		//*[@name='New']
//
//		//label[text()='Phone']
//		// Enter User name
//		// Enter Password
//		// Click on Login
	}

	public void setcompliences() throws IOException {
		System.out.println("In Accessibilty Test for:" + pr.readproperty("Accessibility_Comp"));
		switch (pr.readproperty("Accessibility_Comp")) {
			case "WCAG2.0A": {
				Accessibility.STANDARD = Standard.WCAG2A;
				break;
			}
			case "WCAG2AA": {
				Accessibility.STANDARD = Standard.WCAG2AA;
				break;
			}
			case "Section508": {
				Accessibility.STANDARD = Standard.Section508;
				break;
			}
			case "WCAG2AAA":
			default: {
				Accessibility.STANDARD = Standard.WCAG2AAA;
				break;
			}
		}
	}

	@Before("@DESKTOP")
	public void Desktopsetup(Scenario s) throws Exception {

		System.out.println("Desktop Before method");
		String app = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("App");
		System.out.println(app);
		desktopDriver desktopDriver = new baseDriver();
		DESKTOP_DRIVER_THREAD_LOCAL.set(desktopDriver.desktopinit(app));

	}

	@Before("@MOBILE")
	public void Mobilesetup(Scenario s) throws Exception {


		mobileDriver mobileDriver = new baseDriver();
		String app = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Mapp");
		String apppath = System.getProperty("user.dir") + "/lib/" + app;
		System.out.println("APK Path"+apppath);
		String device = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("device");
		String version = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("version");
		String Automator = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Automator");
		String appPackege = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("appPackage");
		String appActivity = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("appActivity");
//
//		MOBILE_DRIVER_THREAD_LOCAL.set(mobileDriver.mobileinit(device,"","","Android",""));
        MOBILE_DRIVER_THREAD_LOCAL.set(mobileDriver.mobileinit(device, appPackege, appActivity, "Android", apppath));
		Thread.sleep(5000);
	}

//			@AfterStep()
//			public void stepsteardown() throws IOException{
//				{
//
//					//System.out.println("Step Status"+ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(sourcePath));
//					//if (ExtentCucumberAdapter.getCurrentStep().getStatus().toLower().equals("failed")) {
//						try {
//							String sourcePath = "data:image/png;base64," + ((TakesScreenshot) WEB_DRIVER_THREAD_LOCAL.get()).
//									getScreenshotAs(OutputType.BASE64);
//							ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(sourcePath);
//						} catch (Exception e) {
//							ExtentCucumberAdapter.getCurrentStep().info("Screenshot is not required");
//						}
//					//}
//				}
//
//			}



	String StepName;
	String classname;
	String MethodName;

	String ExceptionName;
	public String getErrors(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
		String message = null;
		Field delegate = scenario.getClass().getDeclaredField("delegate");
		delegate.setAccessible(true);

		TestCaseState tcs = (TestCaseState) delegate.get(scenario);

		Field stepResults = tcs.getClass().getDeclaredField("stepResults");
		stepResults.setAccessible(true);

		ArrayList<Result> results = (ArrayList<Result>) stepResults.get(tcs);
		for (Result result : results) {
			if (result.getError() != null) {
				message = result.getError().getMessage();
				ExceptionName=result.getError().toString().split(":")[0];
				ExceptionName=ExceptionName.split("\\.")[ExceptionName.split("\\.").length-1];
			}
		}

		return message;
	}
//	@AfterStep
//	public void stepName() {
//		StepName = StepDetails.stepName;
//		String ClassTest = StepDetails.ClassName;
//		MethodName = ClassTest.split("\\.")[ClassTest.split("\\.").length-1];
//		classname = ClassTest.split("\\.")[ClassTest.split("\\.").length-2];
////		System.out.println("man fature file:"+StepDetails.FeatureFile);
//	}

@AfterStep
public void afterStep()
{
	endTimeWebStep = System.currentTimeMillis();
	executionTimeWebStep = endTimeWebStep - startTimeWebStep;
	ExtentCucumberAdapter.getCurrentStep().info("Total step time taken: <b>" + convertMillisecondsToSeconds(executionTimeWebStep) + " Sec.</b>");
}
	public static double convertMillisecondsToSeconds(long milliseconds) {
		return milliseconds / 1000.0;
	}
	@BeforeStep
	public void beforeStep()
	{
		startTimeWebStep = System.currentTimeMillis();
	}
	@After("@WEB")
	public void TearDown(Scenario s) throws IOException, NoSuchFieldException, IllegalAccessException {

		date2 = new Date();
		String TestCaseID = s.getSourceTagNames().iterator().next();
		String sourcePath = "data:image/png;base64," + ((TakesScreenshot) WEB_DRIVER_THREAD_LOCAL.get()).
				getScreenshotAs(OutputType.BASE64);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(sourcePath).build());
		File scrFile = ((TakesScreenshot)WEB_DRIVER_THREAD_LOCAL.get()).getScreenshotAs(OutputType.FILE);
		Reportlog("Current Step Screenshot - "+s, scrFile);

		if (s.isFailed()) {
//			String ErrorMessage= getErrors(s);
//			String Defect = DataReader.ReadDefectID(classname,MethodName,ExceptionName);
//			String sourcePath = "data:image/png;base64," + ((TakesScreenshot) WEB_DRIVER_THREAD_LOCAL.get()).
//					getScreenshotAs(OutputType.BASE64);
//			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(sourcePath).build());
//			File scrFile = ((TakesScreenshot)WEB_DRIVER_THREAD_LOCAL.get()).getScreenshotAs(OutputType.FILE);
//			Reportlog("Failed Scenario - "+s, scrFile);
			//			PdfReader.onFailedTest(getTestDetails(s), ErrorMessage,Defect);
//			GraphUI.onFailureTest(getTestDetails(s));
//			DataReader.WriteExcelData(classname,MethodName,ExceptionName);
//			String[] parts = getTestDetails(s).split("@");
//			ExtentCucumberAdapter.getCurrentStep().info("Scenarios "+parts[0] +" has been failed with Steps "+parts[3]+" on "+parts[4]+" Page with Reason "+ErrorMessage+" and total time taken as "+parts[2]+" seconds.");

		} else {
//			PdfReader.onSuccessTest(getTestDetails(s));
//			GraphUI.onSuccessTest(getTestDetails(s));
		}
		if (!(ZAP_CANNER_THREAD_LOCAL.get() == null)) {
			String FILEPATH = "Reports/Security_Report.html";
			File file = new File(FILEPATH);
			try {

				// Initialize a pointer
				// in file using OutputStream
				OutputStream
						os
						= new FileOutputStream(file);

				// Starts writing the bytes in it
				os.write(ZAP_CANNER_THREAD_LOCAL.get().core.htmlreport());
				System.out.println("Successfully"
						+ " byte inserted");

				// Close the file
				os.close();
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

//			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "<a href='Security_Report.html'>Please See the Detail of Security Scan by Clicking this link</a>");
			ZAP_CANNER_THREAD_LOCAL.set(null);
		}
		if (Accessibilityflag) {
			accessibilityRunner.generateHtmlReport();

			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "<a href='" + Accessibility.REPORT_PATH + "/report/html/index.html'>Please See the Detail of Accessibility Scan by Clicking this link</a>");

		}
		Accessibilityflag = false;
		System.out.println("TestCaseID is" + s.getId());

		WEB_DRIVER_THREAD_LOCAL.get().close();
//		WEB_DRIVER_THREAD_LOCAL.get().quit();

	}

	@After("@API")
	public void APITearDown(Scenario s) throws IOException, NoSuchFieldException, IllegalAccessException {
		date2 = new Date();
		PropertyReader prpertyreader = new PropertyReader();
		if (!prpertyreader.readproperty("ENV").trim().equalsIgnoreCase("LOCAL")) {
			try {
				String TestCaseID = s.getId();
				System.out.println("TestCaseID is" + TestCaseID);
				TestCaseID = TestCaseID.split(";")[1];
				System.out.println("TestCaseID is" + TestCaseID);
				String[] TestSrings = TestCaseID.split("-");
				TestCaseID = TestSrings[TestSrings.length - 1];
				System.out.println("TestCaseID is" + TestCaseID);


			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("=> Test Result is not updated in the rally " + e.getMessage());
			}
		}
		if (s.isFailed()) {
//			String ErrorMessage= getErrors(s);
//			String Defect = DataReader.ReadDefectID(classname,MethodName,ExceptionName);
//			PdfReader.onFailedTest(getTestDetails(s),ErrorMessage,Defect);
//			GraphUI.onFailureTest(getTestDetails(s));
//			DataReader.WriteExcelData(classname,MethodName,ExceptionName);

//			String[] parts = getTestDetails(s).split("@");
//			ExtentCucumberAdapter.getCurrentStep().info("Scenarios "+parts[0] +" has been failed with Steps "+parts[3]+" on "+parts[4]+" Page with Reason "+ErrorMessage+" and total time taken as "+parts[2]+" seconds.");


		} else {
//			PdfReader.onSuccessTest(getTestDetails(s));
//			GraphUI.onSuccessTest(getTestDetails(s));
		}
	}

	@After("@DESKTOP")
	public void DesktopTearDown(Scenario s) throws IOException {
		String TestCaseID = s.getSourceTagNames().iterator().next();
		//s.
		System.out.println("TestCaseID is" + s.getId());
		if (s.isFailed()) {
			String sourcePath = "data:image/png;base64," + ((TakesScreenshot) DESKTOP_DRIVER_THREAD_LOCAL.get()).
					getScreenshotAs(OutputType.BASE64);
            //		Reporter.addScreenCaptureFromPath(sourcePath);
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(sourcePath).build());

			Rally_Updator rlu = new Rally_Updator();
			rlu.Create("QABuild", "413450412168", "Fail");
		} else {
			Rally_Updator rlu = new Rally_Updator();
			rlu.Create("QABuild", "413450412168", "Pass");
		}
		DESKTOP_DRIVER_THREAD_LOCAL.get().quit();

	}

	@After("@MOBILE")
	public void MobileTearDown(Scenario s) throws IOException {
		String TestCaseID = s.getSourceTagNames().iterator().next();
		//s.
		byte[] screenshot =  ((TakesScreenshot) MOBILE_DRIVER_THREAD_LOCAL.get()).getScreenshotAs(OutputType.BYTES);
		File screenshotFile = File.createTempFile("screenshot_", ".png");
		try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
			fos.write(screenshot);
		}
		ReportPortal.emitLog(
				"Screenshot For :" + s.getName(),
				"ERROR",
				new Date(),
				screenshotFile
		);
//		System.out.println("TestCaseID is" + s.getId());
		if (s.isFailed()) {
			String sourcePath = "data:image/png;base64," + ((TakesScreenshot) MOBILE_DRIVER_THREAD_LOCAL.get()).
					getScreenshotAs(OutputType.BASE64);
            ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(sourcePath).build());

			//		Reporter.addScreenCaptureFromPath(sourcePath);
			Rally_Updator rlu = new Rally_Updator();
			rlu.Create("QABuild", "413450412168", "Fail");
		} else {
			Rally_Updator rlu = new Rally_Updator();
			rlu.Create("QABuild", "413450412168", "Pass");
		}
//		MOBILE_DRIVER_THREAD_LOCAL.get().removeApp("com.sigue.siguepay.UAT");

		MOBILE_DRIVER_THREAD_LOCAL.get().quit();
		//MOBILE_DRIVER_THREAD_LOCAL.get().close();

	}


//	@org.testng.annotations.BeforeSuite
//	public void BeforeSuite(){
//	itr=0;
//	DOMConfigurator.configure("log4j.xml");
//	}

	@BeforeSuite
	public void BeforeSuite() {
		itr = 0;
		DOMConfigurator.configure("log4j.xml");
		PdfReader.onStartTest();
	}

	@AfterSuite
	public void AfterSuite() {
		PdfReader.onFinishTest();
	}

	public static WebDriver getWebDriver() {
		return WEB_DRIVER_THREAD_LOCAL.get();
	}

	public static WindowsDriver getDesktopDriver() {
		return DESKTOP_DRIVER_THREAD_LOCAL.get();
	}

	public static AppiumDriver getMobileDriver() {
		return MOBILE_DRIVER_THREAD_LOCAL.get();
	}

	public RequestSpecification getApiDriver() {
		return API_DRIVER_THREAD_LOCAL.get();
	}

	public void setOrderid(String orderid) {
		order_id.set(orderid);
	}

	public String getOrderid() {
		return order_id.get();
	}

	public String getcurrenttimestamp() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		timestamp.set(formatter.format(date));
		return timestamp.get();

	}

	public String getBeforetimestamp() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		System.out.println("Date = " + cal.getTime());
		timestamp.set(formatter.format(cal.getTime()));
		return timestamp.get();


	}

	public String getcurrentdate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		dateprovided.set(formatter.format(date));
		return dateprovided.get();

	}

	public String getbackdate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		System.out.println("Date = " + cal.getTime());
		dateprovided.set(formatter.format(cal.getTime()));
		return dateprovided.get();

	}

	public String RetuninspecifiedFormate(String datetobefomated, String formate) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date DateintoDB = formatter.parse(datetobefomated);
		DateFormat dDate = new SimpleDateFormat(formate);
		//formatter.format(datetobefomated.toString());
		return dDate.format(DateintoDB);

	}

	public String returnDate() {

		return dateprovided.get();

	}

	public String returnDatetimestamp() {

		return timestamp.get();

	}

	public void settransaction_id(String trid) {
		OrderLatestTransactionID.set(trid);
	}

	public String gettransaction_id() {
		return OrderLatestTransactionID.get();
	}

	public void setAmouttosend(String trid) {
		amounttotransfer.set(trid);
	}

	public String getAmouttosend() {
		return amounttotransfer.get();
	}

	public void setAmouttorecieved(String trid) {
		amounttoRecieve.set(trid);
	}

	public String getAmouttorecieved() {
		return amounttoRecieve.get();
	}

	public void setTransferFees(String trid) {
		transferFees.set(trid);
	}

	public String getTransferFees() {
		return transferFees.get();
	}

	public void setOriginCurrency(String trid) {
		OriginCurrency.set(trid);
	}

	public String getOriginCurrency() {
		return OriginCurrency.get();
	}

	public void setRecivedCurrency(String trid) {
		recivedCurrency.set(trid);
	}

	public String getRecivedCurrency() {
		return recivedCurrency.get();
	}

	public String VerifyHtmlContent(String data, String tag) throws DocumentException {

		org.jsoup.nodes.Document doc = Jsoup.parse(data);

		String link = doc.select("body").toString().replaceAll("  ", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("<br>", "").replaceAll("<body class=\"test\">", "").replaceAll("<.body>", "").trim();

//		String strLineApp = link.text().replaceAll("&"+"nbsp;", " ");
//		strLineApp = strLineApp.replaceAll(String.valueOf((char) 160), " ");
		System.out.println("Value for the tage in response is" + link);
		SAXReader xmlreader = new SAXReader();
		Document doc1 = xmlreader.read(new StringReader(link));


		doc1.selectSingleNode(tag).getText();
		System.out.println("Value for the tage in response is" + doc1.selectSingleNode(tag).getText());
		//Assert.assertTrue(doc.selectSingleNode(Node).getText().equals(Expectedvalue),"Expected the Value of <b>"+Node+"</b> Attribute value contains <b>"+Expectedvalue+"</b> but  Actual returned was <b>"+doc.selectSingleNode(Node).getText()+"</b>");


		return doc1.selectSingleNode(tag).getText();

	}

	public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
		String updatedpath = System.getProperty("user.dir") + "/src/test/resources/apiResourceTemplate/";
		try {
			SchemaFactory factory =
					SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(updatedpath + xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(updatedpath + xmlPath)));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

	@Given("^Scan the Page against Accessibility Complainces$")
	public static void Run_AccessibilityTest() throws Throwable {
		//accessibilitySniffer.runCodeSniffer(pr.getdata("Accessibility_Comp"),);
		//Thread.sleep(2000);
		Accessibilityflag = true;
		accessibilityRunner.execute();
		//or you can pass report name
		// accessibilityRunner.execute("Google");
	}

	@Given("^Running the JMeter Performance Scripts$")
	public void performance() throws Throwable {
		FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "/Reports/Performance"));
		Path path = Paths.get(System.getProperty("user.dir") + "/Reports/Performance/");
		Files.createDirectory(path);
		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.command("cmd.exe", "/c", System.getProperty("user.dir") + "/src/main/java/core/Perfomance/bin/Jmeter.bat -n -t " + System.getProperty("user.dir") + "/src/test/resources/PerformanceScripts/saksoft.jmx -l " + System.getProperty("user.dir") + "/src/test/resources/PerformanceScripts/test.jtl & " + System.getProperty("user.dir") + "/src/main/java/core/Perfomance/bin/Jmeter.bat -g " + System.getProperty("user.dir") + "/src/test/resources/PerformanceScripts/test.jtl -o " + System.getProperty("user.dir") + "/Reports/Performance");

		try {
			Process process = processBuilder.start();
			BufferedReader reader =
					new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			int exitCode = process.waitFor();
			System.out.println("\nExited with error code : " + exitCode);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Given("^Generate the Detailed Report$")
	public void performanceReportLogger() throws Throwable {
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "<a href='Performance/index.html'>Please See the Detail of Performance by Clicking this link</a>");
		//	Reporter.addStepLog("<a href='"+System.getProperty("user.dir")+"/Reports/Performance/index.html'>Please See the Detail of Performance by Clicking this link</a>");
	}

	public void Addlog(String message) throws IOException {
		//	Reporter.addStepLog(message);
		String sourcePath = "data:image/png;base64," + ((TakesScreenshot) MOBILE_DRIVER_THREAD_LOCAL.get()).
				getScreenshotAs(OutputType.BASE64);
        //	Reporter.addScreenCaptureFromPath(sourcePath);
	}


	public void setVariables(HashMap<String, String> args) {
		Variables.set(args);
	}

	public HashMap<String, String> getVariables() {
		return Variables.get();
	}

	PropertyReader propertyreader = new PropertyReader();

	public String getTestDetails(Scenario s) throws IOException {

		String ScenarioName = s.getName();
		String Time = String.valueOf(getDateDiff(date1,date2,TimeUnit.SECONDS));
		return ScenarioName + "@"+Test+ "@" + Time + "@" + StepName + "@" + classname;

	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	int count = 0;
	int linestart=0;
	int exampleLine=0;
	int lineend=0;
	public int countExamples1(String featureFilePath, String scenarioName) throws IOException {

		LineIterator it = IOUtils.lineIterator(
				new BufferedReader(new FileReader(featureFilePath)));

		if(getExampleCount(featureFilePath,scenarioName)!=0) {
			for (int lineNumber = 1; it.hasNext(); lineNumber++) {
				String line = it.next();
				if (lineNumber > getExampleCount(featureFilePath, scenarioName) && lineNumber < getEndCount(featureFilePath, scenarioName)) {
					if (line.trim().startsWith("|")) {
						count++;
					}
				}
			}
			return count - 1;
		}
		else{
			//Handling when This is not Scenario Outline
			return 1;
		}
	}


	public int getStartCount(String featureFilePath, String scenarioName) throws IOException {

		LineIterator it = IOUtils.lineIterator(
				new BufferedReader(new FileReader(featureFilePath)));

		for (int lineNumber = 1; it.hasNext(); lineNumber++) {
			String line = it.next();
			if (line.trim().startsWith("Scenario")&&line.contains(scenarioName)) {
				linestart=lineNumber;
				break;
			}
		}

		return linestart;
	}

	public int getEndCount(String featureFilePath, String scenarioName) throws IOException {

		LineIterator it = IOUtils.lineIterator(
				new BufferedReader(new FileReader(featureFilePath)));

		for (int lineNumber = 1; it.hasNext(); lineNumber++) {
			String line = it.next();
			if(lineNumber>getStartCount(featureFilePath,scenarioName))
			{
				if (line.trim().startsWith("Scenario")) {
					lineend=lineNumber;
					break;
				}
			}
		}
		return lineend;
	}

	public int getExampleCount(String featureFilePath, String scenarioName) throws IOException {

		LineIterator it = IOUtils.lineIterator(
				new BufferedReader(new FileReader(featureFilePath)));

		for (int lineNumber = 1; it.hasNext(); lineNumber++) {
			String line = it.next();
			if(lineNumber>getStartCount(featureFilePath,scenarioName) && lineNumber<getEndCount(featureFilePath,scenarioName))
			{
					if (line.trim().startsWith("Examples:")) {
						exampleLine = lineNumber;
						break;
					}
			}
		}
		return exampleLine;
	}




	public static void logPayload()
	{
		ExtentCucumberAdapter.addTestStepLog("<textarea readonly>"+baseDriverHelper.ReportPayload+"</textarea>");
	}

	public static void log(String message)
	{
		ExtentCucumberAdapter.addTestStepLog(message);
	}
	public static void Reportlog(String message, File file) {
		ReportPortal.emitLog(message, LogLevel.INFO.name(), new Date(), file);

	}

	public void SetValue(String key,String value)
	{
		HashMap<String, String> Variables = new HashMap<>();
		Variables.put(key,value);
		Variables.putAll(getVariables());
		setVariables(Variables);
	}
	public void setRecord_id(String value)
	{
		Record_id.set(value);
	}

	public String getRecord_id()
	{
		return Record_id.get();
	}


	public void setThreadLocalMapValue(String key, String value) {
		TransactionData.get().put(key, value);
	}

	// Getter method to retrieve a value by key from the HashMap
	public String getThreadLocalMapValue(String key) {
		return TransactionData.get().get(key);
	}

	public void setpetname(String name)
	{
		petname.set(name);
	}
	public void setpetid(String id)
	{
		petid.set(id);
	}

	public String getpetname()
	{
		return petname.get();
	}
	public String getpetid()
	{
		return petid.get();

	}
}

