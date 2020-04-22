package eg.edu.alexu.csd.datastructure;

import java.io.Serializable;

public class Queue implements Serializable {
    private static final long serialVersionUID = 1L;

	private class Node implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private Object data;
        private Node next;
        Node(Object data) {
            this.setData(data);
        }
        public Object getData() {
            return data;
        }
        public void setData(Object data) {
            this.data = data;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void enqueue(Object item) {
        // TODO Auto-generated method stub
        if(size==0) {
            head=new Node(item);
            tail=head;
        }
        else
        {
            Node node=new Node(item);
            tail.setNext(node);
            tail=node;
        }
        size++;
    }


  public Object dequeue() {
        // TODO Auto-generated method stub
        if(head==null) throw new RuntimeException("Queue is empty");
        Object temp=head.getData();
        head=head.getNext();
        size--;
        return temp;
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size==0?true:false;
    }

    public int size() {
        // TODO Auto-generated method stub
        return size;
    }


}
