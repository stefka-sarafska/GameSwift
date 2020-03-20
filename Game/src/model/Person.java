package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Person extends Player {

	public Person(String name, int power, int endurance) {
		super(name, power, endurance);
	}



	public static void printPersonInfo(List<String> personInfo) {
		System.out.println("Person name: " + personInfo.get(0));
		System.out.println("Person endurance: " + personInfo.get(2));
	}
	public static List<String> getPersonCharachteristicsFromDB(int personId) {
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

}
