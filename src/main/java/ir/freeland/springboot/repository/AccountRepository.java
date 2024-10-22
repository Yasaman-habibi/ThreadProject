package ir.freeland.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import ir.freeland.springboot.entity.Account;


public interface AccountRepository extends CrudRepository<Account, Long> {
		
	}

