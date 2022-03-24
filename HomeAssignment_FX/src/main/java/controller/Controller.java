package controller;
import java.util.Collection;

public interface Controller<T, Tid>{
    void add(T elem);
    void delete(T elem);
    void deletebyID(Tid id);
    void update(T elem, Tid id);
    T search(Tid id);
    Iterable<T> ctrlFindAll();
    Collection<T> ctrlGetAll();
}
