package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 950;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {280, 270, 250,250,350,200,200,};
    public static int[] heroesDamage = {10, 15, 20,0,5,15,15};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic","Golem","Lucky","Berserk"};
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }




    public static void round() {
        round_number++;
        chooseDefence();
        bossHits();
        heroesHit();
        printStatistics();
        medicHill();
        golemDefence();
        evasion1();
        stun();
        berserkShoot();

    }
    public static void medicHill() {
        for (int i = 0; i < 1; i++) {
            Random random = new Random();
            int randomHill = random.nextInt(40);
            int randomHero = random.nextInt(2);
            if (heroesHealth[i] > 0 && heroesHealth[3] > 0 && heroesHealth[i] < 100) {
                heroesHealth[randomHero] = heroesHealth[randomHero] + randomHill;
                System.out.println("Медик вылечел:" + randomHill);
            }
        }
    }
        public static void golemDefence() {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[6] > 0 && heroesHealth[i] > 0 && heroesHealth[6] != heroesHealth[i]) {
                    heroesHealth[i] += bossDamage / 5;
                    heroesHealth[6] -= bossDamage / 5;
                }

            }
        }
            public static void evasion1() {
                Random random = new Random();
                int randomEvasion = random.nextInt(4)+1;
                switch (randomEvasion) {
                    case 1 :
                        heroesHealth[6] = heroesHealth[6] + bossDamage;
                        System.out.println("Лаки уклонился!");
                    case 2:

                    case 3:

                    case 4:
                }

            }
            public static void stun() {
                Random random = new Random();
                boolean stun = random.nextBoolean();
                if (stun) {
                    bossDamage = 0;
                    System.out.println("Босс оглушен!");
                } else {
                    bossDamage = 50;
                }
            }
            public static void berserkShoot() {
        Random random = new Random();
        int randomDamage = random.nextInt(15) +1;
        int randomC = random.nextInt(3) +1;
        if (heroesHealth[4] > 0 && bossHealth > 0) {
            switch (randomC) {
                case 1 :
                    heroesDamage[4] = (heroesDamage[4] + bossDamage) - randomDamage;
                    System.out.println("Берсерка урон критический!");
                    System.out.println("Потери при увелечении урон берсерка:"+ randomDamage);
                    break;
                case 2 :
                    bossDamage=50;
                    break;
                case 3 :
                    bossDamage=50;
                    break;
            }
        }
            }


    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage; // heroesHealth[i] -= bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(12) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];


                    }
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND --------------------");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + "); " + bossDefenceType);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + " (" + heroesDamage[i] + ")");
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
}
