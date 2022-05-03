package be.iesca.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import be.iesca.dao.BiereDao;
import be.iesca.domaine.Biere;

public class BiereDaoMockImpl implements BiereDao {
	private Map<String, Biere> mapBieres;

	public BiereDaoMockImpl() {
		Comparator<String> comp = new ComparateurBieres();
		this.mapBieres = new TreeMap<String, Biere>(comp);
	}

	@Override
	public boolean ajouterBiere(Biere biere) {
		try {
			if (this.mapBieres.containsKey(biere.getNom()))
				return false;
			this.mapBieres.put(biere.getNom(), biere);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public Biere getBiere(String nom) {
		try {
			Biere biere = this.mapBieres.get(nom);
			if ( biere==null) return null;
			return new Biere(biere);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public List<Biere> listerBieres() {
		Collection<Biere> col = Collections.unmodifiableCollection(this.mapBieres.values());
		return new ArrayList<Biere>(col);
	}

	@Override
	public boolean supprimerBiere(String nom) {
		try {
			if (this.mapBieres.remove(nom) == null)
				return false;
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean modifierBiere(Biere biere) {
		try {
			if (this.mapBieres.remove(biere.getNom()) == null)
				return false;
			this.mapBieres.put(biere.getNom(), biere);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	private class ComparateurBieres implements Comparator<String> {
		@Override
		public int compare(String nom1, String nom2) {
			return nom1.compareTo(nom2);
		}
	}
}
