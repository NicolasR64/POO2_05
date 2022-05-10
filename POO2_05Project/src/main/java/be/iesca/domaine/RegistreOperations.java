package be.iesca.domaine;

import java.util.Collection;
import java.util.Iterator;


//POURRA ETRE SUPPRIMER MAIS METTRE DANS TEST DABORD
public class RegistreOperations implements Collection<Operation> {

	private static final int TAILLE_INI_REGISTRE = 100;
	private static final int AUGMENTATION_REGISTRE = 50;

	private Operation[] tableOperations; // registre des opérations
	private int numOperation; // numéro de la dernière opération

	public RegistreOperations() {
		super();
		this.tableOperations = new Operation [ TAILLE_INI_REGISTRE ];
		this.numOperation = 0;
		this.add(new Operation(null, 0, Operation.CREATION, 0));
	}

	@Override
	public boolean add(Operation opération) {
		if ( this.contains(opération) ) {
			return false; 
		}
		if ( this.numOperation == this.tableOperations.length ) {
			agrandirTableRegistre();
		}
		this.numOperation++;
		opération.setNuméro(this.numOperation);
		this.tableOperations[ this.numOperation-1 ] = opération;
		return true;
	}

	// agrandissement de la table des opérations
	private void agrandirTableRegistre() {
		int taille = this.tableOperations.length + AUGMENTATION_REGISTRE;
		Operation[] histTemp = new Operation[ taille ];
		for (int i = 0; i < this.numOperation ; i++ ) {
			histTemp[i] = this.tableOperations[i];
		}
		this.tableOperations = histTemp;
	}

	// renvoie le registre des opérations sous forme de chaîne
	// de caractères
	public String toString() {
		if ( this.numOperation == 0 ) return "Historique vide";
		String resultat = "";
		for ( int i=0 ; i<this.numOperation; i++ ) {
			resultat += this.tableOperations[i] + "\n";
		}
		return resultat;
	}

	@Override
	public Iterator<Operation> iterator() {
		throw new UnsupportedOperationException("Pas implémentée");		
	}

	@Override
	public boolean contains(Object o) {
		if ( o == null) {
			throw new NullPointerException("référence==null ");
		}
		if ( !(o instanceof Operation)) {
			throw new ClassCastException("L'objet doit être de type"
					+ "opération");
		}
		for ( int i=0 ; i<this.numOperation; i++ ) {
			if ( this.tableOperations[i].equals(o)) return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return numOperation == 0;
	}

	@Override
	public int size() {
		return numOperation;
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException(
				"Les retraits ne sont pas autorisés dans l'historique" +
				" des opérations");		
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException(
				"Les retraits ne sont pas autorisés dans l'historique" +
				" des opérations");
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException(
				"Les retraits ne sont pas autorisés dans l'historique" +
				" des opérations");
	}

	@Override
	public boolean addAll(Collection<? extends Operation> arg0) {
		throw new UnsupportedOperationException("opération refusée");
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("opération refusée");
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("opération refusée");
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("opération refusée");
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException("opération refusée");
	}
}
