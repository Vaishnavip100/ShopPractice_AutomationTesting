package utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent==null) {
            String reportPath=System.getProperty("user.dir") + "/reports/";
            File folder=new File(reportPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            ExtentSparkReporter spark=new ExtentSparkReporter(reportPath + "ExtentReport.html");
            spark.config().setReportName("Automation Test Results");
            spark.config().setDocumentTitle("Test Execution Report");
            extent=new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}