package eg.edu.alexu.cs.datastructures.classes;

import MyDataStructures.*;
import eg.edu.alexu.csd.datastructure.SinglyLinkedList;
import eg.edu.alexu.cs.datastructures.Interfaces.*;
import java.sql.Date;

class Sort implements ISort {

	/**
	 * sorting according to priority
	 * 
	 * @param mails
	 */
	public static void priority(SinglyLinkedList mails) {
		if (mails.head == null)
			throw new RuntimeException();
		PriorityQueue q = new PriorityQueue();
		for (int i = 0; i < mails.size(); i++) {
			Mail mail = (Mail) mails.get(i);
			q.insert(mail, mail.priority);
		}
		mails.clear();
		int queueSize = q.size();
		for (int i = 0; i < queueSize; i++)
			mails.add(q.removeMin());

	}

	public static void iterativeQuickSort(SinglyLinkedList mails, String sortType) {
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
			if (sortType.equals("Default"))
				p = partitionDate(mails, p, start, end);
			else if (sortType.equals("Subject"))
				p = partitionSubject(mails, p, start, end);
			else if (sortType.equals("Sender"))
				p = partitionSender(mails, p, start, end);
			else if (sortType.equals("Body"))
				p = partitionBody(mails, p, start, end);

			stack.push(p + 1);
			stack.push(end);
			stack.push(start);
			stack.push(p);
		}

	}

	private static int partitionBody(SinglyLinkedList mails, int position, int start, int end) {
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

	private static int partitionSender(SinglyLinkedList mails, int position, int start, int end) {
		int low = start;
		int high = end - 2;
		Mail piv = (Mail) mails.get(position);
		String pivot = piv.senderEmail;
		swap(mails, position, end - 1);

		while (low < high) {

			Mail l = (Mail) mails.get(low);
			Mail h = (Mail) mails.get(high);

			if ((l.senderEmail).compareTo(pivot) < 0) {
				low++;
			} 
			else if (!((h.senderEmail).compareTo(pivot) < 0)) {
				high--;
			} 
			else {
				swap(mails, low, high);
			}
		}

		int index = high;
		Mail h = (Mail) mails.get(high);
		if ((h.senderEmail).compareTo(pivot) < 0) {
			index++;
		}
		swap(mails, end - 1, index);

		return index;
	}

	private static int partitionSubject(SinglyLinkedList mails, int position, int start, int end) {
		int low = start;
		int high = end - 2;
		Mail piv = (Mail) mails.get(position);
		String pivot = piv.subject;
		swap(mails, position, end - 1);

		while (low < high) {

			Mail l = (Mail) mails.get(low);
			Mail h = (Mail) mails.get(high);

			if ((l.subject).compareTo(pivot) < 0) {
				low++;
			} 
			else if (!((h.subject).compareTo(pivot) < 0)) {
				high--;
			} 
			else {
				swap(mails, low, high);
			}
		}

		int index = high;
		Mail h = (Mail) mails.get(high);
		if ((h.subject).compareTo(pivot) < 0) {
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

	private static int partitionDate(SinglyLinkedList mails, int position, int start, int end) {
		int low = start;
		int high = end - 2;
		Mail piv = (Mail) mails.get(position);
		Date pivot = Date.valueOf(piv.date);
		swap(mails, position, end - 1);
		while (low < high) {
			Mail l = (Mail) mails.get(low);
			Mail h = (Mail) mails.get(high);
			if (Date.valueOf(l.date).compareTo(pivot) >= 0) {
				low++;
			} 
			else if (!((Date.valueOf(h.date).compareTo(pivot) >= 0))) {
				high--;
			} 
			else {
				swap(mails, low, high);
			}
		}
		int index = high;
		Mail h = (Mail) mails.get(high);

		if (Date.valueOf(h.date).compareTo(pivot) >= 0) {
			index++;
		}
		swap(mails, end - 1, index);
		return index;
	}

	public static void swap(SinglyLinkedList mails, int i, int j) {
		Mail temp = (Mail) mails.get(i);
		mails.set(i, mails.get(j));
		mails.set(j, temp);
	}

}
