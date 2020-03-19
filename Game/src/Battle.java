
public class Battle {
    public static int rounds = 0;
    public static int personScore = 0;
    public static int trollScore = 0;
    Troll troll;
    Person person;

    public Battle(Troll troll, Person person) {
        this.troll = troll;
        this.person = person;
    }

    public void updateScore() {
        Player hero = createBattleAndGetHero();
        if (hero instanceof Troll) {
            trollScore++;
        } else if (hero instanceof Person) {
            personScore++;
        }
    }

    public Player createBattleAndGetHero() {
        while (person.getEndurance() > 0 && troll.getEndurance() > 0) {
            rounds++;
            if (rounds % 2 == 1) {
                person.setEndurance(person.getEndurance() - 6);
            } else if (rounds % 2 == 0) {
                troll.setEndurance(troll.getEndurance() - 10);
            }
        }
        if (troll.getEndurance() > 0) {
            return troll;
        } else if (person.getEndurance() > 0) {
            return person;
        }
        return null;

    }

    public static int getRounds() {
        return rounds;
    }
}
