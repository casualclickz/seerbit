package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
* SeerbitTransaction seerbit transaction model
* 
* @author Hamza Salihu
* 
*/
@Getter
@Builder
@ToString
public class SeerbitTransaction {

	/**
	 * Transaction id
	 */
	private final String transactionId;
	/**
	 * Transaction amount
	 */
	private final BigDecimal transactionAmount;
	/**
	 * Transaction time
	 */
	private final LocalDateTime transactionTime;
}
