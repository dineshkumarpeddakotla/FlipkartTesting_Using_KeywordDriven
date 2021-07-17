/*Purpose : Class is implemented to select web browser and returns driver
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 12-07-2021
 */

package com.flipkarttesting.base;

import com.flipkarttesting.utility.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BaseClass {

    //driver variable is declared
    public static WebDriver driver;

    /**
     * selectDriver method is used to select the driver value from browsers
     * @param browser browser name is given as parameter
     * @return driver value
     */
    public WebDriver selectDriver(String browser) {

        switch (browser) {
            case "chrome": //chrome driver
                Log.info("launching chrome browser");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox"://firefox driver
                Log.info("launching firefox browser");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge"://edge driver
                Log.info("launching edge browser");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "opera"://opera driver
                Log.info("launching opera browser");
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;
        }

        return driver;
    }
}
