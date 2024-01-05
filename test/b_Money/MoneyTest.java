package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}
	/**
	 * Testuję czy motoda getAmount zwraca poprawną wartość
	 */
	@Test
	public void testGetAmount() {
		//fail("Write test case here");
		assertEquals(10000, (int) SEK100.getAmount());
		assertEquals(1000, (int) EUR10.getAmount());
		assertEquals(20000, (int) SEK200.getAmount());
		assertEquals(2000, (int) EUR20.getAmount());
		assertEquals(0, (int) SEK0.getAmount());
		assertEquals(0, (int) EUR0.getAmount());
		assertEquals(-10000, (int) SEKn100.getAmount());
	}
	/**
	 * Testuję czy motoda getCurrency zwraca poprawną wartość
	 */
	@Test
	public void testGetCurrency() {
		//fail("Write test case here");
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
		assertEquals(SEK, SEK0.getCurrency());
		assertEquals(EUR, EUR0.getCurrency());
		assertEquals(SEK, SEKn100.getCurrency());
	}
	/**
	 * Testuję czy motoda toString zwraca poprawną wartość
	 */
	@Test
	public void testToString() {
		//fail("Write test case here");
		assertEquals("100,00 SEK", SEK100.toString());
		assertEquals("20,00 EUR", EUR20.toString());
		assertEquals("-100,00 SEK", SEKn100.toString());
		assertEquals("0,00 EUR", EUR0.toString());
	}
	/**
	 * Testuję czy motoda universalValue w poprawny sposób przelicza na uniwersalną walutę
	 */
	@Test
	public void testGlobalValue() {
		//fail("Write test case here");
		assertEquals(1500,  SEK100.universalValue(), 0.001);
		assertEquals(1500, EUR10.universalValue(), 0.001);
		assertEquals(3000,  SEK200.universalValue(), 0.001);
		assertEquals(3000,  EUR20.universalValue(), 0.001);
		assertEquals(0,  SEK0.universalValue(), 0.001);
		assertEquals(0,  EUR0.universalValue(), 0.001);
		assertEquals(-1500, SEKn100.universalValue(), 0.001);
	}
	/**
	 * Testuję czy motoda equals w poprawny sposób porównała wartości pieniądza w tej samej walucie
	 */
	@Test
	public void testEqualsMoney() {
		//fail("Write test case here");
		assertTrue(SEK100.equals(new Money(10000, SEK)));
		assertFalse(EUR20.equals(new Money(2500, EUR)));
		assertTrue(SEK0.equals(EUR0));
		assertFalse(SEKn100.equals(new Money(-5000, SEK)));
	}
	/**
	 * Testuje poprawność dodawania dwóch obiektów Money.
	 * Dodaję kwotę w walucie SEK do kwoty w walucie EUR
	 * Sprawdzam czy add() poprawnie przeliczy kwotę i zwróci nowy obiekt Money
	 */
	@Test
	public void testAdd() {
		//fail("Write test case here");
		Money result1 = SEK100.add(EUR10);
		assertEquals(10100, (int) result1.getAmount());
		assertEquals(SEK, result1.getCurrency());
	}
	/**
	 * Testuje poprawność odejmowania jednej kwoty Money od drugiej, bez względu na walutę.
	 * Oczekuje, że metoda poprawnie przeliczy i zwróci nowy obiekt z prawidłową
	 * odjętą wartością oraz walutą odpowiadającą obiektowi, na którym wywołano metodę.
	 */
	@Test
	public void testSub() {
		//fail("Write test case here");
		Money result1 = SEK200.sub(SEK100);
		assertEquals(10000, (int) result1.getAmount());
		assertEquals(SEK, result1.getCurrency());

		Money result2 = EUR20.sub(EUR10);
		assertEquals(1000, (int) result2.getAmount());
		assertEquals(EUR, result2.getCurrency());
	}
	/**
	 * Testuje poprawność sprawdzania, czy wartość Money jest równa zero.
	 * Oczekuje, że metoda zwróci true dla obiektu Money o wartości zero,
	 * oraz false dla obiektów Money o wartości różnej od zera.
	 */
	@Test
	public void testIsZero() {
		//fail("Write test case here");
		assertTrue(SEK0.isZero());
		assertFalse(EUR20.isZero());
		assertFalse(SEKn100.isZero());
	}
	/**
	 * Oczekuje, że metoda negate() zwróci nowy obiekt Money z wartością przeciwną
	 * oraz tą samą walutą
	 */
	@Test
	public void testNegate() {
		//fail("Write test case here");
		Money negated1 = SEK100.negate();
		assertEquals(-10000, (int) negated1.getAmount());
		assertEquals(SEK, negated1.getCurrency());

		Money negated2 = EUR20.negate();
		assertEquals(-2000, (int) negated2.getAmount());
		assertEquals(EUR, negated2.getCurrency());
	}
	/**
	 * Testuje poprawność porównywania dwóch obiektów Money.
	 * Oczekuje, że metoda zwróci:
	 * - 0, jeśli wartości Money są równe,
	 * - liczbę ujemną, jeśli obiekt jest mniej wartościowy niż drugi,
	 * - liczbę dodatnią, jeśli obiekt jest bardziej wartościowy niż drugi.
	 */
	@Test
	public void testCompareTo() {
	//	fail("Write test case here");
		assertTrue(SEK100.compareTo(SEK200) < 0);
		assertTrue(EUR20.compareTo(EUR10) > 0);
		assertEquals(0, SEK0.compareTo(EUR0));
		assertTrue(SEKn100.compareTo(SEK0) < 0);
	}
}
