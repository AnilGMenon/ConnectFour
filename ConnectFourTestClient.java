
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANIL MENON
 * @version 1.0
 */
public class ConnectFourTestClient {

    /**
     * tests the ConnectFourGame class by user input.
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // ConnectFourEnum person = new ConnectFourEnum("Black");
        ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.BLACK);
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(game.toString());
            System.out.println(game.getTurn()
                    + ": Where do you want to mark? Enter row column");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            scanner.nextLine();
            game.takeTurn(row, column);

        } while (game.getGameState() == ConnectFourEnum.IN_PROGRESS);
        System.out.println(game.getGameState());
    }
}
