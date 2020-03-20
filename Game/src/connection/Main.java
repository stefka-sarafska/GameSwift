package connection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import model.Battle;
import model.Person;
import model.Troll;

public class Main {
	private static int allRoundsCounter = 0;

	public static void main(String[] args) throws SQLException {
        ConnectionWithDB.truncateTableBattleInfo();
		menu();

	}

	private static void menu() throws SQLException {
		List<Troll> trolls = new LinkedList<Troll>();
		List<Person> people = new LinkedList<Person>();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the number of People: ");
		int peopleCount = Integer.parseInt(scanner.nextLine());
		for (int i = 1; i <= peopleCount; i++) {
			people.add(createPersonFromDB(Person.getPersonCharachteristicsFromDB(i)));
		}
		System.out.println("Please enter the number of trolls: ");
		int trollCount = Integer.parseInt(scanner.nextLine());
		for (int i = 1; i <= trollCount; i++) {
			trolls.add(createTrollFromDB(Troll.getTrollCharachteristicsFromDB(i)));
		}
		battle(trolls, people);

	}

	private static void battle(List<Troll> trolls, List<Person> people) {
		while (trolls.size() > 0 && people.size() > 0) {
			allRoundsCounter++;
			Battle battle = new Battle(trolls.get(0), people.get(0));
			battle.createBattle();
			if (battle.getAliveHero() instanceof Person) {
				trolls.remove(0);
			} else if (battle.getAliveHero() instanceof Troll) {
				people.remove(0);
			}
			Battle.setInfoPerBattle(people.size(), trolls.size(), battle.getPersonScore(),
					battle.getTrollScore());
			Battle.printInfoPerBattle(allRoundsCounter);
		}
		ConnectionWithDB.printFinalResultAndWriteItToFile(allRoundsCounter);
	}

	private static Troll createTrollFromDB(List<String> playerInfo) {
		String name = playerInfo.get(0);
		int power = Integer.parseInt(playerInfo.get(1));
		int endurance = Integer.parseInt(playerInfo.get(2));
		return new Troll(name, power, endurance);
	}

	private static Person createPersonFromDB(List<String> playerInfo) {
		String name = playerInfo.get(0);
		int power = Integer.parseInt(playerInfo.get(1));
		int endurance = Integer.parseInt(playerInfo.get(2));
		return new Person(name, power, endurance);
	}
	
}
