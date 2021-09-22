package seerbit.hamza.transactions.domain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import seerbit.hamza.transactions.exceptions.TransactionExpiredException;
import seerbit.hamza.transactions.exceptions.TransactionInFutureException;

/**
* SeerbitTransactionValidator provide validation for incoming seerbit transactions
* 
* @author Hamza Salihu
* 
*/
@Component
@RequiredArgsConstructor
public class SeerbitTransactionValidator {
	
	@Value("${transaction[time.cap.seconds]:30}")
	private long transactionTimeCap;
	
	private final SeerbitTransactionUtils seerbitTransactionUtils;

	public void validate(SeerbitTransactionAddedDto seerbitTransactionAddedDto) {
		
		LocalDateTime timestamp = seerbitTransactionUtils.parseStringToLocalDateTime(seerbitTransactionAddedDto.getTimestamp());
		
		long transactionTimeAgoInSeconds = seerbitTransactionUtils.currentTimeSeconds() - seerbitTransactionUtils.convertLocalDateTimeToSeconds(timestamp);
		if(transactionTimeAgoInSeconds > transactionTimeCap) {
			
			throw new TransactionExpiredException();
		}

		if(seerbitTransactionUtils.convertLocalDateTimeToSeconds(timestamp) > seerbitTransactionUtils.currentTimeSeconds()) {
			
			throw new TransactionInFutureException();
		}
	}

}
