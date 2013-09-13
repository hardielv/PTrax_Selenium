package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateNewStudy {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://roectm900a.mayo.edu/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreateNewStudy() throws Exception {
    driver.get(baseUrl + "/CTMS/PTrax/home.jsf");
    driver.findElement(By.xpath("//td[@id='resultForm:resultTable:0:homeResultTable_col3']/div")).click();
    driver.findElement(By.id("resultForm:j_id153:anchor")).click();
    driver.findElement(By.id("studyMgmtActions:j_id155")).click();
    driver.findElement(By.id("studyConfigForm:processICStatus:0")).click();
    driver.findElement(By.id("studyConfigForm:manageICStatus:0")).click();
    driver.findElement(By.id("studyConfigForm:participantTrackingStatus:0")).click();
    driver.findElement(By.id("studyConfigForm:processICStatus:1")).click();
    driver.findElement(By.id("studyConfigForm:processICStatus:0")).click();
    driver.findElement(By.id("studyConfigForm:j_id416_lbl")).click();
    new Select(driver.findElement(By.id("studyConfigForm:studySiteSelect"))).selectByVisibleText("MCR");
    new Select(driver.findElement(By.id("studyConfigForm:edcSelect"))).selectByVisibleText("Rave CTMS");
    new Select(driver.findElement(By.id("studyConfigForm:LeadCoordSelect"))).selectByVisibleText("Hardie, Linda V");
    new Select(driver.findElement(By.id("studyConfigForm:StudyTypeSelect"))).selectByVisibleText("Clinical Research");
    driver.findElement(By.id("studyConfigForm:studySubType_Prospective")).click();
    driver.findElement(By.id("studyConfigForm:saveExitButton")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
