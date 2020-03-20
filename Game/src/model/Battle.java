package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Battle {
	public int rounds = 0;
	public int personScore = 0;
	public int trollScore = 0;
	Troll troll;
	Person person;

	public Battle(Troll troll, Person person) {
		this.troll = troll;
		this.person = person;
	}

	public void createBattle() {
		while (person.getEndurance() > 0 && troll.getEndurance() > 0) {
			rounds++;
			if (rounds % 2 == 1) {
				person.setEndurance(person.getEndurance() - troll.getPower());
			} else if (rounds % 2 == 0) {
				troll.setEndurance(troll.getEndurance() - person.getPower());
			}
		}
		updateScore();
	}

	public static void printInfoPerBattle(int round) {

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root", "123456789");
				PreparedStatement ps = con.prepareStatement("select * from battle.batlle_information where id = ?;")) {
			ps.setInt(1, round);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int personPoins = rs.getInt(4);
				int trolPoins = rs.getInt(5);
				System.out.println("____________________________");
				System.out.println("Round " + round);
				System.out.println("Troll poins: " + trolPoins);
				System.out.println("People poins: " + personPoins);
				System.out.println("Players info after round:");
			}

		} catch (SQLException ex) {
			System.out.println("Cannot print info, because " + ex.getMessage());
		}
	}

	public static void setInfoPerBattle(int numberOfPeople, int numberOfTrolls, int peoplePoints, int trollsPoints) {
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

	private void updateScore() {
		Player hero = getAliveHero();
		if (hero instanceof Troll) {
			trollScore = 1;
			personScore = 0;
		} else if (hero instanceof Person) {
			personScore = 1;
			trollScore = 0;
		} else if (hero == null) {
			personScore = 0;
			trollScore = 0;
		}
	}

	public Player getAliveHero() {
		if (troll.getEndurance() > 0) {
			return troll;
		} else if (person.getEndurance() > 0) {
			return person;
		}
		return null;
	}

	public int getPersonScore() {
		return personScore;
	}

	public void setPersonScore(int personScore) {
		this.personScore = personScore;
	}

	public int getTrollScore() {
		return trollScore;
	}

	public void setTrollScore(int trollScore) {
		this.trollScore = trollScore;
	}

	public int getRounds() {
		return rounds;
	}

	public Troll getTroll() {
		return troll;
	}

	public Person getPerson() {
		return person;
	}

}
