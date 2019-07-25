package cn.wangsr.phtest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ErrorHandler;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author: wjl
 * @description:
 * @time: 2019/7/23 0023 09:24
 */
public class Test01 {

    public static void testPh(String phPath){
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,phPath);
        //创建无界面浏览器对象
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);

        try {

            driver.get("https://accounts.douban.com/passport/login");
            Thread.sleep(3000);
//            WebElement  frame= driver.findElement(By.tagName("iframe"));
//            driver.switchTo().frame(frame);
            WebElement pwdLoginbutton = driver.findElementByClassName("account-body-tabs").findElement(By.className("account-tab-account"));

            pwdLoginbutton.click();
//            获取账号密码输入框的节点
            WebElement userNameElement=  driver.findElement(By.id("username"));
            WebElement pwdElement= driver.findElement(By.id("password"));
            driver.setErrorHandler(new ErrorHandler());


            userNameElement.sendKeys("17864280652");
            pwdElement.sendKeys("123456wjl");
//
            WebElement loginButton = driver.findElement(By.className("account-form-field-submit"));
            loginButton.click();
//
            Thread.sleep(4000L);
//
            String windowHandle = driver.getWindowHandle();
            driver.switchTo().window(windowHandle);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Thread.sleep(3000);
            driver.get("https://www.douban.com/mine/");
            Thread.sleep(3000);
//
//            WebElement myInfo = driver.findElement(By.className("info")).findElement(By.tagName("h1"));
//            String content = myInfo.getText();
//            System.out.println("content="+content);

            File file= driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File("./dir02/"+"douban.jpg"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            //关闭并退出浏览器
            driver.close();
            driver.quit();
        }


    }
    public static void main(String[] args){
        String path="D:\\迅雷下载\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";
//        System.setProperty("phantomjs.binary.path", "");
        testPh(path);
//        WebDriver driver = new PhantomJSDriver();
//        driver.get("https://blog.51cto.com/11959730/2136263");

    }

}
