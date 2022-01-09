package com.qa.linkedin.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver=null;
	public WebDriverWait wait=null;
	private static Logger log=Logger.getLogger(TestBase.class);

	/**
	 * read the propery value based on given key
	 * @throws IOException 
	 */
		public String readProperty(String key) throws IOException {
		log.debug("Create an object for Properties class...");
		Properties prop=new Properties();
		log.debug("read the properties file");
		try {
			FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties"));
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop.getProperty(key);	
			
		}
		
	@BeforeSuite
	public void setup() throws IOException {
		log.info("Launching the browser...");
		String browserName=readProperty("browser");
	log.info("we are launching the browser:"+browserName);

	if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	}else if(browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
	}else if(browserName.equalsIgnoreCase("edge")) {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
	}else if(browserName.equalsIgnoreCase("safari")) {
		WebDriverManager.safaridriver().setup();
		driver=new SafariDriver();
	}
	log.debug("maxmize the window");
	driver.manage().window().maximize();
	log.debug("Open the application url:"+readProperty("applicationUrl"));
	driver.get(readProperty("applicationUrl"));
	log.debug("set the implicitwait time");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	log.debug("set explicit wait");
	wait=new WebDriverWait(driver,Duration.ofSeconds(30));

	}

	@AfterSuite
	public void tearDown() {
		
		try {
			log.debug("quitting the webdriver");
		}finally {
		if(driver!=null) {
			driver.quit();
		}
	}
	}

}
