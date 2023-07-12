import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import com.lambdatest.tunnel.Tunnel;
import java.net.URL;
import java.util.HashMap;

public class vanilla_android {
    public static String userName = "username";
    public static String accessKey = "Access key";

    private static RemoteWebDriver driver;

    public static String status ="failed";
    static Tunnel t;

    public static void main(String args[]) throws Exception {
        //Tunnel t;

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

            t = new Tunnel();
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("user", userName);
            options.put("key", accessKey);
    
            //start tunnel
            t.start(options);

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
        t.stop();
    }
    }

