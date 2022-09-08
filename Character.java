import java.util.*;

public class Character {
   
    public static Random generator = new Random();
    
    public String name;
    public int attackStrength;
    public double health;
    public int defense;
    int currentDefense;
    int damageTaken;
    int damage;
    int damageDealt;
    
    public Character(int str, int def, int health) {
        this();
        this.attackStrength = str;
        this.defense = def;
        this.health = health;
    }
    
    
    
    public static String[] nameList = {"Geoff", "Garry", "Bob", "Liam", "Olivia", "Noah", "Emma", "Oliver", "Charlotte", "Elijah", "Amelia", "James", "Ava", "William", "Sophia", "Isabella", "Lucas", "Mia", "Henry", "Evelyn", "Theodore", "Harper"};


    public Character() {
        this.name = nameList[generator.nextInt(nameList.length)];
}
    public int takeDamage(int damage) {
         currentDefense = generator.nextInt(this.defense-4, this.defense+4);
        if (damage - currentDefense < 0) {
            damage = 0;
        }
        damageTaken = damage - currentDefense;
        this.health -= damageTaken;
        return damageTaken;

    }
    public int attack(Character target) {
        damage = generator.nextInt(attackStrength,attackStrength*2);
        damageDealt = target.takeDamage(damage);
        return damageDealt;
    }
    public boolean areYouDead() {
        return this.health> 0;

    }
    
}
