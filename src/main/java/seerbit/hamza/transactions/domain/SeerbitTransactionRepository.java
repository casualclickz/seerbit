package seerbit.hamza.transactions.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
* SeerbitTransactionRepository seerbit transaction repository
* 
* @author Hamza Salihu
* 
*/
@Repository
public class SeerbitTransactionRepository {

	private List<SeerbitTransaction> transactions = new ArrayList<>();
	
	/**
	 * <p>This method add a seerbit transaction to the repository</p>
	 * @param SeerbitTransaction seerbit transaction
	 * @return void
	 * @since 0.0.1-SNAPSHOT
	 */
	public void addSeerbitTransaction(SeerbitTransaction seerbitTransaction) {
		
		transactions.add(seerbitTransaction);
	}
	
	/**
	 * <p>This method retrieves seerbit transaction from the repository</p>
	 * @return List<SeerbitTransaction>
	 * @since 0.0.1-SNAPSHOT
	 */
	public List<SeerbitTransaction> getSeerbitTransactions() {
		
		return Collections.unmodifiableList(transactions);
	}
	
	/**
	 * <p>This method retrieves count of seerbit transaction from the repository</p>
	 * @return long
	 * @since 0.0.1-SNAPSHOT
	 */
	public long getSeerbitTransactionsCount() {
		
		return transactions.size();
	}
	
	/**
	 * <p>This method retrieves clears the repository of seerbit transaction</p>
	 * @return void
	 * @since 0.0.1-SNAPSHOT
	 */
	public void deleteTransactions() {
		
		transactions.clear();
	}
}
