package ir.freeland.springboot.fileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ir.freeland.springboot.entity.Account;
import ir.freeland.springboot.processor.AccountProcessor;


	@Component
	public class AccountReader extends Reader<Account> {

		@Value("${account.file.name}")
	    private String fileName;
	    private final AccountProcessor processor;

	    @Autowired
	    public AccountReader(AccountProcessor processor) {
	        this.processor = processor;
	    }

	    @Override
	    public String getFileName() {
	        return fileName;
	    }

	    @Override
	    public Processor<Account> getProcessor() {
	        return processor;
	    }
	}