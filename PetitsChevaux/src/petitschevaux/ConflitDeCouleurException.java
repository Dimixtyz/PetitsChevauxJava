package petitschevaux;

public class ConflitDeCouleurException extends Exception{
	
	ConflitDeCouleurException(){
		System.out.println("Conflit de couleur !");
	}
	
	ConflitDeCouleurException(String s){
		super(s);
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
