package fr.exceptionpetitschevaux;

public class PasDeJoueursException extends Exception{
	
	public PasDeJoueursException(){
		System.out.println("Il n'y a pas de joueurs !");
	}
	
	public PasDeJoueursException(String message){
		super(message);
	}
	
	public PasDeJoueursException(Throwable cause) {
	    super(cause);
	}
	
	public PasDeJoueursException(Throwable cause,String message) {
	    super(message, cause);
	}

	
	public PasDeJoueursException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	}
}






