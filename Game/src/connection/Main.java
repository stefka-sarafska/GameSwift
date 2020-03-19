package connection;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import model.Battle;
import model.Person;
import model.Troll;

public class Main {
	public static void main(String[] args) throws SQLException {

		menu();
		ConnectionWithDB.printInfo(1);
	}

	private static void menu() throws SQLException {
		List<Troll> trolls = new LinkedList<Troll>();
		List<Person> people = new LinkedList<Person>();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the number of People: ");
		int peopleCount = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < peopleCount; i++) {
			people.add(createPerson(ConnectionWithDB.getPersonCharachteristics(i)));
		}
		System.out.println("Please enter the number of trolls: ");
		int trollCount = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < trollCount; i++) {
			trolls.add(createTroll(ConnectionWithDB.getTrollCharachteristics(i)));
		}
		battle(trolls, people);

	}

	private static void battle(List<Troll> trolls, List<Person> people) {
		while (trolls.size() > 0 && people.size() > 0) {
			Battle battle = new Battle(trolls.get(0), people.get(0));
			battle.createBattle();
			if (battle.getAliveHero() instanceof Person) {
				trolls.remove(0);
			} else if (battle.getAliveHero() instanceof Troll) {
				people.remove(0);
			}
			ConnectionWithDB.setInfoFromBattle(people.size(), trolls.size(), Battle.getPersonScore(),
					Battle.getTrollScore());

		}
	}

	private static Troll createTroll(List<String> playerInfo) {
		String name = playerInfo.get(0);
		int power = Integer.parseInt(playerInfo.get(1));
		int endurance = Integer.parseInt(playerInfo.get(2));
		return new Troll(name, power, endurance);
	}

	private static Person createPerson(List<String> playerInfo) {
		String name = playerInfo.get(0);
		int power = Integer.parseInt(playerInfo.get(1));
		int endurance = Integer.parseInt(playerInfo.get(2));
		return new Person(name, power, endurance);
	}
}
