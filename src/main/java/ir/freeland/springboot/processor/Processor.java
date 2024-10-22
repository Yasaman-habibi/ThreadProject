package ir.freeland.springboot.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ir.freeland.springboot.entity.Account;
import ir.freeland.springboot.exception.Error;
import ir.freeland.springboot.exception.ErrorWriter;
import ir.freeland.springboot.validation.EntityValidation;


public abstract class Processor<T> {

   
    public abstract String getFileName();

   
    public abstract EntityValidation<T> getValidation(String line);

  
    public abstract ErrorWriter getErrorWriter();

 
    public abstract void saveEntity(T entity);

 
    public void processLine(String line) {
        EntityValidation<T> validation = getValidation(line);
        importOrError(validation);
    }

   
    public void importOrError(EntityValidation<T> validation) {
        if (validation.hasError()) {
            errorHandling(validation, "400", "Validation", validation.getFieldErrors().toString());
        } else {
            try {
                saveEntity(validation.getValidated());
            } catch (Exception exception) {
                errorHandling(validation, "500", "SQL", "Database exception on saving record");
            }
        }
    }

    
    
    //manage errors
    public void errorHandling(EntityValidation<T> validation, String errorCode,
    		                                   String errorClassificationName, String errorDescription) {
        Error error = new Error(
                getFileName(), validation.getRecordNumber()
                , errorCode, errorClassificationName, errorDescription, 
                LocalDate.now().toString());

        getErrorWriter().addError(error);
    }
    
   //start process the file
    public void start(CountDownLatch threadCount) {
        try (Stream<String> fileStream = Files.lines(Path.of(getFileName()))) {
            fileStream.parallel().forEach(this::processLine);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadCount.countDown();
        }
    }
}