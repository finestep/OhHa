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
	private List<Ent> toAdd = new LinkedList<Ent>();

    /**
     * Schedules an entity to be added to gamestate
     * @param e The entity
     */
	public void add_ent(Ent e) {
		toAdd.add(e);
		System.out.println("New entity scheduled "+e);
	}

    /**
     * Adds the scheduled entities
     */
	public void updateList() {
		ents.addAll(toAdd);
		toAdd.clear();
	}

    /**
     * Gets an iterator of the entity list
     * @return  the iterator
     */
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

    /**
     * Clears the entity list
     */
	public void delAll() {
		ents.clear();
		System.out.println("Cleared entities");
	}
}
