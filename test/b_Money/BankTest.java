package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}
	/**
	 * Testuje poprawność pobierania nazwy banku.
	 */
	@Test
	public void testGetName() {
		//fail("Write test case here");
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}
	/**
	 * Testuje poprawność pobierania waluty banku.
	 */
	@Test
	public void testGetCurrency() {
		//fail("Write test case here");
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
	}
	/**
	 * Testuje poprawność otwierania konta w banku.
	 * Oczekuje, że metoda utworzy nowe konto dla unikalnego identyfikatora i rzuci wyjątkiem
	 * AccountExistsException, jeśli konto o podanym identyfikatorze już istnieje.
	 */
	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
	//	fail("Write test case here");
		assertTrue(SweBank.accountExists("Ulrika"));
	}
	/**
	 * Testuje poprawność wpłacania środków na określone konto w banku.
	 */
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		//fail("Write test case here");
		SweBank.deposit("Ulrika", new Money(10000, SEK));
		assertEquals(Integer.valueOf(10000), SweBank.getBalance("Ulrika"));
	}
	/**
	 * Testuje poprawność wypłacania środków z konta w banku.
	 * Oczekuje, że metoda withdraw() poprawnie wypłaci określoną kwotę z konta.
	 */
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		//fail("Write test case here");
		SweBank.deposit("Ulrika", new Money(10000, SEK));
		SweBank.withdraw("Ulrika", new Money(5000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Ulrika"));
	}
	/**
	 * Testuje poprawność pobierania salda konta w banku.
	 */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		//fail("Write test case here");
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
	}
	/**
	 * Testuje poprawność transferu środków między kontami w różnych bankach.
	 * Oczekuje, że metoda  poprawnie przekroczy środki z jednego konta do drugiego.
	 */
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		//fail("Write test case here");
		SweBank.deposit("Ulrika", new Money(10000, SEK));
		SweBank.transfer("Ulrika", Nordea, "Bob", new Money(5000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(5000), Nordea.getBalance("Bob"));
	}
	/**
	 * Testuje poprawność planowanego, cyklicznego transferu środków między kontami w tym samym banku.
	 * Oczekuje, że metoda addTimedPayment() poprawnie utworzy cykliczny transfer, a metoda tick() go zrealizuje.
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		//fail("Write test case here");
		SweBank.deposit("Ulrika", new Money(100000, SEK));
		SweBank.addTimedPayment("Ulrika", "payment1", 1, 1, new Money(1000, SEK), SweBank, "Bob");
		SweBank.tick(); // Simulate time passing
		assertEquals(Integer.valueOf(99000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(0), Nordea.getBalance("Bob"));
	}
}
