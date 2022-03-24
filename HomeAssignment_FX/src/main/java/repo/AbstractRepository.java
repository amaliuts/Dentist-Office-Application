package repo;

import domain.*;
import java.util.*;

public abstract class AbstractRepository <T extends Identifiable<ID>, ID> implements Repository<T, ID>{
    protected Map<ID, T> repo;

    public AbstractRepository() {
        repo = new HashMap<>();
    }

    public void add(T elem) {
        if (repo.containsKey(elem.getID()))
            throw new RuntimeException("Element already exists.");
        else
            repo.put(elem.getID(), elem);
    }

    public void delete(T elem) {
        if (repo.containsKey(elem.getID()))
            repo.remove(elem.getID());
    }

    public T findById(ID id) {
        if (repo.containsKey(id))
            return repo.get(id);
        else
            throw new RuntimeException("Element doesn't exist.");
    }

    public void update(T el, ID id){
        if (repo.containsKey(id))
            repo.put(el.getID(), el);
        else
            throw new RuntimeException("Element doesn't exist.");
    }

    public Vector<Appointment> cheaperThan(int bd) {
        Vector<Appointment> app = new Vector<Appointment>();
        for (int i = 0; i < repo.size(); i++) {
            app.add((Appointment) repo.get(i));
        }
        return app;
    }

    public String printList() {
        String out = "";
        for (int i = 0; i < repo.size(); i++)
            out = out + repo.get(i) + "\n";
        return (out);
    }

    public Iterable<T> findAll() {
        return repo.values();
    }

    public Collection<T> getAll(){
        return repo.values();
    }

}
