package selenium_testing;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class register_test {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/testselenium/form.html");
    }

    @Test
    public void testValidRegistration() {
        fillForm("Nguyen Van A", "nguyenvana@example.com", "Password@123", "Password@123", true, "2000-01-01");
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Đăng ký']"));
        submitButton.click();
        // Assert success message or redirection
        // Assert.assertTrue(driver.findElement(By.id("successMessage")).isDisplayed());
    }

    @Test
    public void testInvalidFullName() {
        fillForm("Nguyen", "nguyenvana@example.com", "Password@123", "Password@123", true, "2000-01-01");
        submitForm();
        Assert.assertEquals(driver.findElement(By.id("fullnameError")).getText(), "Họ và Tên phải chứa ít nhất 2 từ.");
    }

//    @Test
//    public void testInvalidEmail() {
//        fillForm("Nguyen Van A", "invalid-email", "Password@123", "Password@123", true, "2000-01-01");
//        submitForm();
//        Assert.assertEquals(driver.findElement(By.id("emailError")).getText(), "Vui lòng nhập đúng định dạng email.");
//    }

    @Test
    public void testWeakPassword() {
        fillForm("Nguyen Van A", "nguyenvana@example.com", "weak", "weak", true, "2000-01-01");
        submitForm();
        Assert.assertEquals(driver.findElement(By.id("passwordError")).getText(), "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt.");
    }

    @Test
    public void testPasswordMismatch() {
        fillForm("Nguyen Van A", "nguyenvana@example.com", "Password@123", "Password@321", true, "2000-01-01");
        submitForm();
        Assert.assertEquals(driver.findElement(By.id("confirmPasswordError")).getText(), "Xác nhận mật khẩu không khớp.");
    }

//    @Test
//    public void testNoGenderSelected() {
//        fillForm("Nguyen Van A", "nguyenvana@example.com", "Password@123", "Password@123", false, "2000-01-01");
//        submitForm();
//        Assert.assertEquals(driver.findElement(By.id("genderError")).getText(), "Vui lòng chọn giới tính.");
//    }

    @Test
    public void testUnderageUser() {
        fillForm("Nguyen Van A", "nguyenvana@example.com", "Password@123", "Password@123", true, "2005-01-01");
        submitForm();
        Assert.assertEquals(driver.findElement(By.id("dobError")).getText(), "Bạn phải đủ 18 tuổi.");
    }

//    @Test
//    public void testEmptyFields() {
//        // Leave fields empty and submit
//        submitForm();
//        Assert.assertEquals(driver.findElement(By.id("fullnameError")).getText(), "Họ và Tên phải chứa ít nhất 2 từ.");
//        Assert.assertEquals(driver.findElement(By.id("emailError")).getText(), "Vui lòng nhập đúng định dạng email.");
//        Assert.assertEquals(driver.findElement(By.id("passwordError")).getText(), "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt.");
//        Assert.assertEquals(driver.findElement(By.id("confirmPasswordError")).getText(), "Xác nhận mật khẩu không khớp.");
//        Assert.assertEquals(driver.findElement(By.id("genderError")).getText(), "Vui lòng chọn giới tính.");
//        Assert.assertEquals(driver.findElement(By.id("dobError")).getText(), "Bạn phải đủ 18 tuổi.");
//    }

    private void fillForm(String fullName, String email, String password, String confirmPassword, boolean selectGender, String dob) {
        WebElement fullnameField = driver.findElement(By.id("fullname"));
        fullnameField.clear();
        fullnameField.sendKeys(fullName);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement confirmPasswordField = driver.findElement(By.id("confirm_password"));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);

        if (selectGender) {
            driver.findElement(By.id("male")).click(); // Default to Male
        }

        WebElement dobField = driver.findElement(By.id("dob"));
        dobField.clear();
        dobField.sendKeys(dob);
    }

    private void submitForm() {
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Đăng ký']"));
        submitButton.click();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after the tests
        if (driver != null) {
            driver.quit();
        }
    }
}
