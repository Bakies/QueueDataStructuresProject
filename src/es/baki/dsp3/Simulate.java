package es.baki.dsp3;

import java.util.Random;

public class Simulate {
	public static void main(String... strings) {
		Random rand = new Random();
		int maxTime = 100;

		// Train route
		/*
		 * Back Bay -----> Ruggles -------> North Station ----------> South
		 * Station ---> Back Bay
		 *
		 */
		TrainRoute route = TrainRoute.makeNewRoute(new Station("Back Bay (1)"));
		route.addStation(new Station("Ruggles (2)"), 5);
		route.addStation(new Station("North Station (3)"), 7);
		route.addStation(new Station("South Station (4)"), 10);
		route.endRoute(3);

		System.out.println(route);

		for (int x = 0; x < maxTime; x++) {
			System.out.println("tick" + x);
			// Spawn new Passengers
			for (Station s : route.getStations())
				if (rand.nextInt() % 10 <= 6)
					s.addPassenger(new Passenger(s, route.getRandomStation(s)));

			// Move all Trains
			for (Train t : Train.getTrains())
				t.move();

			// Spawn a new Train every 5 time units, until there are trains
			// every 5 units
			if (x % 5 == 0 && Train.getTrains().size() < 5)
				new Train();

		}
	}
}
