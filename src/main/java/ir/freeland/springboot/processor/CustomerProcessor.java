package ir.freeland.springboot.processor;

import ir.freeland.springboot.entity.Customer;
import ir.freeland.springboot.exception.ErrorWriter;
import ir.freeland.springboot.validation.EntityValidation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.CountDownLatch;


public class CustomerProcessor extends Processor<Customer> {

	    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    @Override
	    public String getFileName() {
	        return "customers.csv"; 
	    }

	    @Override
	    public EntityValidation<Customer> getValidation(String line) {
	        
	    	
	        String[] parts = line.split(",");
	        if (parts.length != 6) {
	            return new EntityValidation<>(null, true, "Invalid line format", 0);
	        }
	        String customerName = parts[0];
	        String customerSurname = parts[1];
	        String customerAddress = parts[2];
	        String customerZipCode = parts[3];
	        LocalDate customerBirthDate;
	        try {
	            customerBirthDate = LocalDate.parse(parts[4], DATE_FORMATTER);
	        } catch (DateTimeParseException e) {
	            return new EntityValidation<>(null, true, "Invalid date format", 0);
	        }
	        String customerNationalId = parts[5];
	        
	        Customer customer = new Customer(customerName, customerSurname, customerAddress, 
	        		      customerZipCode, java.sql.Date.valueOf(customerBirthDate), customerNationalId);
	        return new EntityValidation<>(customer, false, null, 0);
	    }

	    
	    @Override
	    public ErrorWriter getErrorWriter() {
	        return new ErrorWriter(); 
	    }

	    
	    @Override
	    public void saveEntity(Customer entity) {
	        
	        System.out.println("Customer saved: " + entity);
	    }

	    public static void main(String[] args) {
	        CustomerProcessor processor = new CustomerProcessor();
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