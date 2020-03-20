package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionWithDB {
	// private static List<String> trollInfo;
	// private static List<String> personInfo;

	public static int getTrollFinalScore(int allRounds) throws SQLException {
		int trollFinalScore = 0;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root", "123456789");
				PreparedStatement ps = con.prepareStatement("select * from battle.batlle_information where id = ?;")) {
			for (int i = 1; i <= allRounds; i++) {
				ps.setInt(1, i);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					trollFinalScore += rs.getInt(5);
				}
			}
		}
		return trollFinalScore;
	}

	public static int getPersonFinalScore(int allRounds) throws SQLException {
		int personFinalScore = 0;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root", "123456789");
				PreparedStatement ps = con.prepareStatement("select * from battle.batlle_information where id = ?;")) {
			for (int i = 1; i <= allRounds; i++) {
				ps.setInt(1, i);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					personFinalScore += rs.getInt(4);
				}
			}
		}
		return personFinalScore;
	}

	public static void printFinalResult(int allRounds) {
		try {
			System.out.println("____________________________");
			System.out.println("Final result: ");
			if (getPersonFinalScore(allRounds) < getTrollFinalScore(allRounds)) {
				System.out
						.println(getTrollFinalScore(allRounds) + ":" + getPersonFinalScore(allRounds) + " for trolls");
			} else if (getPersonFinalScore(allRounds) > getTrollFinalScore(allRounds)) {
				System.out
						.println(getPersonFinalScore(allRounds) + ":" + getTrollFinalScore(allRounds) + " for people");
			} else {
				System.out.println(getPersonFinalScore(allRounds) + ":" + getTrollFinalScore(allRounds) + " equality");
			}
		} catch (SQLException e) {
			System.out.println("Cannot print info, because " + e.getMessage());
		}
	}

}