package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
* SeerbitTransactionsMin computes minimum seerbit transaction
* 
* @author Hamza Salihu
* 
*/
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SeerbitTransactionsMin implements SeerbitTransactionsStatistics {
	
	private final SeerbitTransactionRepository seerbitTransactionRepository;
	private final SeerbitTransactionUtils seerbitTransactionUtils;

	/**
	 * <p>This method computes minimum seerbit transactions at service level</p>
	 * @return BigDecimal
	 * @since 0.0.1-SNAPSHOT
	 */
	@Override
	public BigDecimal computeStatistics() {
		
		long count = seerbitTransactionRepository.getSeerbitTransactionsCount();
		
		BigDecimal min = count > 0 ? BigDecimal.valueOf(Double.MAX_VALUE) : BigDecimal.valueOf(0D);
		for(SeerbitTransaction transaction : seerbitTransactionRepository.getSeerbitTransactions()) {
			if(transaction.getTransactionAmount().compareTo(min) < 0) min = transaction.getTransactionAmount();
		}
		return seerbitTransactionUtils.toTwoDecimalHalfRoundUp(min);
	}
}
