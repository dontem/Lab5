package poker;

import java.util.ArrayList;
import java.util.Collections;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class PlayHand {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	static Integer addHand(Hand ahand, int handStrength, int highHand,
			int lowHand, int kicker) {

		Object factory;
		Session session = factory.openSession();
		Transaction t = null;
				
		try {
			t = session.beginTransaction();
			TrackHand here = new TrackHand(ahand, handStrength,
					lowHand, highHand, kicker);
			} 
		catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}

	@SuppressWarnings("Deprecation")
	public static void main(String[] args, Object factory) {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();
			
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		for (int gCount = 0; gCount <= 2000000; gCount++) {
			ArrayList<Hand> Hands = new ArrayList<Hand>();
			Deck d = new Deck();

			for (int hCnt = 0; hCnt <= 2; hCnt++) {
				Hand h = new Hand(d);
				h.EvalHand();
				Hands.add(h);

			}
			final ArrayList<Hand> listHands = Hands;
			for (gCount = 0; gCount <= 200000; gCount++) {
				Hand hand = Hands.get(gCount);
				int handstrength = listHands.get(gCount).getHandStrength();
				int hihand = hand.getHighPairStrength();
				int lohand = hand.getLowPairStrength();
				int kicker = hand.getKicker();
				addHand(hand, handstrength, hihand, lohand, kicker);

			}

			Collections.sort(Hands, Hand.HandRank);

			System.out
					.print("Hand Strength: " + Hands.get(0).getHandStrength());
			System.out.print(" Hi Hand: " + Hands.get(0).getHighPairStrength());
			System.out.print(" Lo Hand: " + Hands.get(0).getLowPairStrength());
			System.out.print(" Kicker: " + Hands.get(0).getKicker());

			System.out.print(" beats ");

			System.out.print(" Hand Strength: "
					+ Hands.get(1).getHandStrength());
			System.out.print(" Hi Hand: " + Hands.get(1).getHighPairStrength());
			System.out.print(" Lo Hand: " + Hands.get(1).getLowPairStrength());
			System.out.print(" Kicker: " + Hands.get(1).getKicker());

			System.out.print("\n");

		}

	}

}
