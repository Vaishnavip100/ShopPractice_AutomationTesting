package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String testName) {
    	String folderPath=System.getProperty("user.dir") + "/screenshots/";
        File folder=new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String filePath=System.getProperty("user.dir")+"/screenshots/" + testName + "_" + timeStamp + ".png";

        try {
            TakesScreenshot ts=(TakesScreenshot) driver;
            File src=ts.getScreenshotAs(OutputType.FILE);
            File dest=new File(filePath);

            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
}