package Selenium.lambda;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Assessment {
	
	public RemoteWebDriver driver = null;
	   String username = "s.arunprasad2005";
		String accessKey = "gOwQ98sG09yOdtSK8QaonCHPWOQqVr4QE8EcWcWJxmX78UN9h4";
		WebDriverWait wait;
		
		@BeforeTest
		@Parameters(value = { "browser", "platform" })
	    public void setUp(String browser, String platform) throws Exception {
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("platform", platform);
		     capabilities.setCapability("browserName", browser);
	        capabilities.setCapability("resolution","1024x768");
	        capabilities.setCapability("build", "First Test");
	        capabilities.setCapability("name", "Sample Test");
	        capabilities.setCapability("network", true); 
	        capabilities.setCapability("visual", true); 
	        capabilities.setCapability("video", true); 
	        capabilities.setCapability("console", true); 

	    
	        try {       
				driver= new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"), capabilities);            
	        } catch (MalformedURLException e) {
	            System.out.println("Invalid grid URL");
	        }
			
	    }

		@Test
		public void testScript() throws MalformedURLException, IOException  {
						driver.get("https://www.lambdatest.com/automation-demos/");
						wait = new WebDriverWait(driver, 5, 1000);
						
						driver.findElement(By.cssSelector(".cbtn.btn_accept_ck")).click();

						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".submitform>.applynow")));
						driver.findElement(By.id("username")).sendKeys("lambda");
						driver.findElement(By.id("password")).sendKeys("lambda123");
						driver.findElement(By.cssSelector(".submitform>.applynow")).click();
						
						wait.until(ExpectedConditions.elementToBeClickable(By.id("developer-name")));
						driver.findElement(By.id("developer-name")).sendKeys("aravi16cs009@rmkcet.ac.in");
						driver.findElement(By.id("populate")).click();
						driver.switchTo().alert().accept();
						
						driver.findElement(By.id("month")).click();
						driver.findElement(By.id("discounts")).click();
						
						driver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("preferred-payment")));
						new Select(driver.findElement(By.id("preferred-payment"))).selectByVisibleText("Wallets");
						driver.findElement(By.id("tried-ecom")).click();
						
				        Actions move = new Actions(driver);
						Action action1 = (Action) move.dragAndDropBy(driver.findElement(By.xpath("//div[@id='slider']/span")), 500, 0).build();
				        action1.perform();
				        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='slider']/span")).getAttribute("style").toString(),"left: 88.8889%;");
				        driver.executeScript("window.open('https://www.lambdatest.com/selenium-automation/', '_blank');");
				        
				        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				        driver.switchTo().window(tabs.get(1));
				        
				        driver.findElement(By.cssSelector(".cbtn.btn_accept_ck")).click();
				        WebElement image = driver.findElement(By.xpath("//img[@alt='LambdaTest Jenkins integration']"));
				        wait.until(ExpectedConditions.visibilityOf(image));


				        BufferedImage a = SVGImage.fromSvg(new URL(image.getAttribute("src")));
				        File outputfile = new File("jenkins.png");
				        ImageIO.write(a, "png", outputfile);
			                File svgfile = new File("jenkins.svg");
				        outputfile.renameTo(svgfile);
				        
				        
				        
				        driver.switchTo().window(tabs.get(0));
				        System.out.println(svgfile.getAbsolutePath());
				        driver.setFileDetector(new LocalFileDetector());
				        driver.findElement(By.id("file")).sendKeys(svgfile.getAbsolutePath());
				        driver.switchTo().alert().accept();
				        driver.switchTo().window(tabs.get(0));
				        driver.findElement(By.id("submit-button")).click();
						driver.quit();	
						
		}


}
