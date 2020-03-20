package connection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public static void printFinalResultAndWriteItToFile(int allRounds) {

		try {
			PrintWriter out = new PrintWriter(new FileOutputStream("results.txt"));
			String finalResult = " ";
			System.out.println("____________________________");
			System.out.println("Final result: ");
			if (getPersonFinalScore(allRounds) < getTrollFinalScore(allRounds)) {
				System.out
						.println(getTrollFinalScore(allRounds) + ":" + getPersonFinalScore(allRounds) + " for trolls");
				finalResult = getTrollFinalScore(allRounds) + ":" + getPersonFinalScore(allRounds) + " for trolls.\n";
				out.write(finalResult);
			} else if (getPersonFinalScore(allRounds) > getTrollFinalScore(allRounds)) {
				System.out
						.println(getPersonFinalScore(allRounds) + ":" + getTrollFinalScore(allRounds) + " for people");
				finalResult = getPersonFinalScore(allRounds) + ":" + getTrollFinalScore(allRounds) + " for people. \n";
				out.write(finalResult);
			} else {
				System.out.println(getPersonFinalScore(allRounds) + ":" + getTrollFinalScore(allRounds) + " equality");
				finalResult = getPersonFinalScore(allRounds) + ":" + getTrollFinalScore(allRounds) + " equality. \n";
				out.write(finalResult);
			}
			out.close();
		} catch (SQLException e) {
			System.out.println("Can not print info, because " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("Can not open file, beacause " + e.getMessage());
		}
	}

	public static void truncateTableBattleInfo() {
		try (Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root", "123456789");
			     Statement statement = connection.createStatement()) {
			  statement.execute("truncate table batlle_information");
			  System.out.println("Table truncated..");
		} catch (SQLException e) {
			System.out.println("Can not trancate table battle_information, because " + e.getMessage());
		}
	}
}