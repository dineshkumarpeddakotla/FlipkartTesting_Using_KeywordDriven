/*
 *Purpose : Class is implemented for reading keywords and steps from excel sheet
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 25-06-2021
 */

package com.flipkarttesting.engine;

import com.flipkarttesting.config.ReadProperties;
import com.flipkarttesting.base.BaseClass;
import com.flipkarttesting.utility.Log;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KeywordEngine extends BaseClass {

    public ReadProperties readProperties;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public BaseClass base;
    public WebElement element;
    boolean booleanValue;

    //path of a file
    public final String SCENARIO_SHEET_PATH = ".\\src\\test\\resources\\testScenarios.xlsx";

    /**
     * startExecution method is used to start reading and execution the steps
     * @param sheetName sheet name of a excel sheet
     */
    public void startExecution(String sheetName) {

        FileInputStream file;
        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);// reading file
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);
        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {
                //reading columns and rows from excel sheet
                String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();
                String assertValue = sheet.getRow(i+1).getCell(k+5).toString().trim();

                //for opening and closing the browser
                switch (action) {
                    case "open browser":
                        base = new BaseClass();
                        readProperties = new ReadProperties();
                        prop = readProperties.init_properties();

                        if (value.isEmpty() || value.equals("NA")) {
                            driver = base.selectDriver(prop.getProperty("browser"));
                        } else {
                            Log.info("Browser is selected");
                            driver = base.selectDriver(value);
                        }
                        driver.manage().window().maximize();
                        break;

                    case "enter url":
                        if (value.isEmpty() || value.equals("NA")) {
                            driver.get(prop.getProperty("url"));
                        } else {
                            driver.get(value);
                        }
                        Log.info("navigating to url");
                        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        break;

                    case "quit":
                        Log.info("closing browser");
                        driver.quit();
                        break;
                    default:
                        break;
                }

                //identifying the web elements by locator type and using their actions
                switch (locatorType) {
                    case "id":// locator id
                        element = driver.findElement(By.id(prop.getProperty(locatorValue)));
                        if (action.equalsIgnoreCase("sendKeys")) {
                            Log.info("clear text box");
                            element.clear();
                            Log.info("send values");
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            Log.info("click");
                            element.click();
                            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            Log.info("element is displayed");
                            booleanValue = element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        break;

                    case "name":// locator  name
                        element = driver.findElement(By.name(prop.getProperty(locatorValue)));
                        if (action.equalsIgnoreCase("sendKeys")) {
                            element.clear();
                            Log.info("sending values");
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            Log.info("click on element");
                            element.click();
                            driver.manage().timeouts().implicitlyWait(20 , TimeUnit.SECONDS);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            Log.info("element is displayed");
                            booleanValue = element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            Log.info("get text of element");
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        break;

                    case "xpath":// locator  xpath
                        element = driver.findElement(By.xpath(prop.getProperty(locatorValue)));
                        if (action.equalsIgnoreCase("sendKeys")) {
                            element.clear();
                            Log.info("send value");
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            Log.info("click on element");
                            element.click();
                            driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            Log.info("element is displayed");
                            booleanValue = element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            Log.info("get text");
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        break;

                    case "cssSelector":// locator  css selector
                        element = driver.findElement(By.cssSelector(prop.getProperty(locatorValue)));
                        if (action.equalsIgnoreCase("sendKeys")) {
                            element.clear();
                            Log.info("send value");
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                            Log.info("element is clicked");
                            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            Log.info("element is displayed");
                            booleanValue = element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        break;

                    case "className"://locator className
                        element = driver.findElement(By.className(prop.getProperty(locatorValue)));
                        if (action.equalsIgnoreCase("sendKeys")) {
                            element.clear();
                            Log.info("send value");
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            Log.info("click on element");
                            element.click();
                            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            Log.info("element is displayed");
                            booleanValue = element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            Log.info("get text of element");
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        break;

                    case "linkText"://locator link text
                        element = driver.findElement(By.linkText(prop.getProperty(locatorValue)));
                        Log.info("click on element");
                        element.click();
                        break;

                    case "partialLinkText"://locator partial link test
                        element = driver.findElement(By.partialLinkText(prop.getProperty(locatorValue)));
                        Log.info("click on element");
                        element.click();
                        break;

                    default:
                }

                //for assertion
                switch (action) {
                    case "getTitle" ://by get title
                        Assert.assertEquals(driver.getTitle(), assertValue);
                        break;
                    case "getCurrentUrl" ://by get current url
                        Assert.assertEquals(driver.getCurrentUrl(), assertValue);
                        break;
                    case "getBoolean":// by boolean value
                        Assert.assertTrue(booleanValue);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
