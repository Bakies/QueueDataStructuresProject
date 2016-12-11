package es.baki.dsp3;

public class Passenger {
	private Station startStation, endStation;

	public Passenger(Station startStation, Station endStation) {
		this.startStation = startStation;
		this.endStation = endStation;
	}

	public Station getEndStation() {
		return endStation;
	}

	public Station getStartStation() {
		return startStation;
	}

}
