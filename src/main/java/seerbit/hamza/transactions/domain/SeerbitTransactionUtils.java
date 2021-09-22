package seerbit.hamza.transactions.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import seerbit.hamza.transactions.exceptions.TransactionNotParsableException;

/**
* SeerbitTransactionUtils provide utility methods
* 
* @author Hamza Salihu
* 
*/
@Component
public class SeerbitTransactionUtils {

	public String getNextTranId() {
		
		return String.valueOf(System.currentTimeMillis());
	}
	
	public LocalDateTime parseStringToLocalDateTime(String dateString) {
		
		try {
			DateTimeFormatter isoDateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;isoDateTimeFormatter.format(LocalDateTime.now());
			return LocalDateTime.parse(dateString, isoDateTimeFormatter);
		} catch (DateTimeParseException e) {
			throw new TransactionNotParsableException();
        }
	}
	
	public String formatLocalDateTimeToISODATETIMEFormat(LocalDateTime localDateTime) {
		
		DateTimeFormatter isoDateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
		return isoDateTimeFormatter.format(Optional.ofNullable(localDateTime).orElse(LocalDateTime.now()));
	}
	
	public long convertLocalDateTimeToSeconds(LocalDateTime localDateTime) {
		
		return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
	}
	
	public long currentTimeSeconds() {
		
		return System.currentTimeMillis() / 1000;
	}
	
	public BigDecimal toTwoDecimalHalfRoundUp(BigDecimal bigDecimal) {
		
		return bigDecimal.setScale(2, RoundingMode.HALF_UP);
	}
}
