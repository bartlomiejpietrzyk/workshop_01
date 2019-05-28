package pl.pyt0.workshops;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static pl.pyt0.workshops.lottoGame.getNumbers;
import static pl.pyt0.workshops.lottoGame.numberCompare;

public class LottoGame {

    public static void main(String[] args) {

        System.out.println("Cześć! \nWpisz swoje imie lub pseudonim!");
        Scanner scanner = new Scanner(System.in);
        String newUser = scanner.nextLine();

            System.out.printf("%s witaj w grze Lotto!\n", newUser);
        String positiveAnswer = "tak";

        try {
            do {

                System.out.println("Wpisz swoje 6 liczb i sprawdź czy wygrałeś!");

                int[] userNumbers = getNumbers(6, 1, 49);
                int[] randomNumbers = numGenerator(6, 1, 49);

                System.out.println(newUser + " Twoje liczby to: " + Arrays.toString(userNumbers));
                System.out.println("Wygenerowane liczby to : " + Arrays.toString(randomNumbers));

                System.out.println(numberCompare(userNumbers, randomNumbers));
                System.out.println("Czy chcesz grać dalej?\nWpisz: tak lub nie.");

                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase(positiveAnswer) == true) {
                    System.out.println("Świetnie, zacznijmy od nowa!");
                    continue;

                } else {
                    System.out.printf("Dziękuje za grę.");
                    break;
                }

            } while (true);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("coś poszło nie tak! " + e.getMessage());
        }
    }

    static int[] getNumbers(int count, int lowerBound, int upperBound) {
        int[] uniqueNumbers = new int[count];
        Arrays.fill(uniqueNumbers, lowerBound - 1);
        int counter = 0;
        try {
            do {
                System.out.printf("Podaj %d liczbę: ", counter + 1);
                Scanner scanner = new Scanner(System.in);

                while (!scanner.hasNextInt()) {
                    String badInput = scanner.nextLine();
                    System.out.printf("\"%s\" nie jest liczbą. Podaj poprawną liczbę: ", badInput);
                }

                int properNumber = scanner.nextInt();

                if (properNumber < lowerBound || properNumber > upperBound) {
                    System.out.printf("Podaj liczbę z zakresu od %d do %d!\n", lowerBound, upperBound);
                    continue;
                }

                if (Arrays.binarySearch(uniqueNumbers, properNumber) >= 0) {
                    System.out.printf("Podaj nową liczbę! \n");
                    continue;
                }

                uniqueNumbers[0] = properNumber;
                Arrays.sort(uniqueNumbers);
                counter++;

            } while (counter < count);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Coś poszło nie tak... próbój ponownie!" + e.getMessage());
        }
        return uniqueNumbers;
    }


    static int[] numGenerator(int count, int lowerBound, int upperBound) {
        int[] randNumbers = new int[count];
        Random genNumbers = new Random();
        Arrays.fill(randNumbers, lowerBound - 1);

        for (int i = 0; i < randNumbers.length; i++) {
            randNumbers[i] = genNumbers.nextInt(upperBound);
        }
        for (int num : randNumbers) {
            if (num < lowerBound) {
                num = lowerBound;
            }
        }
        Arrays.sort(randNumbers);
        return randNumbers;
    }


    static int numberCompare(int[] userArr, int[] genArr) throws ArrayIndexOutOfBoundsException {

        int equalNumbers = 0;

        for (int i = 0; i < userArr.length; i++) {
            if (userArr[i] == genArr[i]) {
                equalNumbers++;
            }
        }


        if (equalNumbers == 3) {
            System.out.printf("Wylosowałeś %d liczby. Wygrałeś 100 kolejnych gier!", equalNumbers);

        } else if (equalNumbers == 4) {
            System.out.printf("Wylosowałeś %d liczby. Wygrałeś 200 kolejnych gier!", equalNumbers);

        } else if (equalNumbers == 5) {
            System.out.printf("Wylosowałeś %d liczby. Wygrałeś 300 kolejnych gier!", equalNumbers);

        } else if (equalNumbers == 6) {
            System.out.printf("Wylosowałeś %d liczby. Rozbiłeś bank!", equalNumbers);

        } else {
            System.out.printf("Wylosowałeś %d liczb. Niestety nic nie wygrałeś!", equalNumbers);

        }
        return equalNumbers;
    }

}
