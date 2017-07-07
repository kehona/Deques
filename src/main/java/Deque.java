import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author konadip
 *
 * @param <Item> is a generic data type
 */
public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	
	private int N;

	private class Node {
		Item item;
		Node next;
		Node previous;
		
		private Node(Item item){
			this.item = item;
			this.next = null;
			this.previous = null;
		}
		@Override
		public String toString() {
			return "Node [item=" + item + ", next=" + next + "]";
		}
				
	}
	/**
	 * initializes the first and last item to be null
	 */
	public Deque() {
		first = null;
		last = null;
	}
	
	//is the deque empty?
	public boolean isEmpty() {
		return (first == null);
	}
	
	/**
	 * 
	 * @return the size of the deque
	 */
	public int size() {
		return N;
	}
	
	/**
	 * 
	 * @param item to be added to the beginning of the deque
	 */
	public void addFirst(Item item) {
		validateItem(item);
		Node newItemNode = new Node(item);
		
		if (first == null) {
			first = newItemNode;
			last = first;
		} else {
			Node oldfirst = first;
			first = newItemNode;
			first.next = oldfirst;
			oldfirst.previous = first;
		}
		N++; //increment N
	}
	
	/**
	 * 
	 * @param item to be added to the end of the deque
	 */
	public void addLast(Item item) {
		validateItem(item);
		Node newItemNode = new Node(item);
		
		if (last == null) {
			last = newItemNode;
			first = newItemNode;
		} else {
			Node oldlast = last;
			last = newItemNode;
			oldlast.next = last;
			last.previous = oldlast;
		}
		N++; //increment N
	}
	
	/**
	 * 
	 * @return the first item in the deque
	 */
	public Item removeFirst() {
		checkQueue();
		if (first.next == null && first == last ) {
			Item item = first.item;
			first = last = null;
			N--;
			return item;
		}
		Node oldfirst = first;
		first = first.next;
		first.previous = null;
	
		N--; //decrement N
		return oldfirst.item;
	}
	
	/**
	 * 
	 * @return the last item in the deque
	 */
	public Item removeLast() {
		checkQueue();
		if (last.previous == null && last == first) {
			Item item = last.item;
			first = last = null;
			N--;
			return item;
		}
		Node oldLast = last;
		last = last.previous;
		last.next = null;
		N--; //decrement N
		return oldLast.item;
	}
	
	/**
	 * check corner cases
	 */
	private void checkQueue() {
		if (first == null) {
			throw new NoSuchElementException("Queue is empty");
		}
	}
	
	/**
	 * 
	 * @param item to be validated
	 * throws exception if item is null
	 */
	private void validateItem(Item item) {
		if (item == null) {
			throw new NullPointerException("You can't enter a null item");		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		
		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				return null;
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
	@Override
	public String toString() {
		return "Deque [first=" + first + ", last=" + last + ", N=" + N + "]";
	}
	
	

}
