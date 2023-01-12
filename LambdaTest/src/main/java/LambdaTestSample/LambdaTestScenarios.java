package LambdaTestSample;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
public class LambdaTestScenarios {
		 String username = "guruprasadpkini";
		 String accesskey = "fWmEYaZiEgBhZAs0swEPvZtHBU2dTN9mBZJDR3H8cf83btpVf5";
		 static RemoteWebDriver driver = null;
		 String gridURL = "@hub.lambdatest.com/wd/hub";
		 boolean status = false;
	
		public static void main(String[] args) {
			new LambdaTestScenarios().testScenario1();
			new LambdaTestScenarios().testScenario2();
			new LambdaTestScenarios().testScenario3();
		}
	
		public void testScenario1() {
			browserSetUp();
			try {
			driver.get("https://www.lambdatest.com/selenium-playground/");
			driver.findElement(By.xpath("//a[contains(text(), 'Simple Form Demo')]")).click();
			String URL = driver.getCurrentUrl();
			Assert.assertEquals(URL.contains("simple-form-demo"), true);
			driver.findElement(By.id("user-message")).sendKeys("Welcome to LambdaTest");
			driver.findElement(By.xpath("//button[@id = 'showInput']")).click();
			String message = driver.findElement(By.xpath("(//p[@class='mt-20'])[1]")).getText();
			if(message.equals("Welcome to LambdaTest")) {
				status = true;
			}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			finally {
				tearDown();
			}
		}
		public void testScenario2() {
			browserSetUp();
			try {
				driver.get("https://www.lambdatest.com/selenium-playground/");
				driver.findElement(By.xpath("//a[contains(text(),'Drag & Drop Sliders')]")).click();
				WebElement slider = driver.findElement(By.xpath("//div[@class='sp__range sp__range-success']//input"));
				Actions actions = new Actions(driver);
				actions.dragAndDropBy(slider, 95, 0).perform();
				String message = driver.findElement(By.xpath("//div[@class='sp__range sp__range-success']//output")).getText();
				if(message.equals("95")) {
					status = true;
				}
				}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally {
				tearDown();
			}
		}
		public void testScenario3() {
			browserSetUp();
			try {
				driver.get("https://www.lambdatest.com/selenium-playground/");
				driver.findElement(By.xpath("//a[contains(text(), 'Input Form Submit')]")).click();
				driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
				String errorMessage = driver.findElement(By.xpath("//input[@id = 'name']")).getAttribute("validationMessage");
				if(errorMessage.equals("Please fill out this field.")) {
					driver.findElement(By.name("name")).sendKeys("Guruprasad Kini");
					driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("guruprasadpkini@gmail.com");
					driver.findElement(By.name("password")).sendKeys("password");
					driver.findElement(By.name("company")).sendKeys("ABC Ltd");
					driver.findElement(By.name("website")).sendKeys("https://abc.com");
					driver.findElement(By.name("city")).sendKeys("Bengaluru");
					driver.findElement(By.name("address_line1")).sendKeys("152 coral hill drive");
					driver.findElement(By.name("address_line2")).sendKeys("hills view road");
					driver.findElement(By.id("inputState")).sendKeys("Karnataka");
					driver.findElement(By.name("zip")).sendKeys("560092");
					WebElement dropDown = driver.findElement(By.name("country"));
					Select select = new Select(dropDown);
					select.selectByVisibleText("India");
					driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
					Thread.sleep(5000);
					String message = driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
					if(message.equals("Thanks for contacting us, we will get back to you shortly.")) {
						status = true;
					}
				}
				}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally {
				tearDown();
			}
		}
		
		public void browserSetUp(){
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName", "chrome");
			capabilities.setCapability("version", "77.0");
			capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
			capabilities.setCapability("build", "LambdaTestSampleApp");
			capabilities.setCapability("name", "LambdaTestJavaSample");
			capabilities.setCapability("network", true);// To enable network logs
			capabilities.setCapability("visual", true);// To enable step by step screenshot
			capabilities.setCapability("video",true);// To enable video recording
			capabilities.setCapability("console", true);// To capture console logs
			try {
				driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
			}
			catch(MalformedURLException e) {
				System.out.println("Invalid grid URL");
		    } catch (Exception e) {
		    	System.out.println(e.getMessage());
		    }
		}
	
		private void tearDown() {
			if (driver != null) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("lambda-status="+status);
				driver.quit(); 
			}
		}
}

