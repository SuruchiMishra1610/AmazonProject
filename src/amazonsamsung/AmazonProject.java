package amazonsamsung;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Setting up system property and invoking Chrome Driver
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//Launching the Amazon Website
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		//Passing "samsung" keyword in Search-box 
		WebElement Search = driver.findElement(By.id("twotabsearchtextbox"));
		Search.sendKeys("samsung");
		
		//Clicking on Search button
		WebElement SearchBtn = driver.findElement(By.id("nav-search-submit-button"));
		SearchBtn.click();
		 
		//Getting the list of Samsung products and prices
		List<WebElement> SamsungProductsList = driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(), 'Samsung ')]"));
		List<WebElement> SamsungProductsPriceList = driver.findElements(By.xpath("//div[@data-component-type=\"s-search-result\"]//span[@class=\"a-price\"]"));
		System.out.println("Total count of Samsung Product  :" + SamsungProductsList.size());
		
		for(int i = 0; i<=SamsungProductsList.size(); i++) {
			
			System.out.println(SamsungProductsList.get(i).getText() + "  " + SamsungProductsPriceList.get(i).getText());
		}
		
		String DefaultWindowHandler = driver.getWindowHandle();
		String ExpectedResult = SamsungProductsList.get(0).getText();
		SamsungProductsList.get(0).click();
		Set<String> AllWin = driver.getWindowHandles();
		
		for(String win : AllWin) {
			System.out.println(win);
		
   		if(!win.equals(DefaultWindowHandler)) {
			
			driver.switchTo().window(win);
		}
	}		

		//Validating Expected and Actual Result
		WebElement ActualResult = driver.findElement(By.id("productTitle"));
		ActualResult.getText();
		if(ExpectedResult.equals(ActualResult)) {
			System.out.println("******* TestCase Passed ********");
		}else {
			System.out.println("******* TestCase Failed ********");
		}
		
	}

}
