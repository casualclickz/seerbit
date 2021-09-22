package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;


/**
* SeerbitTransactionsMax computes maximum seerbit transactions
* 
* @author Hamza Salihu
* 
*/
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SeerbitTransactionsMax implements SeerbitTransactionsStatistics {
	
	private final SeerbitTransactionRepository seerbitTransactionRepository;
	private final SeerbitTransactionUtils seerbitTransactionUtils;

	/**
	 * <p>This method computes maximum seerbit transactions at service level</p>
	 * @return BigDecimal
	 * @since 0.0.1-SNAPSHOT
	 */
	@Override
	public BigDecimal computeStatistics() {
		BigDecimal max = BigDecimal.valueOf(Double.MIN_VALUE);
		for(SeerbitTransaction transaction : seerbitTransactionRepository.getSeerbitTransactions()) {
			if(transaction.getTransactionAmount().compareTo(max) > 0) max = transaction.getTransactionAmount();
		}
		return seerbitTransactionUtils.toTwoDecimalHalfRoundUp(max);
	}
}
