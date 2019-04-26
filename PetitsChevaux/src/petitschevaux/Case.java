package petitschevaux;

import java.util.ArrayList;

public abstract class Case {
	
	private ArrayList<Pion> chevaux = new ArrayList<Pion>();
	
	public Case() {
		
	}
	
	public ArrayList<Pion> getChevaux() {
		return chevaux;
	}
	
	public void ajouteCheval(Pion p) {
		chevaux.add(p);
	}
	
	public abstract boolean peutPasser(Pion p);
	
	public abstract boolean peutSArreter(Pion p);
	
	public void retirerCheval(Pion p) {
		chevaux.remove(p);
	}

}
