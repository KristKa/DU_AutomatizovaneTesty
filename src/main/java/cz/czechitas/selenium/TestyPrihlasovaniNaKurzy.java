package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
      System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void zaregistrovanyRodicSePrihlasil() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement prihlaseniHP = prohlizec.findElement(By.xpath("/html/body/div/header/nav/div/div[2]/a"));
        prihlaseniHP.click();
        WebElement login = prohlizec.findElement(By.id("email"));
        login.sendKeys("kristyna.kounkova@gmail.com");
        WebElement password = prohlizec.findElement(By.id("password"));
        password.sendKeys("*LastMinutePanic*666");
        WebElement submitLogin = prohlizec.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submitLogin.click();
        WebElement prihlasky = prohlizec.findElement(By.xpath("/html/body/div/header/div/h1"));

        Assertions.assertNotNull(prihlasky);
    }

    @Test
    public void rodicVybralKurzPrihlasilSeDoAppAPrihlasilDite() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement kurzWeb = prohlizec.findElement(By.xpath("(//div[@class = 'card'])[2]//a[text()='Více informací']"));
        kurzWeb.click();

        WebDriverWait cekani = new WebDriverWait (prohlizec, 20);
        cekani.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci/pridat/41-html-1']")));

        WebElement prihlasitNaKurz = prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci/pridat/41-html-1']"));
        prihlasitNaKurz.click();

        WebElement login = prohlizec.findElement(By.id("email"));
        login.sendKeys("kristyna.kounkova@gmail.com");
        WebElement password = prohlizec.findElement(By.id("password"));
        password.sendKeys("*LastMinutePanic*666");
        WebElement submitLogin = prohlizec.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submitLogin.click();

        WebElement vyberTerminu = prohlizec.findElement(By.className("filter-option-inner-inner"));
        vyberTerminu.click();
        WebElement oznacTermin = prohlizec.findElement(By.xpath("//span[contains(text(), '15.12.')]"));
        oznacTermin.click();
        WebElement krestniJmeno = prohlizec.findElement(By.id("forename"));
        krestniJmeno.sendKeys("Andrea");
        WebElement prijmeni = prohlizec.findElement(By.id("surname"));
        prijmeni.sendKeys("Agilní");
        WebElement narozeniny = prohlizec.findElement(By.id("birthday"));
        narozeniny.sendKeys("06.06.2016");
        WebElement metodaUhrady = prohlizec.findElement(By.xpath("//label[@for='payment_fksp']"));
        metodaUhrady.click();
        WebElement souhlasPodminky = prohlizec.findElement(By.xpath("//label[contains(text(),'Souhlasím')]"));
        souhlasPodminky.click();
        WebElement potvrdPrihlasku = prohlizec.findElement(By.xpath("//input[@type=\"submit\"]"));
        potvrdPrihlasku.click();

        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/zaci");
        WebElement ZobrazitDetailPrihlaskyAndrey = prohlizec.findElement(By.xpath("//table/tbody/tr[1]/td[5]/div/a[1]"));
        ZobrazitDetailPrihlaskyAndrey.click();

        WebElement detailPrihlaskyAndrey = prohlizec.findElement(By.xpath("//h4[contains(text(),'15.12.')]"));

        Assertions.assertNotNull(detailPrihlaskyAndrey);

               }

    @Test
    public void rodicsePrihlasilDoAppNaselKurzAPrihlasilDite() {

        zaregistrovanyRodicSePrihlasil();

        WebElement zalozitNovouPrihlasku = prohlizec.findElement(By.xpath("//a[@class='btn btn-sm btn-info']"));
        zalozitNovouPrihlasku.click();
        WebElement kurzDA = prohlizec.findElement(By.xpath("(//div[@class = 'card'])[1]//a[text()='Více informací']"));
        kurzDA.click();
        WebElement zalozitPrihlasku = prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci/pridat/21-zaklady-algoritmizace']"));
        zalozitPrihlasku.click();

        WebElement vyberTerminu = prohlizec.findElement(By.className("filter-option-inner-inner"));
        vyberTerminu.click();
        WebElement oznacTermin = prohlizec.findElement(By.xpath("//span[contains(text(), '11.12.')]"));
        oznacTermin.click();
        WebElement krestniJmeno = prohlizec.findElement(By.id("forename"));
        krestniJmeno.sendKeys("Barbora");
        WebElement prijmeni = prohlizec.findElement(By.id("surname"));
        prijmeni.sendKeys("Agilní");
        WebElement narozeniny = prohlizec.findElement(By.id("birthday"));
        narozeniny.sendKeys("10.10.2011");
        WebElement metodaUhrady = prohlizec.findElement(By.xpath("//label[@for='payment_cash']"));
        metodaUhrady.click();
        WebElement souhlasPodminky = prohlizec.findElement(By.xpath("//label[contains(text(),'Souhlasím')]"));
        souhlasPodminky.click();
        WebElement potvrdPrihlasku = prohlizec.findElement(By.xpath("//input[@type=\"submit\"]"));
        potvrdPrihlasku.click();


        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/zaci");
        WebElement ZobrazitDetailPrihlaskyBarbory = prohlizec.findElement(By.xpath("//table/tbody/tr[2]/td[5]/div/a[1]"));
        ZobrazitDetailPrihlaskyBarbory.click();
        WebElement detailPrihlaskyBarbory = prohlizec.findElement(By.xpath("//h4[contains(text(),'15.12.')]"));

        Assertions.assertNotNull(detailPrihlaskyBarbory);
    }

    @Test
    public void rodicSePrihlasiAZrusiPrihlaskuDitetProNemoc() {

        zaregistrovanyRodicSePrihlasil();

        WebElement zrusitTretiPrihlasku = prohlizec.findElement(By.xpath("//table/tbody/tr[2]/td[5]/div/a[3]"));
        zrusitTretiPrihlasku.click();
        WebElement odhlasitProNemoc = prohlizec.findElement(By.xpath("//label[@for='logged_out_illness']"));
        odhlasitProNemoc.click();
        WebElement potvrdOdhlaseni = prohlizec.findElement (By.xpath("//input[@value='Odhlásit žáka']"));
        potvrdOdhlaseni.click();

        WebElement ZobrazitDetailTretiPrihlasky = prohlizec.findElement(By.xpath("//table/tbody/tr[3]/td[5]/div/a[1]"));
        ZobrazitDetailTretiPrihlasky.click();

        WebElement detailTretiPrihlasky = prohlizec.findElement(By.xpath("//ul[@class='m-0']"));

        Assertions.assertNotNull(detailTretiPrihlasky);
    }

    @AfterEach
    public void tearDown() {
        prohlizec.quit();
    }
}
