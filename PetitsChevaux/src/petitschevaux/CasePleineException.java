package petitschevaux;

public class CasePleineException extends Exception{
	
	CasePleineException(){
		System.out.println("Il y a deja un pion sur cette case");
	}

	CasePleineException(String message){
		super(message);
	}
	
	CasePleineException(Throwable cause) {
	    super(cause);
	}
	
	CasePleineException(Throwable cause,String message) {
	    super(message, cause);
	}

	
	CasePleineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	}
	
}