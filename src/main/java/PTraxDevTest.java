import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PTraxDevTest extends TestCase{
	private WebDriver driver;
	
	public void setup() throws Exception{	
		File file = new File("C:/dev/selenium/IEDriverServer_x64_2.35.3/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		this.driver = new InternetExplorerDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void loadPage(String url) throws Exception{
		this.driver.get(url);
	}

	public void testSimple() throws Exception {
		this.setup();
		this.loadPage("https://test.login.mayo.edu/adfs/ls/?wa=wsignin1.0&wtrealm=https%3a%2f%2froectm900a%2fCTMS%2fPTrax&wctx=%2FCTMS%2FPTrax%2Fhome.jsf");
		assertEquals("Log In", this.driver.getTitle());
	}
	
//	public void loginTest() throws Exception {
//		this.ptraxLogin("m113216", "iLjohn23");
//	}
//	
//	public void advancedSearchTest() throws Exception {
//		this.launchAdvancedSearch("Study Coordinator", "Hardie");
//		this.createNewStudy();
//	}
	
	public void tearDown() throws Exception {
		this.driver.quit();
	}
		
	
	public void ptraxLogin(String username, String password){
		List<WebElement> elements = this.driver.findElements(By.xpath("//input"));
		WebElement submitButton = null;
		for(WebElement element : elements){
			if(element.getAttribute("id").equals("UsernameTextBox")){
				element.sendKeys(username);
			}
			if(element.getAttribute("id").equals("PasswordTextBox")){
				element.sendKeys(password);
			}
			if(element.getAttribute("id").equals("SubmitButton")){
				submitButton = element;
			}
		}
		
		submitButton.click();
	}
	
	public void launchAdvancedSearch(String studyRole, String lastName){
	    WebDriverWait wait = new WebDriverWait(driver, 100);
		driver.findElement(By.id("searchProtocolForm:advancedCriteriaPanel_header")).click();
		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchProtocolForm:studyRolecomboboxButton")));
		myElement.click();
		try{
			driver.findElement(By.xpath(".//*[text()='" + studyRole + "']"));
		}
		catch(NoSuchElementException e){
			myElement.click();
		}
		
		WebElement myElement2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[text()='" + studyRole + "']")));
		myElement2.click();

//		Select select = new Select(driver.findElement(By.id("searchProtocolForm:studyRolecomboboxButton")));
//		select.selectByVisibleText(studyRole);
		
//	    WebElement myElement = driver.findElement(By.xpath(".//*[@id='searchProtocolForm:studyRolecomboboxField']"));
//		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[text()='" + studyRole + "']")));
//		myElement.click();
	    //	    driver.findElement(By.xpath(".//*[text()='" + studyRole + "']")).click();

	    driver.findElement(By.id("searchProtocolForm:lastName")).sendKeys(lastName);
	    driver.findElement(By.id("searchProtocolForm:btnAdvancedSearch")).click();
	 
	}
	
	public void createNewStudy(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//*[@id="resultForm:resultTable:0:protocolTitleOut"]
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@id='resultForm:resultTable:0:homeResultTable_col3']/div"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("resultForm:j_id153"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("studyMgmtActions:j_id155"))).click();

		
//		driver.findElement(By.xpath("//td[@id='resultForm:resultTable:0:homeResultTable_col3']/div")).click();
//	    driver.findElement(By.id("resultForm:j_id153:anchor")).click();
//	    driver.findElement(By.id("studyMgmtActions:j_id155")).click();
//	    driver.findElement(By.id("studyConfigForm:processICStatus:0")).click();
//	    driver.findElement(By.id("studyConfigForm:manageICStatus:0")).click();
//	    driver.findElement(By.id("studyConfigForm:participantTrackingStatus:0")).click();
//	    driver.findElement(By.id("studyConfigForm:processICStatus:1")).click();
//	    driver.findElement(By.id("studyConfigForm:processICStatus:0")).click();
//	    driver.findElement(By.id("studyConfigForm:j_id416_lbl")).click();
//	    new Select(driver.findElement(By.id("studyConfigForm:studySiteSelect"))).selectByVisibleText("MCR");
//	    new Select(driver.findElement(By.id("studyConfigForm:edcSelect"))).selectByVisibleText("Rave CTMS");
//	    new Select(driver.findElement(By.id("studyConfigForm:LeadCoordSelect"))).selectByVisibleText("Hardie, Linda V");
//	    new Select(driver.findElement(By.id("studyConfigForm:StudyTypeSelect"))).selectByVisibleText("Clinical Research");
//	    driver.findElement(By.id("studyConfigForm:studySubType_Prospective")).click();
//	    driver.findElement(By.id("studyConfigForm:saveExitButton")).click();
	}

}
