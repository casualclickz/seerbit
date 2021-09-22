package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
* SeerbitTransactionsAverage computes seerbit transactions average
* 
* @author Hamza Salihu
* 
*/
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SeerbitTransactionsAverage implements SeerbitTransactionsStatistics {
	
	private final SeerbitTransactionRepository seerbitTransactionRepository;
	
	/**
	 * <p>This method computes seerbit transactions average at service level</p>
	 * @return BigDecimal
	 * @since 0.0.1-SNAPSHOT
	 */
	@Override
	public BigDecimal computeStatistics() {
		BigDecimal total = BigDecimal.valueOf(0D);
		for(SeerbitTransaction transaction : seerbitTransactionRepository.getSeerbitTransactions()) {
			total = total.add(transaction.getTransactionAmount());
		}
		long seerbitTransactionsCount = seerbitTransactionRepository.getSeerbitTransactionsCount();
		seerbitTransactionsCount = (seerbitTransactionsCount != 0) ? seerbitTransactionsCount : 1;
		return total.divide(BigDecimal.valueOf(seerbitTransactionsCount), RoundingMode.HALF_UP);
	}
}
