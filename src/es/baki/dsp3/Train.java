package es.baki.dsp3;

import java.util.ArrayList;

public class Train {
	private static int nextID = 0;

	private static ArrayList<Train> trains = new ArrayList<>();
	private ArrayList<Passenger> passengers = new ArrayList<>();

	private int location, id;

	public Train() {
		id = nextID;
		nextID++;
		location = 0;
		trains.add(this);
		checkLoc();
	}

	public void addPassenger(Passenger p) {
		passengers.add(p);
	}

	public static ArrayList<Train> getTrains() {
		return trains;
	}

	public void move() {
		location++;
		if (location > TrainRoute.getTrainRoute().length())
			location = 0;
		checkLoc();
	}

	private void checkLoc() {
		Station s = TrainRoute.getTrainRoute().getStationAtLoc(location);
		if (s != null) {
			int dropCount = 0;
			for (int x = 0; x < passengers.size(); x++)
				if (passengers.get(x).getEndStation() == s) {
					passengers.remove(x);
					dropCount++;
				}

			System.out.printf("Train %d arrives at %s, dropped off %d passengers then picked up %d passengers%n", id,
					s.getName(), dropCount, s.trainArrives(this));
		}
	}
}