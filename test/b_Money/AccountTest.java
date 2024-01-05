package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	/**
	 * Testuje poprawność dodawania i usuwania planowanego, cyklicznego transferu środków na koncie.
	 * Metoda addTimedPayment() ma poprawnie utworzyć cykliczny transfer, a metoda removeTimedPayment()
	 * poprawnie usunie go z listy planowanych transferów.
	 */
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("payment1", 2, 1, new Money(1000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("payment1"));
		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment1"));
	}
	/**
	 * Testuje poprawność planowanego, cyklicznego transferu środków między kontami w różnych bankach.
	 * Oczekuje, że metoda addTimedPayment() poprawnie utworzy cykliczny transfer, a metoda tick() go zrealizuje.
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("payment1", 2, 1, new Money(1000, SEK), SweBank, "Alice");
		testAccount.tick();
		testAccount.tick();
		assertEquals(new Money(1001000, SEK).getAmount(), SweBank.getBalance("Alice"));
	}
	/**
	 * Testuje poprawność wpłacania i wypłacania środków z konta.
	 */
	@Test
	public void testAddWithdraw() {
		testAccount.deposit(new Money(5000, SEK));
		assertEquals(new Money(10005000, SEK).toString(), testAccount.getBalance().toString());
		testAccount.withdraw(new Money(2000, SEK));
		assertEquals(new Money(10003000, SEK).toString(), testAccount.getBalance().toString());
	}
	/**
	 * Testuje poprawność pobierania salda konta.
	 */
	@Test
	public void testGetBalance() {
		assertEquals(new Money(10000000, SEK).toString(), testAccount.getBalance().toString());
	}
}