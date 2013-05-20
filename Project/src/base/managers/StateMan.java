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
	}

	public Iterator<Ent> getEntIter() {
		return ents.iterator();
	}

	public boolean del_ent(int delid) {
		Iterator<Ent> i = ents.iterator();
		while(i.hasNext()) {
			Ent e = i.next();
			if(e.id==delid) {
				ents.remove(e);
				return true;
			}
		}
		return false;
	}
}
