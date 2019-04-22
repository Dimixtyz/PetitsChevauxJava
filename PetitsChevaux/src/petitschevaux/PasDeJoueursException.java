package petitschevaux;

public class PasDeJoueursException extends Exception{
	
	PasDeJoueursException(){
		System.out.println("Il n'y a pas de joueurs !");
	}
	
	PasDeJoueursException(String message){
		super(message);
	}
	
	PasDeJoueursException(Throwable t){
		
	}
	
	PasDeJoueursException(Throwable t, String s){
		
	}
	
	PasDeJoueursException(Throwable t, String s, boolean b1, boolean b2){
		
	}
	
}