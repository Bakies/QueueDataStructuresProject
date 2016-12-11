package es.baki.dsp3;

public class CircularObjectArrayQueue<T> {
	private Object[] circle;

	private int beginIndex, endIndex, numEntries;

	public CircularObjectArrayQueue(int maxSize) {
		circle = new Object[maxSize];
		numEntries = beginIndex = endIndex = 0;
	}

	public boolean enqueue(T o) {
		if (numEntries == circle.length)
			return false;
		circle[endIndex] = o;

		endIndex = (endIndex + 1) % circle.length;
		numEntries++;
		return true;
	}

	public T dequeue() {
		if (numEntries == 0)
			return null;

		@SuppressWarnings("unchecked")
		T toReturn = (T) circle[beginIndex];

		beginIndex = (beginIndex + 1) % circle.length;
		numEntries--;

		return toReturn;
	}

	@Override
	public String toString() {
		String s = "";
		if (beginIndex == endIndex)
			return s;

		for (int x = beginIndex; x != endIndex; x = (x + 1) % circle.length)
			s += circle[x] + ", ";

		return s.substring(0, s.length() - 2);
	}

	public static void main(String... strings) {
		CircularObjectArrayQueue<String> q = new CircularObjectArrayQueue<String>(3);
		System.out.println(q);
		System.out.println(q.enqueue("first"));
		System.out.println(q.enqueue("second"));
		System.out.println(q.enqueue("third"));
		System.out.println(q.enqueue("fourth"));

		System.out.println(q);

		System.out.println(q.dequeue());
		System.out.println(q.enqueue("fifth"));
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());

	}

	public boolean isFull() {
		return numEntries == circle.length;
	}
}