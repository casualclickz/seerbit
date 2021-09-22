package seerbit.hamza.transactions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import seerbit.hamza.transactions.TransactionsApplication;
import seerbit.hamza.transactions.domain.SeerbitTransactionUtils;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TransactionsApplication.class})
public class SeerbitTransactionControllerBadRequestTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
	
	@Autowired MockMvc mockMvc;
	@Autowired SeerbitTransactionUtils seerbitTransactionUtils;
	
	ObjectMapper mapper;
	ObjectWriter ow;
	
	@BeforeEach
	void setUp() {

		this.mapper = new ObjectMapper();
		this.mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		this.ow = this.mapper.writer().withDefaultPrettyPrinter();
	}
	
	@Test
	public void seerbitTransactionControllerBadRequestTest() throws Exception {
		
		Map<String, String> transactionDto = new LinkedHashMap<>();
		transactionDto.put("amount","12.3343");
		transactionDto.put("timestamp",seerbitTransactionUtils.formatLocalDateTimeToISODATETIMEFormat(LocalDateTime.now()));
		
		String requestJson = this.ow.writeValueAsString(transactionDto).substring(1);
		
		mockMvc.perform(post("/transaction").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print()).andExpect(status().isBadRequest());
	}
}