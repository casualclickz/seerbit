package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
* SeerbitTransactionPostr posts seerbit transactions
* 
* @author Hamza Salihu
* 
*/
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SeerbitTransactionPostr {
	
	private final SeerbitTransactionRepository seerbitTransactionRepository;
	private final SeerbitTransactionUtils seerbitTransactionUtils;

	/**
	 * <p>This method posts a seerbit transaction at service level</p>
	 * @param SeerbitTransactionAddedDto incoming seerbit transaction dto
	 * @return void
	 * @since 0.0.1-SNAPSHOT
	 */
	public void postTransaction(final SeerbitTransactionAddedDto seerbitTransactionAddedDto) {
		
		SeerbitTransaction seerbitTransaction = SeerbitTransaction.builder()
			.transactionId(seerbitTransactionUtils.getNextTranId())
			.transactionAmount(new BigDecimal(seerbitTransactionAddedDto.getAmount()))
			.transactionTime(seerbitTransactionUtils.parseStringToLocalDateTime(seerbitTransactionAddedDto.getTimestamp()))
			.build();
		seerbitTransactionRepository.addSeerbitTransaction(seerbitTransaction);
	}
}
