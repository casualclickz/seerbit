package seerbit.hamza.transactions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import seerbit.hamza.transactions.domain.SeerbitTransaction;
import seerbit.hamza.transactions.domain.SeerbitTransactionRepository;
import seerbit.hamza.transactions.domain.SeerbitTransactionUtils;

@RunWith(SpringRunner.class)
public class SeerbitTransactionRepositoryTest {
	
	@Test
	public void seerbitTransactionRepositoryTest() {
		
		SeerbitTransactionUtils seerbitTransactionUtils = new SeerbitTransactionUtils();
		
		SeerbitTransactionRepository seerbitTransactionRepository = new SeerbitTransactionRepository();
		seerbitTransactionRepository.addSeerbitTransaction(SeerbitTransaction.builder()
				.transactionAmount(BigDecimal.valueOf(12.00))
				.transactionTime(seerbitTransactionUtils.parseStringToLocalDateTime("2018-07-17T09:59:51.312Z"))
				.transactionId(String.valueOf(System.currentTimeMillis()))
			.build());
		
		assertTrue(1 == seerbitTransactionRepository.getSeerbitTransactions().size(), "Not equal");
	}
}