package definition;

import adt.Contact;
import org.w3c.dom.Node;


public class MyLinkedList<E> implements Contact<E> {
    private Node head=null;
    public int size=0;

    private void addFirst(E item){
        head = new Node<>(item,head);
        size++;
    }


    @Override
    public void add(E item) {

    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E getData(int index) {
        return null;
    }
}
