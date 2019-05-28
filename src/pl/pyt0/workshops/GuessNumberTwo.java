package pl.pyt0.workshops;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GuessNumberTwo {

    public static void main(String[] args) throws InterruptedException {

        int max = 1000;
        int min = 0;
        int countTries = 1;
        int userInput = 0;
        int guess = ((max - min) / 2) + min;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cześć! Jak masz na imię?");
        String name = scanner.nextLine();
        try {
            System.out.printf("%s pomyśl liczbę od 0 do 1000, a ja ją zgadnę w maksymalnie 10 próbach\n", name);
//        do {
            System.out.printf("Wprowadź swoją liczbę: \n");
            userInput = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Błędny format! Próbuj ponownie");
        }
        guessNum(userInput, min, max, guess, countTries);
        System.out.printf("%s dzięki za gre!", name);
    }

    static int guessNum(int userInput, int min, int max, int guess, int countTries) throws InterruptedException {
        System.out.println("Więc zaczynajmy...");

        do {
            if (userInput == guess) {
                System.out.printf("%d próba. Mój typ to: %d. Strzał w 10'tke! Zgadłem za %d razem, wygrałem!\n", countTries, guess, countTries);
                break;
            } else if (userInput != guess) {
                while (userInput != guess) {
                    System.out.println("Chwila na zastanowienie...");
                    TimeUnit.SECONDS.sleep(1);
                    if (userInput > guess) {
                        System.out.printf("%d próba. \nMój typ to: %d. Podałem za mała liczbę...\n", countTries, guess);
                        min = guess;

                    } else if (userInput < guess) {
                        System.out.printf("%d próba. \nMój typ to: %d. Nie trafiłem, za duża liczba\n", countTries, guess);
                        max = guess;
                    }
                    countTries++;
                    guess = ((max - min) / 2) + min;
                    TimeUnit.SECONDS.sleep(1);

                }
            }
        } while (countTries <= 10);

        return guess;
    }
}