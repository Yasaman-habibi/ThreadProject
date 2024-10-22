package ir.freeland.springboot.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import ir.freeland.springboot.entity.Account;
import ir.freeland.springboot.entity.Customer;
import ir.freeland.springboot.exception.ErrorWriter;
import ir.freeland.springboot.validation.EntityValidation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.stream.Stream;


public class AccountProcessor extends Processor<Account> {

	@Override
    public String getFileName() {
        return "accounts.csv"; 
    }

	
	
    @Override
    public EntityValidation<Account> getValidation(String line) {
        
    	
    	final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	
    	
    	//Separate with ","
        String[] parts = line.split(",");
        //Customer[] parts = line.split(",")
        
        if (parts.length != 6) {
            return new EntityValidation<>(null, true, "Invalid line format", 0);
        }
        String accountNumber = parts[0];
        
        String accountType = parts[1];
        
       
        Customer customer;
        try {
        	customer = Customer.parse(parts[2]);
        } catch (text.ParseException e) {
            return new EntityValidation<>(null, true, "Invalid customer format", 0);
        }
        
        
        String accountBalance = parts[3];
        
        LocalDate accountOpenDate;
        try {
            accountOpenDate = LocalDate.parse(parts[4], DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return new EntityValidation<>(null, true, "Invalid date format", 0);
        }
        
        Long balanceLimit;
        try {
            balanceLimit = Long.parseLong(parts[5]);
        } catch (NumberFormatException e) {
            return new EntityValidation<>(null, true, "Invalid balance limit format", 0);
        }
 
        
        Account account = new Account( accountNumber,  accountType , customer , 
        		                       accountBalance , accountOpenDate ,  balanceLimit);
        
        return new EntityValidation<>(account, false, null, 0);
    }

    
    
    @Override
    public ErrorWriter getErrorWriter() {
        return new ErrorWriter(); 
    }

    
    
    @Override
    public void saveEntity(Account entity) {
       
        System.out.println("Account saved: " + entity);
    }

    
    
    public static void main(String[] args) {
        AccountProcessor processor = new AccountProcessor();
        CountDownLatch latch = new CountDownLatch(1);
        processor.start(latch);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processor.getErrorWriter().writeErrors();
    }
}
