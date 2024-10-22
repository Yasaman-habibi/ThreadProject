package ir.freeland.springboot.exception;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class ErrorWriter {

	@Value("${errors.file.json.fileName}")
	private String errorsFileName;
	private final ObjectMapper objectMapper;
	private final Set<Error> errors = new HashSet<>();

	
	public ErrorWriter() {
	    this.objectMapper = new ObjectMapper();
	    this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	
	public synchronized void addError(Error error) {
	    errors.add(error);
	}

	
	public void writeErrors() {
	    try {
	        objectMapper.writeValue(new File(errorsFileName), errors);
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to write errors to file: " + errorsFileName, e);
	    }
	}

	
	public Set<Error> getErrors() {
	    return errors;
	}
	
	public void writeError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }
}


