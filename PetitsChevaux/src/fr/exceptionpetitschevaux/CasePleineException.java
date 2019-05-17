package fr.exceptionpetitschevaux;

public class CasePleineException extends Exception{
	
	public CasePleineException(){
		System.out.println("Il y a deja un pion sur cette case");
	}

	public CasePleineException(String message){
		super(message);
	}
	
	public CasePleineException(Throwable cause) {
	    super(cause);
	}
	
	public CasePleineException(Throwable cause,String message) {
	    super(message, cause);
	}

	
	public CasePleineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	}
	
}