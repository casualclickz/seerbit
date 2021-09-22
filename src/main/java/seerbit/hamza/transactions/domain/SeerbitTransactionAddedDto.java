package seerbit.hamza.transactions.domain;

import java.io.Serializable;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* SeerbitTransactionAddedDto seerbit transaction incoming DTO
* 
* @author Hamza Salihu
* 
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeerbitTransactionAddedDto implements Serializable {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Input amount
	 */
	@NonNull
	private String amount;
	
	/**
	 * Input timestamp in ISO_DATE_TIME format
	 */
	@NonNull
	private String timestamp;
}
