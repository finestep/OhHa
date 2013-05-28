package base.managers;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import base.ents.Ent;

/**
 * Gamestate manager; holds entity positions
 */
public class StateMan {

	private List<Ent> ents = new LinkedList<Ent>();

	public void add_ent(Ent e) {
		ents.add(e);
		System.out.println("New entity "+e);
	}

	public Iterator<Ent> getEntIter() {
		return ents.iterator();
	}

	/**
	 * Removes an ent with a particular id
	 * @param delid  ent to remove
	 * @return boolean true if such ent was found
	 */
	public boolean del_ent(int delid) {
		Iterator<Ent> i = ents.iterator();
		while(i.hasNext()) {
			Ent e = i.next();
			if(e.id==delid) {
				ents.remove(e);
				System.out.println("Removed "+e);
				return true;
			}
		}
		return false;
	}

	public void delAll() {
		ents.clear();
	}
}
