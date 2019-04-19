package petitschevaux;

import java.util.ArrayList;

public class Case {
	
	private ArrayList<Pion> chevaux = new ArrayList<Pion>();
	
	public Case() {
		
	}
	
	public ArrayList<Pion> getChevaux() {
		return chevaux;
	}
	
	public void ajouteCheval(Pion p) {
		chevaux.add(p);
	}
	
	public boolean peutPasser(Pion p) {
		
		return false; ////////////////////////////////////////////
	}
	
	public boolean peutSArreter(Pion p) {
		
		return false; //////////////////////////////////////////////
	}

}
