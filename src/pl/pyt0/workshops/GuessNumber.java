package pl.pyt0.workshops;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

    public static void main(String[] args) {
        int userNum = 0;
        int genNum = 0;
        int countTries = 0;
        checker(userNum, genNum, countTries);
    }

    public static void checker(int userNumber, int genNumber, int counter) {
        Random random = new Random();

        genNumber = random.nextInt(100);
        System.out.println("Zgadnij liczbę z zakresu 1-100: ");

        try {
            while (userNumber != genNumber) {
                counter++;
                Scanner scanner = new Scanner(System.in);
                userNumber = scanner.nextInt();
                if (userNumber > genNumber) {
                    System.out.println("Podana liczba jest za duża! Spróbuj ponownie! ");
                } else if (userNumber < genNumber) {
                    System.out.println("Podana liczba jest za mała! Spróbuj ponownie! ");
                } else {
                    System.out.println("Brawo! Zgadłeś za " + counter + " razem!");
                    continue;
                }
            }

        } catch (InputMismatchException e) {
            System.err.println("Błąd, to nie jest liczba. Spróbuj ponownie!");
        }
    }
}
