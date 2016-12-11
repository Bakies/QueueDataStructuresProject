package es.baki.dsp3;

public class Station {
	private static int nextID = 0;

	private String stationName;
	private int location, id;

	CircularObjectArrayQueue<Passenger> q = new CircularObjectArrayQueue<Passenger>(50);

	public Station(String stationName) {
		id = nextID;
		nextID++;
		this.stationName = stationName;
	}

	public int trainArrives(Train t) {
		Passenger p;
		int count = 0;
		while ((p = q.dequeue()) != null) {
			t.addPassenger(p);
			count++;
		}
		return count;
	}

	public int getLocation() {
		return this.location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String getName() {
		return this.stationName;
	}

	@Override
	public String toString() {
		return this.stationName + " @ " + this.location;
	}

	public void addPassenger(Passenger passenger) {
		if (q.enqueue(passenger))
			System.out.println("Passenger started waiting @ " + stationName);
		else
			System.out.println("Passenger queue is full @ " + stationName);
	}

	public int getId() {
		return id;
	}
}
