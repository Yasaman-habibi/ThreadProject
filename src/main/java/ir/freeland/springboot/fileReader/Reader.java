package ir.freeland.springboot.fileReader;

import java.io.BufferedReader;
import java.io.IOException;

import ir.freeland.springboot.exception.ErrorWriter;
import ir.freeland.springboot.validation.EntityValidation;

import java.io.FileReader;


public abstract class Reader<T> {

    public abstract String getFileName();

    public abstract Processor<T> getProcessor();

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(getFileName()))) {
        	
            String line;
            while ((line = br.readLine()) != null) {
                EntityValidation<T> validation = getProcessor().processLine(line);
                if (!validation.hasError()) {
                    saveEntity(validation.getEntity());
                } else {
                    getErrorWriter().writeError(validation.getErrorMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void saveEntity(T entity);

    public abstract ErrorWriter getErrorWriter();
}
    public interface Processor<T> {
        T processLine(String line);
        void saveEntity(T entity);
    }
