public class Enemy {

    public String enemyType;
    public int enemyAttack;
    public double enemyHealth;
    public int enemyDefense;

    public Enemy(int attack, double health, int defense) {
        this();
        this.enemyAttack = attack;
        this.enemyDefense = defense;
        this.enemyHealth = health;
    }

    public static String[] nameList = {"Rat", "Wolf"};
    public Enemy() {
        this.enemyType = nameList[MyLibrary.generator.nextInt(nameList.length)];
    }
}
