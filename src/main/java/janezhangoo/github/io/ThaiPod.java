package janezhangoo.github.io;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ThaiPod {
    public static void main(String[] args) {
        String url = "https://www.thaipod101.com/lesson-library/level-1-thai/";


        //setup to use local session for selenium test

        //"C:\Program Files\Google\Chrome\Application\chrome.exe"  --remote-debugging-port=9222 --user-data-dir="C:\selenum\ChromeProfile"

        System.out.println("Hello World!");

        System.setProperty("webdriver.chrome.driver", "C:\\software\\chromedriver-100\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(options);

        try {

            driver.get(url);
//            driver.findElement(By.className("lightBox-signup-header-close")).click();
            List<WebElement> lessons = driver.findElements(By.className("Lesson__title_2e8e"));

            for (int i = 19; i < lessons.size(); i++) {
                WebElement lesson = driver.findElements(By.className("Lesson__title_2e8e")).get(i);
                System.out.println(lesson.getDomAttribute("href"));
                Thread.sleep(5000);
                lesson.click();
                Thread.sleep(5000);
                String location = driver.findElement(By.xpath("//*[@id=\"download-center\"]/ul/li[1]/a")).getDomAttribute("href");
                System.out.println(location + " downloading...");
                String fileName = location.substring(location.lastIndexOf("/") + 1);
                FileUtils.copyURLToFile(new URL(location), new File(fileName));
                driver.navigate().back();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
            driver.quit();
        }


    }
}
