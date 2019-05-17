package fr.exceptionpetitschevaux;

public class ConflitDeCouleurException extends Exception{
	
	public ConflitDeCouleurException(){
		System.out.println("Conflit de couleur !");
	}
	
	public ConflitDeCouleurException(String s){
		super(s);
	}
	
	public ConflitDeCouleurException(Throwable cause) {
	    super(cause);
	}
	
	public ConflitDeCouleurException(Throwable cause,String message) {
	    super(message, cause);
	}

	
	public ConflitDeCouleurException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
