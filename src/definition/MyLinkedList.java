package definition;

import adt.Contact;

public class MyLinkedList<E> implements Contact<E> {
    private Node<E> head=null;
    public int size=0;

    private void addFirst(E item){
        head = new Node<>(item,head);
        size++;
    }

    private void addAfter(E item, Node<E> afterNode){
        afterNode.next = new Node<>(item, afterNode.next);
        size++;
    }

    public void add(E item, int index){
        if(index > size || index < 0)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        else if(index == 0)
            addFirst(item);
        else
            addAfter(item, getNode(index-1));
    }

    private Node<E> getNode(int index) {
        Node<E> response = head;
        for(int i=0;i<index;i++) {
            response = response.getNext();
        }
        return response;
    }

    private E removeFirst(){
        Node<E> temp=head;
        E response=null;
        if(head==null){
            head=head.getNext();
        }
        if(temp!=null){
            size--;
            response=temp.getData();
        }
        return response;
    }

    private E removeAfter(Node<E> afterNode){
        Node<E> temp=afterNode.getNext();
        if(temp!=null){
            afterNode.next=temp.getNext();
            size--;
        }
        E response=temp.getData();
        return response;
    }

    public E remove(int index){
        E response=null;
        if(index>0 || index<size){
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        else if(index==0){
            response=removeFirst();
        }
        else{
            Node<E> previousNode=getNode(index-1);
            response=removeAfter(previousNode);
        }
        return response;

    }
    @Override
    public void add(E item) {
        add(item,size);
    }

    @Override
    public E remove() {
        return remove(size-1);
    }

    @Override
    public E getData(int index) {
        return getNode(index).getData();
    }

    private static class Node<E>{
        private E data;
        private Node<E> next;
        public E getData() {
            return data;
        }
        public Node<E> getNext() {
            return next;
        }
        public Node(E data, Node<E> next){
            this.data=data;
            this.next=next;
        }
    }
}
