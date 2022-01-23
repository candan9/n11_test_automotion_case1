package stepdefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;//open source github project for browser driver management
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class BaseTest {

    WebDriver driver ;

    @BeforeAll
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.n11.com/kampanyalar");
        driver.manage().window().maximize();
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }

}
