package ir.freeland.springboot.exception;


public class Error {

	private String fileName;
    private int recordNumber;
    private String errorCode;
    private String errorClassificationName;
    private String errorDescription;
    private String errorDate;

    public Error(String fileName, int recordNumber, String errorCode, 
    		     String errorClassificationName, String errorDescription,
    		     String errorDate) {
    	
        this.fileName = fileName;
        this.recordNumber = recordNumber;
        this.errorCode = errorCode;
        this.errorClassificationName = errorClassificationName;
        this.errorDescription = errorDescription;
        this.errorDate = errorDate;
    }

    
    @Override
    public String toString() {
        return "Error{" +
                "fileName='" + fileName  + ", recordNumber=" + recordNumber +
                ", errorCode='" + errorCode  + ", errorClassificationName='" + errorClassificationName  +
                ", errorDescription='" + errorDescription  +  ", errorDate='" + errorDate  + '}';
    }
}