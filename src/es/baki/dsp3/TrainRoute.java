package es.baki.dsp3;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class to describe a circular train route
 *
 * @author jon
 *
 */
public class TrainRoute {
	private static TrainRoute tr = null;

	private ArrayList<Station> stations = new ArrayList<Station>();
	private ArrayList<Integer> times = new ArrayList<Integer>();
	private boolean complete = false;

	private TrainRoute(Station s) {
		stations.add(s);
		s.setLocation(0);
	}

	public boolean addStation(Station s, int distance) {
		if (complete)
			return false;
		stations.add(s);
		s.setLocation(stations.get(stations.size() - 1).getLocation() + distance);
		times.add(new Integer(distance));
		return true;
	}

	public boolean endRoute(int i) {
		if (stations.size() == 1)
			return false;
		times.add(new Integer(i));
		return true;
	}

	public static TrainRoute getTrainRoute() {
		return tr;
	}

	public static TrainRoute makeNewRoute(Station station) {
		if (tr == null) {
			tr = new TrainRoute(station);
			return tr;
		} else
			System.out.println("Warning: train route already exists");
		return null;
	}

	public Station getStationAtLoc(int location) {
		for (Station s : this.stations)
			if (s.getLocation() == location)
				return s;
		return null;
	}

	public ArrayList<Station> getStations() {
		return this.stations;
	}

	public Station getRandomStation(Station s) {
		// Worst way ever to get a random station
		// Could technically run until stack overflow
		// But what are the chances of that?
		Random rand = new Random();
		Station toReturn = stations.get(rand.nextInt(stations.size()));
		if (s == toReturn)
			return getRandomStation(s);
		else
			return toReturn;
	}

	public int length() {
		int length = 0;
		for (Integer x : times)
			length += x.intValue();

		return length;
	}

	@Override
	public String toString() {
		String toReturn = "";
		for (Station s : stations)
			toReturn += String.format("Station %d: %s is located @ %d%n", s.getId(), s.getName(), s.getLocation());

		return toReturn;
	}

}
