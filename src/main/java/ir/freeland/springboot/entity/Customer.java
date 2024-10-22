package ir.freeland.springboot.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	    @Id
	    @GeneratedValue( strategy = GenerationType.IDENTITY)
	    private long customerId;

	    private String customerName;

	    private String customerSurname;

	    private String customerAddress;

	    private String customerZipCode;

	    private String customerNationalId;

	    private Date customerBirthDate;
	    
	    
	    public Customer() {}
		
		
		public Customer(String customerName, String customerSurname , String customerAddress
				       ,String customerZipCode , Date customerBirthDate , String customerNationalId) {
		
		       this.customerName = customerName;
		       this.customerSurname = customerSurname;
		       this.customerAddress = customerAddress;
		       this.customerZipCode = customerZipCode;
		       this.customerBirthDate = customerBirthDate;
		       this.customerNationalId = customerNationalId;
		       
	      }
		
		
		public long getCustomerId() {
			return customerId;
		}

		public void setCustomerId(long customerId) {
			this.customerId = customerId;
		}
		
		
		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		
		
		public String getCustomerSurname() {
			return customerSurname;
		}

		public void setCustomerSurname(String customerSurname) {
			this.customerSurname = customerSurname;
		}
		
		
		public String getCustomerAddress() {
			return customerAddress;
		}

		public void setCustomerAddress(String customerAddress) {
			this.customerAddress = customerAddress;
		}
		
		
		public String getCustomerZipCode() {
			return customerZipCode;
		}

		public void setCustomerZipCode(String customerZipCode) {
			this.customerZipCode = customerZipCode;
		}
		
		
		public String getCustomerNationalId() {
			return customerNationalId;
		}

		public void setCustomerNationalId(String customerNationalId) {
			this.customerNationalId = customerNationalId;
		}
		
		
		public Date getCustomerBirthDate() {
			return customerBirthDate;
		}

		public void setCustomerBirthDate(Date customerBirthDate) {
			this.customerBirthDate = customerBirthDate;
		}
		
		
		
		@Override
		public String toString() {
			return "Customer{ + customerId = " + customerId + ", customerName = " + customerName
					+ ", customerSurname = " + customerSurname + ", customerAddress = " + customerAddress
					+ ", customerZipCode = " + customerZipCode + ", customerNationalId = " + customerNationalId
					+ ", customerBirthDate = " + customerBirthDate + "}" ;
					
		}
    

}
