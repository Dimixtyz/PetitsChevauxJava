package petitschevaux;

public class CasePleineException extends Exception{
	
	CasePleineException(){
		System.out.println("Il y a deja un pion sur cette case");
	}

	CasePleineException(String s){
		super(s);
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