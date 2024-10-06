package selenium_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class login_facebook {
	public static void main(String[] args) {
        // Thiết lập đường dẫn đến ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Khởi tạo đối tượng WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // 1. Mở trang đăng nhập Facebook
            driver.get("https://www.facebook.com/");
            
            // 2. Nhập tên người dùng
            WebElement usernameField = driver.findElement(By.id("email"));
            usernameField.sendKeys("your-username");

            // 3. Nhập mật khẩu
            WebElement passwordField = driver.findElement(By.id("pass"));
            passwordField.sendKeys("your-password");

            // 4. Nhấn nút đăng nhập
            WebElement loginButton = driver.findElement(By.name("login"));
            loginButton.click();

            // 5. Xác minh người dùng đã được chuyển hướng đến trang chính (bằng cách kiểm tra tiêu đề trang)
            Thread.sleep(3000); // Đợi trang tải (có thể dùng WebDriverWait cho chuyên nghiệp hơn)
            String expectedTitle = "Facebook";
            String actualTitle = driver.getTitle();
            
            if (actualTitle.contains(expectedTitle)) {
                System.out.println("Đăng nhập thành công!");
            } else {
                System.out.println("Đăng nhập thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng trình duyệt
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
