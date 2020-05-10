package eg.edu.alexu.cs.datastructures.classes;

import java.io.Serializable;

import eg.edu.alexu.cs.datastructures.Interfaces.*;

class PriorityQueue implements IPriorityQueue,Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = -5562556808576696772L;
	int size;
	 
	public class Node {
		Object element;
		Node next;
		int key;
	}
 
	Node head;
	Node tail;
 
	@Override
	public void insert(Object item, int key) {
 
		if (key <= 0) {
			throw new RuntimeException("Invalid key");
		}
		Node x = new Node();
		x.key = key;
		x.element=item;
		if(size()==0) {
			head=tail=x;
			x.next=null;
		}
		else if(size()==1) {
			if(head.key<x.key) {
				tail=x;
				head.next=x;
				x.next=null;
			}
			else {
				tail=head;
				x.next=head;
				head=x;
			}
		}
		else {
			if (x.key < head.key) {
				x.next=head;
				head=x;
			}
			else if(x.key>tail.key) {
				tail.next=x;
				tail=x;
				tail.next=null;
			}
			else {
				while (head.key <= x.key && head.next != null) {
					if (head.next.key > x.key) {
						break;
					}
					head = head.next;
				}
				x.next = head.next;
				head.next = x;
			}
		}
		size++;
	}
 
	@Override
	public Object removeMin() {
 
		if(isEmpty())
			throw new RuntimeException("Empty queue");
		Object min=head.element;
		head=head.next;
		size--;
		return min;
	}
 
	@Override
	public Object min() {
 
		if(isEmpty())
			throw new RuntimeException("Empty queue");
		return head.element;
	}
 
	@Override
	public boolean isEmpty() {
 
		return size()==0;
	}
 
	@Override
	public int size() {
 
		return size;
	}
 
}
