package eg.edu.alexu.cs.datastructures.classes;
import java.sql.Date;
import eg.edu.alexu.cs.datastructures.Interfaces.IFilter;
import eg.edu.alexu.csd.datastructure.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.SinglyLinkedList;
import eg.edu.alexu.csd.datastructure.Stack;

public class Filter implements IFilter {

	public String filter; // subject priority sender
	public Object target; // value
	public DoubleLinkedList emails;
	private DoubleLinkedList filtered;
 
	Filter(String filter, Object target, DoubleLinkedList emails) {
		this.filter = filter;
		this.target = target; 
		this.emails = emails;
		filtered = new DoubleLinkedList();
	}
 
	public DoubleLinkedList getFiltered() {

		return binarySearch();
	}

	public DoubleLinkedList binarySearch() {

		Stack upper = new Stack();
		Stack lower = new Stack();
		int position = 0;
		int low = 0;
		int high = emails.size() - 1;
		int mid = low + (high - low) / 2;
                boolean found = false;
		lower.push(low);
		lower.push(mid);
		upper.push(high);
		upper.push(mid + 1);
	
		/*while (!found) {
			Mail current;
			if(!lower.isEmpty())
				 current = (Mail) emails.get((int) lower.peek());
			
			else
				break;
			
			if (compare(current) > 0) {
				upper.clear();
				high = (int) lower.pop();
				upper.push(high);
				mid = low + (high - low) / 2;
				if (!lower.isEmpty() && (int) lower.peek() != mid) {
					lower.push(mid);
				}
				if ((int) upper.peek() != mid + 1) {
					upper.push(mid + 1);
				}

			} else if (compare(current) < 0) {
				lower.clear();
				low = (int) upper.pop();
				lower.push(low);
				mid = low + (high - low) / 2;
				if ((int) lower.peek() != mid) {
					lower.push(mid);
				}
				if ((int) upper.peek() != mid + 1) {
					upper.push(mid + 1);
				}

			} else {
				found = true;
				position = mid;
				filtered.add((Mail) emails.get(mid));
			}

		}
		if (found) {
			addDuplicates(position, target);
		}*/
		for(int i=0; i<emails.size(); i++) {
			Mail current = (Mail) emails.get(i);
			if(compare(current) == 0) {	
				filtered.add(current);
			}
		}

		return filtered;
	}

	void addDuplicates(int position, Object target) {
		int i = position - 1;
		int j = position + 1;

		while (i >= 0) {
			if (compare((Mail) emails.get(i)) != 0)
				break;
			filtered.add((Mail) emails.get(i));

			i--;
		}
		while (j < emails.size()) {
			if (compare((Mail) emails.get(j)) != 0)
				break;

			filtered.add((Mail) emails.get(j));
			j++;
		}

	}

	int getFilterType(String type) {
		if (type.equals("subject"))
			return 1;
		if (type.equals("priority"))
			return 2;
		if (type.equals("sender"))
			return 3;
		if (type.equals("date"))
			return 4;

		return 0;
	}

	@SuppressWarnings("null")
	public int compare(Mail o1) {
		// TODO Auto-generated method stub
		int filterType = getFilterType(this.filter);
		switch (filterType) {
		case 1:
			return o1.getSubject().compareTo((String) target);
		case 2:
			return Integer.compare(o1.getPriority(), (Integer) target);
		case 3:
			return o1.getSender().compareTo((String) target);
		case 4: //TODO date should be string as a target parameter
			return o1.getDate().compareTo((Date) target);
		}
		return (Integer) null;
	}

}
