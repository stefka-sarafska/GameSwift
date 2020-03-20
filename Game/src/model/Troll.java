package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Troll extends Player {

	public Troll(String name, int power, int endurance) {
		super(name, power, endurance);
	}

	public static List<String> getTrollCharachteristicsFromDB(int trollId) {
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

	public static void printTrollInfo(List<String> trollInfo) {
		System.out.println("Troll name: " + trollInfo.get(0));
		System.out.println("Troll endurance: " + trollInfo.get(2));
	}
}
