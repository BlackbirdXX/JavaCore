import java.util.Random;
import java.util.Scanner;

public class Program {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random rand = new Random();
    private static final int WIN_COUNT = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '*';
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static char[][] field;

    public static void main(String[] args) {
        while (true) {
            fieldInit();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkState(DOT_HUMAN, "Вы победили!")) {
                    break;
                }
//                aiTurn();
//                printField();
//                if (checkState(DOT_AI, "ЭВМ победила!")) {
//                    break;
//                }
            }
            System.out.println("Сыграть еще? \n(Y - да) : ");
            if (!scanner.next().equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    static void fieldInit() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        field = new char[fieldSizeX][fieldSizeY];
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printField() {
        System.out.print("+");
        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print("-" + (x + 1));
        }
        System.out.println("-");

        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }
        for (int x = 0; x < fieldSizeX * 2 + 2; x++) {
            System.out.print("-");
        }
        System.out.println();
    }

    static void humanTurn() {
        int x;
        int y;
        do {
            System.out.printf("Введите координаты X и Y\n(от 1 до %d для X)\n(от 1 до %d для Y)\nДва числа через пробел.", fieldSizeX, fieldSizeY);
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }


    static void aiTurn() {
        int x;
        int y;
        do {
            x = rand.nextInt(fieldSizeX);
            y = rand.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Переработанный метод определения победы.
     * @param dot
     * @return
     */
    static boolean checkWin(char dot){
        for(int x = 0; x < fieldSizeX; x++){

            for (int y = 0; y < fieldSizeY; y++){
                if (checkRight(x, y, dot) || checkDown(x, y, dot) || checkDownR(x, y, dot) || checkDownL(x, y, dot)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод проверки победы по горизонтали (движение курсора вправо).
     * @param x
     * @param y
     * @param dot
     * @return
     */
    static boolean checkRight(int x, int y, char dot){
        if (field[x][y] != dot){
            return false;
        }
        int counter = 0;

        for (int i = 0; i < WIN_COUNT; i++) {
            if (isCellValid(x, y + i) && field[x][y + i] == dot) {
                counter += 1;
            }
        }
        return counter == WIN_COUNT;
    }

    /**
     * Метод проверки победы по вертикали (движение курсора вниз).
     * @param x
     * @param y
     * @param dot
     * @return
     */
    static boolean checkDown(int x, int y, char dot){
        if (field[x][y] != dot){
            return false;
        }
        int counter = 0;

        for (int i = 0; i < WIN_COUNT; i++) {
            if (isCellValid(x + i, y) && field[x + i][y] == dot) {
                counter += 1;
            }
        }
        return counter == WIN_COUNT;
    }

    /**
     * Метод проверки победы по диагонали (движение курсора вниз и вправо).
     * @param x
     * @param y
     * @param dot
     * @return
     */
    static boolean checkDownR(int x, int y, char dot){
        if (field[x][y] != dot){
            return false;
        }
        int counter = 0;

        for (int i = 0; i < WIN_COUNT; i++) {
            if (isCellValid(x + i, y + i) && field[x + i][y + i] == dot) {
                counter += 1;
            }
        }
        return counter == WIN_COUNT;
    }

    /**
     * Метод проверки победы по диагонали (движение курсора вниз и влево).
     * @param x
     * @param y
     * @param dot
     * @return
     */
    static boolean checkDownL(int x, int y, char dot){
        if (field[x][y] != dot){
            return false;
        }
        int counter = 0;

        for (int i = 0; i < WIN_COUNT; i++) {
            if (isCellValid(x + i, y - i) && (field[x + i][y - i] == dot)) {
                counter += 1;
            }
        }
        return counter == WIN_COUNT;
    }


    static boolean checkState(char dot, String s){
        if (checkWin(dot)){
            System.out.println(s);
            return true;
        }
        else if (checkDraw()) {
            System.out.println("Ничья");
            return true;
        }
        return false;
    }
}
