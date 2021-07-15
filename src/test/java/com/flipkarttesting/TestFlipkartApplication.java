/*
 *Purpose : Class is implemented for executing the test cases by using testng
 *                @Test is used identify the test case and to execute it
 *                @Listeners annotation is used to make listen instructions before and after testcases
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 12-07-2021
 */

package com.flipkarttesting;

import com.flipkarttesting.engine.KeywordEngine;
import com.flipkarttesting.utility.listeners.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class TestFlipkartApplication {

    @Test
    @Description("login to application")
    @Severity(SeverityLevel.BLOCKER)
    public void loginToApplication() {
        KeywordEngine keywordEngine = new KeywordEngine();
        keywordEngine.startExecution("sheet1");
    }
}
