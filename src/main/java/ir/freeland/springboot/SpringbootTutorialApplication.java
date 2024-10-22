package ir.freeland.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.ApplicationContext;
import ir.freeland.springboot.fileReader.AccountReader;
import ir.freeland.springboot.fileReader.CustomerReader;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringbootTutorialApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringbootTutorialApplication.class, args);

		
		CustomerReader customerReader = context.getBean(CustomerReader.class);
		AccountReader accountReader = context.getBean(AccountReader.class);
	}
}