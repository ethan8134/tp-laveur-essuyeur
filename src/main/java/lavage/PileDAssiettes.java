package lavage;

import java.util.LinkedList;
import java.util.List;

class PileDAssiettes {

	private final static int MAX = 10;
	private final List<Assiette> myList = new LinkedList<>();

	private boolean isEmpty() {
		return myList.isEmpty();
	}

	private boolean isFull() {
		return (myList.size() >= MAX);
	}

	synchronized public void push(Assiette assiette) throws InterruptedException {
		while (isFull()) {
			wait();
		}
		myList.add(assiette);
		System.out.printf("la pile contient %d assiettes%n", myList.size());
		notifyAll(); // réveille les essuyeurs bloqués
	}

	synchronized public Assiette pop() throws InterruptedException {
		while (isEmpty()) {
			wait();
		}
		Assiette result = myList.remove(myList.size() - 1);
		System.out.printf("la pile contient %d assiettes%n", myList.size());
		notifyAll(); // réveille les laveurs bloqués
		return result;
	}

}
