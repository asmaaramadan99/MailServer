package eg.edu.alexu.csd.datastructure;

import java.io.Serializable;

public class SinglyLinkedList implements Serializable{

	private static final long serialVersionUID = 1L;
	
    public Node head;
    Integer size;
    public final Node nullNode = new Node(null,null);

    public class Node implements Serializable{
    	private static final long serialVersionUID = 1L;
        public Object value;
        public Node next;

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

    public SinglyLinkedList(){
        head = nullNode;
        size = 0;
    }

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


    public void add(Object element) {
        this.add(this.size,element);
    }

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

    public void set(int index, Object element) {

        if(index >= size || index < 0){
            /** TODO **/
            return ;
        }

        this.remove(index);
        this.add(index,element);
    }

    public void clear() {
        this.head = nullNode;
        this.size = 0;
    }

    public boolean isEmpty() {
        if(this.size == 0)
            return true;

        else
            return false;
    }

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

    
    public int size() {
        return this.size;
    }

    public SinglyLinkedList sublist(int fromIndex, int toIndex) {
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
    
    public int getIndex(Object o) {
        Node a= this.head;
        int index = 0;
        Node elm = new Node(o, nullNode);
        if(a == nullNode) return -1;
        while(a.next != nullNode) {
            if(a.value == elm.value)
                return index;
            a = a.next;
            index++;
        }
        if(a.value == elm.value)
            return index;

        return -1;
    }

    public void printAll(){
        /// for primitive values only
        Node a= this.head;
        if(a == nullNode) return;
        while(a.next != nullNode) {
            System.out.println(a.value + " ");
            a = a.next;
        }
        System.out.println(a.value);
    }
}
