package seerbit.hamza.transactions.domain;

import java.io.Serializable;

import org.springframework.lang.NonNull;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
* SeerbitTransactionsStatisticsDto statistics outgoing dto
* 
* @author Hamza Salihu
* 
*/
@Getter
@Builder
@ToString
public final class SeerbitTransactionsStatisticsDto implements Serializable {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Computed sum transaction
	 */
	@NonNull
	private final String sum;
	
	/**
	 * Computed average transaction
	 */
	@NonNull
	private final String avg;
	
	/**
	 * Computed maximum transaction
	 */
	@NonNull
	private final String max;
	
	/**
	 * Computed minimum transaction
	 */
	@NonNull
	private final String min;
	
	/**
	 * Count of transactions
	 */
	@NonNull
	private final long count;
}
