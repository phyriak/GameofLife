package pl.phyriak;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private Board board;

    public Game() {
        int size = readBoardSize();
        int percent = readPercentOfAlive();

        board = new Board(size, percent);
    }

    private int readBoardSize() {
        Scanner input = new Scanner(System.in);
        System.out.print("Podaj rozmiar planszy: ");

        return Integer.valueOf(input.nextLine());
    }

    private int readPercentOfAlive() {
        Scanner input = new Scanner(System.in);
        System.out.print("Podaj procent żywych komórek na początku: ");

        return Integer.valueOf(input.nextLine());
    }

    public void startGame() throws InterruptedException {

        int rounds = 0;

        do {
            rounds++;

            board.print();
            System.out.println("==========");
            TimeUnit.MILLISECONDS.sleep(500);
        } while (board.tick());

        board.print();

        System.out.println("There were " + rounds + " rounds");
    }
}
