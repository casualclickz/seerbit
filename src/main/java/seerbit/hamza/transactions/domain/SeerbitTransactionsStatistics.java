package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;

/**
* SeerbitTransactionsStatistics seerbit transactions compute interface
* 
* @author Hamza Salihu
* 
*/
public interface SeerbitTransactionsStatistics {

	/**
	 * <p>This method is contract for compute implementations</p>
	 * @return BigDecimal
	 * @since 0.0.1-SNAPSHOT
	 */
	BigDecimal computeStatistics();
}
