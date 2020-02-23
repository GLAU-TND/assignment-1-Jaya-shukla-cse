package adt;

public interface Contact<E>{
    void add(E item);
    E remove();
    E getData(int index);
}
