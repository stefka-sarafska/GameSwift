import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Battle {
	private static int rounds = 0;
	List<Player> players = new LinkedList<>();

	public Battle(List<Player> players) {
		this.players = players;
	}

	public void getBattleDate() {

	}

	public void createBattle(Player troll, Player person) {
		rounds++;
		if (rounds % 2 == 1) {
			person.setEndurance(person.getEndurance() - 6);
		} else if (rounds % 2 == 0) {
			troll.setEndurance(troll.getEndurance() - 10);
		}

	}

	public void getPlayerByNameFromData(String name) throws SQLException {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/battle", "root",
				"123456789");
				PreparedStatement ps = con.prepareStatement("select * from person where name=?")) {

			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				
			}
			
		
		}
	}
}
