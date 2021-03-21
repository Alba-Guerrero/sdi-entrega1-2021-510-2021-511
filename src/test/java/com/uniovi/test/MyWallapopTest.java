package com.uniovi.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repository.UsersRepository;
import com.uniovi.services.OfertaService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.test.pageobjects.PO_HomeView;
import com.uniovi.test.pageobjects.PO_LoginView;
import com.uniovi.test.pageobjects.PO_Properties;
import com.uniovi.test.pageobjects.PO_RegisterView;
import com.uniovi.test.pageobjects.PO_View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.uniovi.test.util.SeleniumUtils;

import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTest {
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\ignac\\Desktop\\Uni\\20-21\\SDI\\PL\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	@Autowired
	private UsersService usersService;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private OfertaService ofertaService;

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();

		return driver;
	}

	@Before
	public void setUp(){
		initdb();
		driver.navigate().to(URL);
	}

	public void initdb() {
		usersRepository.deleteAll();

		User user1 = new User("Pedro", "Díaz", "pedro@email.com", "123456", "123456");
		user1.setRole(rolesService.getRoles()[0]);

		Oferta o1_1 = new Oferta(1L, 50.30, "Falda", "Falda larga talla L", user1);
		Oferta o1_2 = new Oferta(2L, 5, "Cuchara", "Cuchara de acero inoxidable", user1);
		Oferta o1_3 = new Oferta(3L, 100, "Monitor LED", "Monitor LED de 24 pulgadas", user1);
		Oferta o1_4 = new Oferta(4L, 20, "Microfono", "Microfono modelo AXD15", user1);

		User user2 = new User("Ana", "Gonzalez", "ana@email.com", "123456", "123456");
		user2.setRole(rolesService.getRoles()[0]);

		Oferta o2_1 = new Oferta(5L, 1.90, "Funda movil", "Funda para movil modelo Redmi 4", user2);
		Oferta o2_2 = new Oferta(6L, 10, "Cascos", "Cascos bluetooth", user2);
		Oferta o2_3 = new Oferta(7L, 3.50, "Pila", "Pila recargable", user2);

		User user3 = new User("Jose", "Díaz", "jose@email.com", "123456", "123456");
		user3.setRole(rolesService.getRoles()[0]);

		Oferta o3_1 = new Oferta(8L, 500, "Ordenador", "Ordenador gaming I7...", user3);
		Oferta o3_2 = new Oferta(9L, 150, "Tarjeta grafica", "Tarjeta grafica gtx 1660", user3);
		Oferta o3_3 = new Oferta(10L, 57.96, "Disco duro", "Disco duro ssd 120gb", user3);
		Oferta o3_4 = new Oferta(11L, 100, "Vestido", "Vestido de una pieza", user3);

		User user4 = new User("Maria", "Sanchez", "maria@email.com", "123456", "123456");
		user4.setRole(rolesService.getRoles()[0]);

		Oferta o4_1 = new Oferta(12L, 20, "Sudadera", "Sudadera MaxPower talla S hombre", user4);
		Oferta o4_2 = new Oferta(13L, 15.99, "Comida para perro", "Comida para perros grande mas 10kg", user4);
		Oferta o4_3 = new Oferta(14L, 2.50, "Cabe hdmi", "Cable hdmi longitud 120 cm", user4);

		User user5 = new User("Andrea", "Garcia", "andrea@email.com", "123456", "123456");
		user5.setRole(rolesService.getRoles()[0]);

		Oferta o5_1 = new Oferta(15L, 2000, "Reloj", "Reloj rolex edicion limitada", user5);
		Oferta o5_2 = new Oferta(16L, 0.50, "Chicles", "Paquete x10 chicles", user5);
		Oferta o5_3 = new Oferta(17L, 12.84, "Camiseta", "Camiseta deporte M mujer", user5);

		User user6 = new User("Admin", "Admin", "admin@email.com", "admin", "admin");
		user6.setRole(rolesService.getRoles()[1]);

		o1_3.setEmailComprador(user3.getEmail());
		o1_4.setEmailComprador(user2.getEmail());

		o2_2.setEmailComprador(user1.getEmail());
		o2_1.setEmailComprador(user4.getEmail());

		o3_3.setEmailComprador(user1.getEmail());
		o3_1.setEmailComprador(user5.getEmail());

		o4_3.setEmailComprador(user5.getEmail());
		o4_2.setEmailComprador(user3.getEmail());

		o5_1.setEmailComprador(user2.getEmail());
		o5_3.setEmailComprador(user3.getEmail());

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);

		ofertaService.addOferta(o1_1);
		ofertaService.addOferta(o1_2);
		ofertaService.addOferta(o1_3);
		ofertaService.addOferta(o1_4);

		ofertaService.addOferta(o2_1);
		ofertaService.addOferta(o2_2);
		ofertaService.addOferta(o2_3);

		ofertaService.addOferta(o3_1);
		ofertaService.addOferta(o3_2);
		ofertaService.addOferta(o3_3);
		ofertaService.addOferta(o3_4);
		
		ofertaService.addOferta(o4_1);
		ofertaService.addOferta(o4_2);
		ofertaService.addOferta(o4_3);

		ofertaService.addOferta(o5_1);
		ofertaService.addOferta(o5_2);
		ofertaService.addOferta(o5_3);
	}

	@After
	public void tearDown(){
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	public void login(String email, String pass) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, email , pass );
		PO_View.checkElement(driver, "span", email);
	}

	public void fillFormOferta(String descripcion, String detalle, int precio) {
		WebElement dni = driver.findElement(By.name("descripcion"));
		dni.click();
		dni.clear();
		dni.sendKeys(descripcion);
		WebElement name = driver.findElement(By.name("detalle"));
		name.click();
		name.clear();
		name.sendKeys(detalle);
		WebElement lastname = driver.findElement(By.name("precio"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(String.valueOf(precio));

		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	/**
	 * [Prueba1] Registro de Usuario con datos válidos.
	 */
	@Test
	public void Prueba1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "Josefo@email.com", "Josefo", "Perez", "123456", "123456");
		PO_View.checkElement(driver, "span", "Josefo@email.com");
	}

	/**
	 * Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos).
	 */
	@Test
	public void Prueba2() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "", "", "123456", "123456");
		PO_View.checkElement(driver, "text", "Registrarte");
	}

	/**
	 * [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña inválida).
	 */
	@Test
	public void Prueba3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "Antonio@email.com", "Antonio", "Suarez", "1234567", "123456");
		PO_RegisterView.checkKey(driver, "Error.passwordConfirm.coincidence",
				PO_Properties.getSPANISH() );
	}

	/**
	 * [Prueba4] Registro de Usuario con datos inválidos (email existente).
	 */
	@Test
	public void Prueba4() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "pedro@email.com", "Pedro", "Perez", "123456", "123456");
		PO_RegisterView.checkKey(driver, "Error.email.coincidence",
				PO_Properties.getSPANISH() );
	}

	/**
	 * [Prueba5] Inicio de sesión con datos válidos (administrador).
	 */
	@Test
	public void Prueba5() {
		login("admin@email.com", "admin");
	}

	/**
	 * [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
	 */
	@Test
	public void Prueba6() {
		login("pedro@email.com", "123456");
	}

	/**
	 * [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email y contraseña vacíos).
	 */
	@Test
	public void Prueba7() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "" , "" );
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	/**
	 * [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email existente, pero contraseña incorrecta).
	 */
	@Test
	public void Prueba8() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@email.com" , "123456789" );
		PO_View.checkElement(driver, "h2", "Identifícate");
	}

	/**
	 * [Prueba9] Inicio de sesión con datos inválidos (usuario estándar, email no existente en la aplicación).
	 */
	@Test
	public void Prueba9() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pepe@email.com" , "123456" );
		PO_View.checkElement(driver, "h2", "Identifícate");
	}

	/**
	 * [Prueba10] Hacer click en la opción de salir de sesión y comprobar que se redirige a la página de inicio de sesión (Login).
	 */
	@Test
	public void Prueba10() {
		login("pedro@email.com", "123456");

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	/**
	 * [Prueba11] Comprobar que el botón cerrar sesión no está visible si el usuario no está autenticado.
	 */
	@Test(expected = TimeoutException.class)
	public void Prueba11() {
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	/**
	 * [Prueba12] Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el sistema.
	 */
	@Test
	public void Prueba12() {
		login("admin@email.com", "admin");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Pedro");
		PO_View.checkElement(driver, "text", "Ana");
		PO_View.checkElement(driver, "text", "Jose");
		PO_View.checkElement(driver, "text", "Maria");
		PO_View.checkElement(driver, "text", "Andrea");
	}

	/**
	 * [Prueba13] Ir a la lista de usuarios, borrar el primer usuario de la lista, comprobar que la lista se actualiza y que el usuario desaparece.
	 */
	@Test
	public void Prueba13() {
		login("admin@email.com", "admin");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//input[contains(@id, 'cbox')]");
		int numUsuariosIniciales = elementos.size();
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//button[contains(@id, 'deleteButton')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//input[contains(@id, 'cbox')]");
		int numUsuariosFinales = elementos.size();

		assertEquals(numUsuariosIniciales - 1,  numUsuariosFinales);
	}

	/**
	 * [Prueba14] Ir a la lista de usuarios, borrar el último usuario de la lista, comprobar que la lista se actualiza y que el usuario desaparece.
	 */
	@Test
	public void Prueba14() {
		login("admin@email.com", "admin");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//input[contains(@id, 'cbox')]");
		int numUsuariosIniciales = elementos.size();
		elementos.get(elementos.size() - 1).click();

		elementos = PO_View.checkElement(driver, "free", "//button[contains(@id, 'deleteButton')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//input[contains(@id, 'cbox')]");
		int numUsuariosFinales = elementos.size();

		assertEquals(numUsuariosIniciales - 1,  numUsuariosFinales);
	}

	/**
	 * [Prueba15] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se actualiza y que los usuarios desaparecen.
	 */
	@Test
	public void Prueba15() {
		login("admin@email.com", "admin");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//input[contains(@id, 'cbox')]");
		int numUsuariosIniciales = elementos.size();
		elementos.get(0).click();
		elementos.get(1).click();
		elementos.get(2).click();

		elementos = PO_View.checkElement(driver, "free", "//button[contains(@id, 'deleteButton')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//input[contains(@id, 'cbox')]");
		int numUsuariosFinales = elementos.size();

		assertEquals(numUsuariosIniciales - 3,  numUsuariosFinales);
	}

	/**
	 * [Prueba16] Ir al formulario de alta de oferta, rellenarla con datos válidos y pulsar el botón Submit.
	 *  Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	 */
	@Test
	public void Prueba16() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/add')]");
		elementos.get(0).click();

		fillFormOferta("Ordenador", "Odenador de sobremesa i3", 450);
		
		PO_View.checkElement(driver, "text", "Odenador de sobremesa i3");
	}

	/**
	 * [Prueba17] Ir al formulario de alta de oferta, rellenarla con datos inválidos (campo título vacío) y pulsar el botón Submit. 
	 *  Comprobar que se muestra el mensaje de campo obligatorio.
	 */
	@Test
	public void Prueba17() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/add')]");
		elementos.get(0).click();

		fillFormOferta("", "Odenador sobremesa i3", 450);
	}

	/**
	 * [Prueba18] Mostrar el listado de ofertas para dicho usuario y comprobar que se muestran todas los que existen para este usuario. 
	 */
	@Test
	public void Prueba18() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/list')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Falda larga talla L");
		PO_View.checkElement(driver, "text", "Cuchara de acero inoxidable");
		PO_View.checkElement(driver, "text", "Monitor LED de 24 pulgadas");
		PO_View.checkElement(driver, "text", "Microfono modelo AXD15");
	}

	/**
	 * [Prueba19] Ir a la lista de ofertas, borrar la primera oferta de la lista, comprobar que la lista se actualiza y que la oferta desaparece.
	 */
	@Test
	public void Prueba19() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/list')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/delete/')]");
		int inicio = elementos.size();
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/delete/')]");

		assertEquals(inicio - 1, elementos.size());
	}

	/**
	 * [Prueba20] Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar que la lista se actualiza y que la oferta desaparece.
	 */
	@Test
	public void Prueba20() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/list')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/delete/')]");
		int inicio = elementos.size();
		elementos.get(elementos.size() - 1).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/delete/')]");

		assertEquals(inicio - 1, elementos.size());
	}
	
	/**
	 * [Prueba21] Hacer una búsqueda con el campo vacío y comprobar que se muestra la página que corresponde con el listado de las ofertas existentes en el sistema
	 */
	@Test
	public void Prueba21() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/find')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type, 'submit')]");
		elementos.get(0).click();
		
		PO_View.checkElement(driver, "text", "Cuchara de acero inoxidable");
		PO_View.checkElement(driver, "text", "Monitor LED de 24 pulgadas");
		PO_View.checkElement(driver, "text", "Microfono modelo AXD15");
		PO_View.checkElement(driver, "text", "Funda movil");
		PO_View.checkElement(driver, "text", "Cascos");
		
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		assertEquals("1", elementos.get(1).getText());
	}
	
	/**
	 * [Prueba22] Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar que se muestra la página que corresponde, con la lista de ofertas vacía.
	 */
	@Test
	public void Prueba22() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/find')]");
		elementos.get(0).click();

		WebElement text = driver.findElement(By.name("searchText"));
		text.click();
		text.clear();
		text.sendKeys("wewewewewewe");
		
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type, 'submit')]");
		elementos.get(0).click();
		
		try {
			elementos = PO_View.checkElement(driver, "free", "//td");
			assertTrue(false);
		} catch (TimeoutException e) { assertTrue(true); }
		
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		assertEquals("1", elementos.get(1).getText());
	}
	
	/**
	 * [Prueba23] Sobre una búsqueda determinada (a elección del desarrollador), comprar una oferta que deja un saldo positivo en el contador del comprador. 
	 *  Comprobar que el contador se actualiza correctamente en la vista del comprador.
	 */
	@Test
	public void Prueba23() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/find')]");
		elementos.get(0).click();

		WebElement text = driver.findElement(By.name("searchText"));
		text.click();
		text.clear();
		text.sendKeys("Chicles");
		
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type, 'submit')]");
		elementos.get(0).click();
		
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/buyList/')]");
		elementos.get(0).click();
		
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'saldo')]");
		assertTrue(Double.valueOf(elementos.get(0).getText()) > 0 && Double.valueOf(elementos.get(0).getText()) < 100);
	}
	
	/**
	 * [Prueba24] Sobre una búsqueda determinada (a elección del desarrollador), comprar una oferta que deja un saldo 0 en el contador del comprador. 
	 *  Comprobar que el contador se actualiza correctamente en la vista del comprador. 
	 */
	@Test
	public void Prueba24() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/find')]");
		elementos.get(0).click();

		WebElement text = driver.findElement(By.name("searchText"));
		text.click();
		text.clear();
		text.sendKeys("Vestido");
		
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type, 'submit')]");
		elementos.get(0).click();
		
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/buyList/')]");
		elementos.get(0).click();
		
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'saldo')]");
		System.out.println(elementos.get(0).getText());
		assertTrue(Double.valueOf(elementos.get(0).getText()) == 0);
	}
	
	/**
	 * [Prueba25] Sobre una búsqueda determinada (a elección del desarrollador), intentar comprar una oferta que esté por encima de saldo disponible del comprador. 
	 *  Y comprobar que se muestra el mensaje de saldo no suficiente.
	 */
	@Test
	public void Prueba25() {
		login("pedro@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/find')]");
		elementos.get(0).click();

		WebElement text = driver.findElement(By.name("searchText"));
		text.click();
		text.clear();
		text.sendKeys("Tarjeta");
		
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type, 'submit')]");
		elementos.get(0).click();
		
		elementos = PO_View.checkElement(driver, "free", "//td[contains(@id, 'saldoInsuficiente')]");
	}
	
	/**
	 * [Prueba26] Ir a la opción de ofertas compradas del usuario y mostrar la lista. 
	 *  Comprobar que aparecen las ofertas que deben aparecer.
	 */
	@Test
	public void Prueba26() {
		login("ana@email.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'menu-ofertas')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/oferta/buyList')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Microfono modelo AXD15");
		PO_View.checkElement(driver, "text", "Reloj rolex edicion limitada");
	}
}
