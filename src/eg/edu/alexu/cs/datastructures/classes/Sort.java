package eg.edu.alexu.cs.datastructures.classes;

import MyDataStructures.*;
import eg.edu.alexu.csd.datastructure.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.SinglyLinkedList;
import eg.edu.alexu.cs.datastructures.Interfaces.*;
import java.sql.Date;

public class Sort implements ISort {

	/**
	 * sorting according to priority
	 * 
	 * @param mails
	 */
	public static void priority(DoubleLinkedList mails) {
		System.out.println(mails.size());
		if (mails.head == null)
			throw new RuntimeException();
		PriorityQueue q = new PriorityQueue();
		for (int i = 0; i < mails.size(); i++) {
			
			Mail mail = (Mail) mails.get(i);
		//	System.out.println(mail.getPriority());
	//		System.out.println(mail.getSubject());
			
			q.insert(mail, mail.getPriority());
		}
		mails.clear();
		int queueSize = q.size();
		//System.out.println(q.size());
		
		for (int i = 0; i < queueSize; i++) {
		//	System.out.println(i);
			Object k = q.removeMin();
			mails.add(k);
		}
	}

	public static void iterativeQuickSort(DoubleLinkedList mails, String sortType) {
		Stack stack = new Stack();
		stack.push(0);
		stack.push(mails.size());
		while (stack != null && !stack.isEmpty()) {
			int end = (int) stack.pop();
			int start = (int) stack.pop();
			if (end - start < 2) {
				continue;
			}
			int p = start + ((end - start) / 2);
			if (sortType.equals("default"))
				p = partitionDate(mails, p, start, end);
			else if (sortType.equals("subject"))
				p = partitionSubject(mails, p, start, end);
			else if (sortType.equals("sender"))
				p = partitionSender(mails, p, start, end);
			else if (sortType.equals("body"))
				p = partitionBody(mails, p, start, end);

			stack.push(p + 1);
			stack.push(end);
			stack.push(start);
			stack.push(p);
		}

	}

	private static int partitionBody(DoubleLinkedList mails, int position, int start, int end) {
		int low = start;
		int high = end - 2;
		Mail piv = (Mail) mails.get(position);
		String pivot = piv.bodyText;
		swap(mails, position, end - 1);

		while (low < high) {

			Mail l = (Mail) mails.get(low);
			Mail h = (Mail) mails.get(high);

			if ((l.bodyText).compareTo(pivot) < 0) {
				low++;
			} 
			else if (!((h.bodyText).compareTo(pivot) < 0)) {
				high--;
			} 
			else {
				swap(mails, low, high);
			}
		}

		int index = high;
		Mail h = (Mail) mails.get(high);
		if ((h.bodyText).compareTo(pivot) < 0) {
			index++;
		}
		swap(mails, end - 1, index);

		return index;
	}

	private static int partitionSender(DoubleLinkedList mails, int position, int start, int end) {
		int low = start;
		int high = end - 2;
		Mail piv = (Mail) mails.get(position);
		String pivot = piv.getSender();
		swap(mails, position, end - 1);

		while (low < high) {

			Mail l = (Mail) mails.get(low);
			Mail h = (Mail) mails.get(high);

			if ((l.getSender()).compareTo(pivot) < 0) {
				low++;
			} 
			else if (!((h.getSender()).compareTo(pivot) < 0)) {
				high--;
			} 
			else {
				swap(mails, low, high);
			}
		}

		int index = high;
		Mail h = (Mail) mails.get(high);
		if ((h.getSender()).compareTo(pivot) < 0) {
			index++;
		}
		swap(mails, end - 1, index);

		return index;
	}

	private static int partitionSubject(DoubleLinkedList mails, int position, int start, int end) {
		int low = start;
		int high = end - 2;
		Mail piv = (Mail) mails.get(position);
		String pivot = piv.getSubject();
		swap(mails, position, end - 1);

		while (low < high) {

			Mail l = (Mail) mails.get(low);
			Mail h = (Mail) mails.get(high);

			if ((l.getSubject()).compareTo(pivot) < 0) {
				low++;
			} 
			else if (!((h.getSubject()).compareTo(pivot) < 0)) {
				high--;
			} 
			else {
				swap(mails, low, high);
			}
		}

		int index = high;
		Mail h = (Mail) mails.get(high);
		if ((h.getSubject()).compareTo(pivot) < 0) {
			index++;
		}
		swap(mails, end - 1, index);

		return index;
	}

	/**
	 * for date sorting from newest to oldest
	 * 
	 * @param mails
	 * @param position
	 * @param start
	 * @param end
	 * @return
	 */

	private static int partitionDate(DoubleLinkedList mails, int position, int start, int end) {
		int low = start;
		int high = end - 2;
		Mail piv = (Mail) mails.get(position);
		Date pivot = Date.valueOf(piv.getDateString());
		swap(mails, position, end - 1);
		while (low < high) {
			Mail l = (Mail) mails.get(low);
			Mail h = (Mail) mails.get(high);
			if (Date.valueOf(l.getDateString()).compareTo(pivot) >= 0) {
				low++;
			} 
			else if (!((Date.valueOf(h.getDateString()).compareTo(pivot) >= 0))) {
				high--;
			} 
			else {
				swap(mails, low, high);
			}
		}
		int index = high;
		Mail h = (Mail) mails.get(high);

		if (Date.valueOf(h.getDateString()).compareTo(pivot) >= 0) {
			index++;
		}
		swap(mails, end - 1, index);
		return index;
	}

	public static void swap(DoubleLinkedList mails, int i, int j) {
		Mail temp = (Mail) mails.get(i);
		mails.set(i, mails.get(j));
		mails.set(j, temp);
	}

}