
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class vanilla_android {
    public static String userName = System.getenv("LT_USERNAME") == null ? "Username"  //Add username here
            : System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Access_Key" //Add accessKey here
            : System.getenv("LT_ACCESS_KEY");

    private static RemoteWebDriver driver;

    public static String status ="failed";

    public static void main(String args[]) throws MalformedURLException, InterruptedException {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("w3c", true);
            ltOptions.put("platformName", "android");
            ltOptions.put("deviceName", "Galaxy S20");
            ltOptions.put("platformVersion", "10");
            ltOptions.put("build", "Java Selenium 4");
            ltOptions.put("name", "Sample Test Java");
            capabilities.setCapability("LT:Options", ltOptions);  // LT:Options

            driver = new RemoteWebDriver(new URL("https://" +userName + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);

            driver.get("https://www.google.com/");
            Thread.sleep(3000);

            status="passed";

        }
        catch (AssertionError a)
        {
            a.printStackTrace();
        }
        ((JavascriptExecutor) driver).executeScript("lambda-status="+status);
        driver.quit();
    }
    }
