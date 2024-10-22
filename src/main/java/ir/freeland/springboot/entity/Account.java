package ir.freeland.springboot.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private Long accountId;
	
	@Id
	@GeneratedValue
	private String accountNumber;
	
	@Id
	@GeneratedValue
	private String accountType;
	
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	private String accountBalance;
	private LocalDate accountOpenDate;
	private Long balanceLimit;
	
	
	
	public Account() {}
	
	
	public Account(String accountNumber, String accountType , Customer customer
			       ,String accountBalance , LocalDate accountOpenDate , Long balanceLimit) {
	
	       this.accountNumber = accountNumber;
	       this.accountType = accountType;
	       this.customer = customer;
	       this.accountBalance = accountBalance;
	       this.accountOpenDate = accountOpenDate;
	       this.balanceLimit = balanceLimit;
	       
      }
	
	
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	public long getBalanceLimit() {
		return balanceLimit;
	}

	public void setBalanceLimit(long balanceLimit) {
		this.balanceLimit = balanceLimit;
	}
	
	
	public LocalDate getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(LocalDate accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}
	
	
	
	@Override
	public String toString() {
		return "Account{ + accountId = " + accountId + ", accountNumber = " + accountNumber
				+ ", accountType = " + accountType + ", customer = " + customer
				+ ", accountBalance = " + accountBalance + ", accountOpenDate = " +accountOpenDate
				+ ", balanceLimit = " + balanceLimit + "}" ;
				
	}


}
