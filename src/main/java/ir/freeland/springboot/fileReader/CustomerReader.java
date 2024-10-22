package ir.freeland.springboot.fileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ir.freeland.springboot.entity.Customer;
import ir.freeland.springboot.processor.AccountProcessor;

@Configuration
@EnableWebSecurity
public class CustomerReader {

	@Value("${account.file.name}")
    private String fileName;
    private final CustomerReader processor;

    @Autowired
    public CustomerReader(CustomerReader processor) {
        this.processor = processor;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public Processor<Customer> getProcessor() {
        return processor;
    }
}

