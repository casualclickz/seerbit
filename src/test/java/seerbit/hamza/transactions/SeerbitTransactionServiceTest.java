package seerbit.hamza.transactions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import seerbit.hamza.transactions.TransactionsApplication;
import seerbit.hamza.transactions.domain.SeerbitTransactionAddedDto;
import seerbit.hamza.transactions.domain.SeerbitTransactionPostr;
import seerbit.hamza.transactions.domain.SeerbitTransactionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TransactionsApplication.class})
public class SeerbitTransactionServiceTest {
	
	@Autowired SeerbitTransactionPostr seerbitTransactionPostr;
	@Autowired SeerbitTransactionRepository seerbitTransactionRepository;
	
	@Test
	public void seerbitTransactionServiceTest() {
		
		int initSize = seerbitTransactionRepository.getSeerbitTransactions().size();
		
		SeerbitTransactionAddedDto seerbitTransactionAddedDto = new SeerbitTransactionAddedDto();
		seerbitTransactionAddedDto.setAmount("12.00");
		seerbitTransactionAddedDto.setTimestamp("2018-07-17T09:59:51.312Z");
		
		seerbitTransactionPostr.postTransaction(seerbitTransactionAddedDto);
		
		int newSize = seerbitTransactionRepository.getSeerbitTransactions().size();
		
		assertTrue(newSize > initSize);
	}
}