package petitschevaux;

public class ConflitDeCouleurException extends Exception{
	
	ConflitDeCouleurException(){
		
	}
	
	ConflitDeCouleurException(String s){
		
	}
	
	ConflitDeCouleurException(Throwable cause) {
	    super(cause);
	}
	
	ConflitDeCouleurException(Throwable cause,String message) {
	    super(message, cause);
	}

	
	ConflitDeCouleurException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
