package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
* SeerbitTransactionsSum computes seerbit transactions total sum
* 
* @author Hamza Salihu
* 
*/
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SeerbitTransactionsSum implements SeerbitTransactionsStatistics {
	
	private final SeerbitTransactionRepository seerbitTransactionRepository;

	/**
	 * <p>This method computes seerbit transactions total sum at service level</p>
	 * @return BigDecimal
	 * @since 0.0.1-SNAPSHOT
	 */
	@Override
	public BigDecimal computeStatistics() {
		BigDecimal total = BigDecimal.valueOf(0D);
		for(SeerbitTransaction transaction : seerbitTransactionRepository.getSeerbitTransactions()) {
			total = total.add(transaction.getTransactionAmount());
		}
		return total;
	}
}
