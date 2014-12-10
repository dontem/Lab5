package poker;

public class TrackHand {
	private int kicker;
	private int lowHand;
	private int highHand;
	private int handStrength;
	private Hand a;



	public TrackHand(Hand aHand,int handStrength, int highHand, int lowHand, int kicker) {
		this.a = aHand;
		this.kicker = kicker;
		this.lowHand = lowHand;
		this.handStrength = handStrength;
		this.highHand = highHand;
	}

}
