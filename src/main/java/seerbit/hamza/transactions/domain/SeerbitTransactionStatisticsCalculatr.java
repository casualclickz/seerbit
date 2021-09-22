package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
* SeerbitTransactionStatisticsCalculatr collates seerbit transactions statistics
* 
* @author Hamza Salihu
* 
*/
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SeerbitTransactionStatisticsCalculatr {
	
	private final SeerbitTransactionsSum seerbitTransactionsSum;
	private final SeerbitTransactionsAverage seerbitTransactionsAverage;
	private final SeerbitTransactionsMin seerbitTransactionsMin;
	private final SeerbitTransactionsMax seerbitTransactionsMax;
	
	private final SeerbitTransactionUtils seerbitTransactionsUtils;
	
	private final SeerbitTransactionRepository seerbitTransactionRepository;

	/**
	 * <p>This method collates computed seerbit transactions statistics at service level</p>
	 * @return SeerbitTransactionsStatisticsDto
	 * @since 0.0.1-SNAPSHOT
	 */
	public SeerbitTransactionsStatisticsDto getStatistics() {
		
		BigDecimal sum = BigDecimal.valueOf(0D);
		BigDecimal average = BigDecimal.valueOf(0D);
		BigDecimal min = BigDecimal.valueOf(0D);
		BigDecimal max = BigDecimal.valueOf(0D);
		long count = 0;
		
		for(Statistics statistic : Statistics.values()) {
			
			switch(statistic) {
			
				case SUM : {
					
					sum = seerbitTransactionsSum.computeStatistics();
					break;
				}
				
				case AVERAGE : {
					
					average = seerbitTransactionsAverage.computeStatistics();
					break;
				}
				
				case MIN : {
					
					min = seerbitTransactionsMin.computeStatistics();
					break;
				}
				
				case MAX : {
					
					max = seerbitTransactionsMax.computeStatistics();
					break;
				}
				
				default : {
					
					count = seerbitTransactionRepository.getSeerbitTransactionsCount();
				}
			}
		}
		
		return SeerbitTransactionsStatisticsDto.builder()
			.sum(seerbitTransactionsUtils.toTwoDecimalHalfRoundUp(sum).toString())
			.avg(seerbitTransactionsUtils.toTwoDecimalHalfRoundUp(average).toString())
			.min(seerbitTransactionsUtils.toTwoDecimalHalfRoundUp(min).toString())
			.max(seerbitTransactionsUtils.toTwoDecimalHalfRoundUp(max).toString())
			.count(count)
		.build();
	}
	
	private enum Statistics {
		
		SUM,AVERAGE,MIN,MAX,COUNT;
	}
}
