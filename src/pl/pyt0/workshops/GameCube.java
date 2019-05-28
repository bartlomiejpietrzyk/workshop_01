package pl.pyt0.workshops;

import java.util.Random;
import java.util.Scanner;

public class GameCube {

    public static void main(String[] args) throws NumberFormatException {
        System.out.println("Wpisz jaki rzut chcesz wykonać.\nFormat:");
        System.out.println("x - liczba rzutów koścmi");
        System.out.println("Dy - rodzaj kostki - y - to liczba ścian ");
        System.out.println("z - dodawanie/odejmowanie oczek");
        Scanner scanner = new Scanner(System.in);
        String cubeToUse = scanner.nextLine();
        System.out.println("Wyrzuciłeś " + drawCube(cubeToUse) + " oczek.");
    }

    static int drawCube(String cubeToUse) {
        System.out.println(cubeToUse);
        int numDraws = getNumOfDraws(cubeToUse);
        int cubeType = getCubeType(cubeToUse);
        int toBeAdded = getToBeAdded(cubeToUse);
        int draw = draw(cubeType, numDraws) + toBeAdded;
        return draw;
    }

    static int getNumOfDraws(String cubeToUse) {
        String numDrawsString = "";
        int numDraws = 1;
        for (int i = 1; i < cubeToUse.length(); i++) {
            try {
                numDrawsString = cubeToUse.substring(0, i);
                numDraws = Integer.parseInt(numDrawsString);
            } catch (NumberFormatException e) {
                return numDraws;
            }
        }
        return numDraws;
    }

    static int getCubeType(String cubeToUse) {
        int indexOfD = cubeToUse.indexOf('D') + 1;
        int indexOfPlus = cubeToUse.indexOf('+');
        if (indexOfPlus == -1) {
            indexOfPlus = cubeToUse.length();
        }
        String cubeTypeString = cubeToUse.substring(indexOfD, indexOfPlus);
        int cubeType = Integer.parseInt(cubeTypeString);
        return cubeType;
    }

    static int getToBeAdded(String cubeToUse) {
        int indexOfPlus = cubeToUse.indexOf('+');
        if (indexOfPlus == -1) {
            return 0;
        }
        String toBeAddedString = cubeToUse.substring(indexOfPlus);
        int toBeAdded = Integer.parseInt(toBeAddedString);
        return toBeAdded;
    }

    static int draw(int cubeType, int numOfDraws) {
        Random r = new Random();
        int draw = 0;
        for (int i = 0; i < numOfDraws; i++) {
            draw += r.nextInt(cubeType) + 1;
        }
        return draw;
    }
}