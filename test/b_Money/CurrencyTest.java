package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}
	/**
	 * Testuje poprawność pobierania nazwy waluty.
	 */
	@Test
	public void testGetName() {
		//fail("Write test case here");
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	/**
	 * Testuje poprawność pobierania kursu waluty.
	 */
	@Test
	public void testGetRate() {
		//fail("Write test case here");
		assertEquals(0.15, SEK.getRate(), 0.0001);
		assertEquals(0.20, DKK.getRate(), 0.0001);
		assertEquals(1.5, EUR.getRate(), 0.0001);
	}
	/**
	 * Testuje poprawność ustawiania kursu waluty.
	 * Metoda setRate() ma poprawnie ustawiać nowy kurs dla każdej zdefiniowanej waluty.
	 */
	@Test
	public void testSetRate() {
		//fail("Write test case here");
		SEK.setRate(0.25);
		assertEquals(0.25, SEK.getRate(), 0.001);

		DKK.setRate(0.30);
		assertEquals(0.30, DKK.getRate(), 0.001);

		EUR.setRate(1.8);
		assertEquals(1.8, EUR.getRate(), 0.001);
	}
	/**
	 * Testuje poprawność konwersji kwoty danej waluty na wartość w "uniwersalnej walucie".
	 */
	@Test
	public void testGlobalValue() {
	//	fail("Write test case here");
		assertEquals(15,(int)SEK.universalValue(10000));
		assertEquals(20,(int)DKK.universalValue(10000));
		assertEquals(15,(int)EUR.universalValue(1000));
	}
	/**
	 * Testuje poprawność konwersji kwoty z innej waluty na wartość w danej walucie.
	 */
	@Test
	public void testValueInThisCurrency() {
	//	fail("Write test case here");
		assertEquals(15,(int)SEK.valueInThisCurrency(150,EUR));
	}

}
