package seerbit.hamza.transactions.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import seerbit.hamza.transactions.exceptions.TransactionExpiredException;
import seerbit.hamza.transactions.exceptions.TransactionInFutureException;
import seerbit.hamza.transactions.exceptions.TransactionNotParsableException;

/**
* SeerbitTransactionController seerbit transaction rest api controller
* 
* @author Hamza Salihu
* 
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
@Transactional(rollbackFor = Exception.class)
public class SeerbitTransactionController {
	
	private final SeerbitTransactionPostr seerbitTransactionPostr;	
	private final SeerbitTransactionValidator seerbitTransactionValidator;
	private final SeerbitTransactionStatisticsCalculatr seerbitTransactionStatisticsCalculatr;
	private final SeerbitTransactionsCleanr seerbitTransactionsCleanr;
	
	/**
	 * <p>This method posts a seerbit transaction</p>
	 * @param SeerbitTransactionAddedDto incoming seerbit transaction dto
	 * @return ResponseEntity<Void>
	 * @since 0.0.1-SNAPSHOT
	 */
	@PostMapping
	public ResponseEntity<Void> post(@NonNull @RequestBody SeerbitTransactionAddedDto seerbitTransactionAddedDto) {
		
		seerbitTransactionValidator.validate(seerbitTransactionAddedDto);
		seerbitTransactionPostr.postTransaction(seerbitTransactionAddedDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
	 * <p>This method get the seerbit transaction statistics</p>
	 * @return SeerbitTransactionsStatisticsDto
	 * @since 0.0.1-SNAPSHOT
	 */
	@GetMapping
	public SeerbitTransactionsStatisticsDto get() {

		return seerbitTransactionStatisticsCalculatr.getStatistics();
	}
	
	/**
	 * <p>This method clears existing seerbit transaction</p>
	 * @return ResponseEntity<Void>
	 * @since 0.0.1-SNAPSHOT
	 */
	@DeleteMapping
	public ResponseEntity<Void>  delete() {

		seerbitTransactionsCleanr.deleteTransactions();
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@ExceptionHandler(TransactionExpiredException.class)
	public void transactionExpiredException() {
		// no message
	}
	
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(TransactionNotParsableException.class)
	public void transactionNotParsableException() {
		// no message
	}
	
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(TransactionInFutureException.class)
	public void transactionInFutureException() {
		// no message
	}
}
