package seerbit.hamza.transactions.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
* SeerbitTransactionsCleanr deletes all seerbit transactions
* 
* @author Hamza Salihu
* 
*/
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SeerbitTransactionsCleanr {
	
	private final SeerbitTransactionRepository seerbitTransactionRepository;

	/**
	 * <p>This method deletes all seerbit transactions at service level</p>
	 * @return void
	 * @since 0.0.1-SNAPSHOT
	 */
	public void deleteTransactions() {
		
		seerbitTransactionRepository.deleteTransactions();
	}
}
