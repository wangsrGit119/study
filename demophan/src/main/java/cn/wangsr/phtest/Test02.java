package cn.wangsr.phtest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: wjl
 * @description:
 * @time: 2019/7/23 0023 09:24
 */
public class Test02 {

    public static void testPh(String phPath) throws Exception{




    }


    public static void main(String[] args) {
        String path="D:\\迅雷下载\\chromedriver_win32(3)\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver driver = new ChromeDriver();

        try{

            driver.get("https://www.douban.com/");
            Thread.sleep(3000);
            WebElement  frame= driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(frame);
            WebElement pwdLoginbutton = ((ChromeDriver) driver).findElementByClassName("account-body-tabs").findElement(By.className("account-tab-account"));

            pwdLoginbutton.click();
//            获取账号密码输入框的节点
            WebElement userNameElement=  driver.findElement(By.id("username"));
            WebElement pwdElement= driver.findElement(By.id("password"));


            userNameElement.sendKeys("17864280652");
            pwdElement.sendKeys("123456wjl");

            WebElement loginButton = driver.findElement(By.className("account-form-field-submit"));
            loginButton.click();

            Thread.sleep(3000L);

            String windowHandle = driver.getWindowHandle();
            driver.switchTo().window(windowHandle);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://www.douban.com/mine/");
            Thread.sleep(3000L);

            WebElement myInfo = driver.findElement(By.className("info")).findElement(By.tagName("h1"));
            String content = myInfo.getText();
            System.out.println("content="+content);

            File file=((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File("./dir02/"+"douban.jpg"));

        }catch (Exception e){
            e.getStackTrace();
        }finally {

            driver.close();
            driver.quit();
        }



    }

}
