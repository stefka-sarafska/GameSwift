package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionWithDB {

	public static List<String> getPersonCharachteristics(int personId) {
		List<String> personInfo = new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root", "123456789");
				PreparedStatement ps = con.prepareStatement("select * from battle.people where person_id = ?;")) {
			ps.setInt(1, personId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				personInfo.add(rs.getString(2));
				personInfo.add(rs.getString(3));
				personInfo.add(rs.getString(4));
			}
		} catch (SQLException ex) {
			System.out.println("Cannot get this person, because " + ex.getMessage());
		}
		return personInfo;
	}

	public static List<String> getTrollCharachteristics(int trollId) {
		List<String> trollInfo = new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root", "123456789");
				PreparedStatement ps = con.prepareStatement("select * from battle.trolls where troll_id = ?;")) {
			ps.setInt(1, trollId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				trollInfo.add(rs.getString(2));
				trollInfo.add(rs.getString(3));
				trollInfo.add(rs.getString(4));
			}

		} catch (SQLException ex) {
			System.out.println("Cannot get this troll, because " + ex.getMessage());
		}
		return trollInfo;
	}

	public static void setInfoFromBattle(int numberOfPeople, int numberOfTrolls, int peoplePoints, int trollsPoints) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root", "123456789");
				PreparedStatement ps = con.prepareStatement(
						"insert into battle.batlle_information (people_number, trolls_number, people_points, trolls_points) values (?, ?, ?, ?) ;")) {
			ps.setInt(1, numberOfPeople);
			ps.setInt(2, numberOfTrolls);
			ps.setInt(3, peoplePoints);
			ps.setInt(4, trollsPoints);
			ps.execute();
		} catch (SQLException ex) {
			System.out.println("Cannot set info, because " + ex.getMessage());
		}
	}

	public static void printInfo(int id) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root", "123456789");
				PreparedStatement ps = con.prepareStatement("select * from battle.batlle_information where id = ?;")) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int personPoins = rs.getInt(4);
				int trolPoins = rs.getInt(5);
				System.out.println("Troll poins: " + trolPoins);
				System.out.println("People poins: " + personPoins);
				if (personPoins < trolPoins) {
					System.out.println("Trol wins");
				} else if (personPoins > trolPoins) {
					System.out.println("Person wins");
				} else {
					System.out.println("Equality");
				}

			}

		} catch (SQLException ex) {
			System.out.println("Cannot get this troll, because " + ex.getMessage());
		}
	}

}