package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

public class SinglyLinkedList implements ILinkedList{



    Node head;
    Integer size;
    final Node nullNode = new Node(null,null);

    private class Node{
        Object value;
        Node next;

        Node(Object value, Node next){
            this.value = value;
            this.next = next;
        }
        Node(){ }
        Node(Object value){
            this.value = value;
            this.next = nullNode;
        }
    }

    SinglyLinkedList(){
        head = nullNode;
        size = 0;
    }

    @Override
    public void add(int index, Object element) {
        if(index > this.size || index < 0)
        { /** TODO **/ return; }

            Node elm = new Node(element, null);
        this.size++;

        if(index == 0){
            Node second = this.head;
            elm.next = second;
            this.head = elm;
            return;
        }
        else {
            Node current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            Node nextToCurrent = current.next;
            current.next = elm;
            elm.next = nextToCurrent;
        }
    }


    @Override
    public void add(Object element) {
        this.add(this.size,element);
    }

    @Override
    public Object get(int index) {
        if(index >= size || index < 0){
            /** TODO **/
            return nullNode;
        }

        Node node = this.head;
        for(int i=0; i<index; i++)
            node = node.next;

        return node.value;
    }

    @Override
    public void set(int index, Object element) {

        if(index >= size || index < 0){
            /** TODO **/
            return ;
        }

        this.remove(index);
        this.add(index,element);
    }

    @Override
    public void clear() {
        this.head = nullNode;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        if(this.size == 0)
            return true;

        else
            return false;
    }

    @Override
    public void remove(int index) {
        if(index >= size || index < 0){
            return;
        }
        this.size--;
        Node preNode = this.head;
        for(int i=0; i<index-1; i++)
            preNode = preNode.next;


        Node node = preNode.next;
        Node nextNode = node.next;

        if(index == 0)
            this.head = node;
        else {
            preNode.next = nextNode;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if(fromIndex >= 0 && fromIndex < this.size &&
            toIndex >= 0 && toIndex < this.size &&
            toIndex >= fromIndex){

            Node a= this.head;
            int i=0;
            SinglyLinkedList arr = new SinglyLinkedList();

            for(i=0; i<fromIndex; i++){
                a = a.next;
            }
            for(; i<=toIndex; i++){
                arr.add(a.value);
                a = a.next;
            }
            return arr;
        } else {
            /* TODO */
            return  null;
        }
    }

    @Override
    public boolean contains(Object o) {
        Node a= this.head;
        Node elm = new Node(o, nullNode);
        if(a == nullNode) return false;
        while(a.next != nullNode) {
            if(a.value == elm.value)
                return true;
            a = a.next;
        }
        if(a.value == elm.value)
            return true;

        return false;
    }

   /* public void printAll(){
        /// for primitive values only
        Node a= this.head;
        if(a == SinglyLinkedList.nullNode) return;
        while(a.next != SinglyLinkedList.nullNode) {
            System.out.print(a.value + " ");
            a = a.next;
        }
        System.out.println(a.value);
    }*/
}
