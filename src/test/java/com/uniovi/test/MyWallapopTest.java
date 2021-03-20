package com.uniovi.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.test.pageobjects.PO_HomeView;
import com.uniovi.test.pageobjects.PO_LoginView;
import com.uniovi.test.pageobjects.PO_RegisterView;
import com.uniovi.test.pageobjects.PO_View;

import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyWallapopTest {
	//En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\ignac\\Desktop\\Uni\\20-21\\SDI\\PL\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	//En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas):
	//static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	//static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
	//Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();

		return driver;
	}

	//Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp(){
		driver.navigate().to(URL);
	}

	//Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown(){
		driver.manage().deleteAllCookies();
	}

	//Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	//Al finalizar la última prueba
	@AfterClass
	static public void end() {
		//Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	/**
	 * [Prueba1] Registro de Usuario con datos válidos.
	 */
	@Test
	public void Prueba1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "Josefo@email.com", "Josefo", "Perez", "123456", "123456");
		PO_View.checkElement(driver, "text", "Ofertas");
	}

	/**
	 * Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos).
	 */
	@Test
	public void Prueba2() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "", "", "123456", "123456");
		PO_View.checkElement(driver, "text", "Registrate");
	}

	/**
	 * [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña inválida).
	 */
	@Test
	public void Prueba3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "Antonio@email.com", "Antonio", "Suarez", "1234567", "123456");
		PO_View.checkElement(driver, "text", "Registrate");
	}

	/**
	 * [Prueba4] Registro de Usuario con datos inválidos (email existente).
	 */
	@Test
	public void Prueba4() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "pedro@email.com", "Pedro", "Perez", "123456", "123456");
		PO_View.checkElement(driver, "text", "Registrate");
	}

	/**
	 * [Prueba5] Inicio de sesión con datos válidos (administrador).
	 */
	@Test
	public void Prueba5() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com" , "admin" );
		PO_View.checkElement(driver, "text", "Gestion de usuarios");
	}
	
	/**
	 * [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
	 */
	@Test
	public void Prueba6() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@email.com" , "123456" );
		PO_View.checkElement(driver, "text", "Ofertas");
	}
	
	/**
	 * [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email y contraseña vacíos).
	 */
	@Test
	public void Prueba7() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "" , "" );
		PO_View.checkElement(driver, "text", "Identificate");
	}
	
	/**
	 * [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email existente, pero contraseña incorrecta).
	 */
	@Test
	public void Prueba8() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@email.com" , "123456789" );
		PO_View.checkElement(driver, "text", "Identificate");
	}
	
	/**
	 * [Prueba9] Inicio de sesión con datos inválidos (usuario estándar, email no existente en la aplicación).
	 */
	@Test
	public void Prueba9() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pepe@email.com" , "123456" );
		PO_View.checkElement(driver, "text", "Identificate");
	}

	/**
	 * [Prueba10] Hacer click en la opción de salir de sesión y comprobar que se redirige a la página de inicio de sesión (Login).
	 */
	@Test
	public void Prueba10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@email.com" , "123456" );
		PO_View.checkElement(driver, "text", "Ofertas");
		
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Identificate");
	}
	
	/**
	 * [Prueba11] Comprobar que el botón cerrar sesión no está visible si el usuario no está autenticado.
	 */
	@Test(expected = TimeoutException.class)
	public void Prueba11() {
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
}
