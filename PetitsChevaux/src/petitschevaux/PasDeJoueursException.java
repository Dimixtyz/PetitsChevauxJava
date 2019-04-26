package petitschevaux;

public class PasDeJoueursException extends Exception{
	
	PasDeJoueursException(){
		System.out.println("Il n'y a pas de joueurs !");
	}
	
	PasDeJoueursException(String message){
		super(message);
	}
	
	PasDeJoueursException(Throwable cause) {
	    super(cause);
	}
	
	PasDeJoueursException(Throwable cause,String message) {
	    super(message, cause);
	}

	
	PasDeJoueursException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	}
}






