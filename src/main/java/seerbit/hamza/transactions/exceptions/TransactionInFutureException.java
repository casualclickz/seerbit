package seerbit.hamza.transactions.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class TransactionInFutureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
