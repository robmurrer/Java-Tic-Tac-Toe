/*  T3Textinterface.java
 *  TicTacToe text interface
 *
 *  Rob Murrer
 *  November 9, 2012 
 *  Section Leader: Gurrala Chandan Reddy
 */

import java.util.Scanner;

public class T3TextInterface extends T3Interface
{

    private TicTacToe game;

    //constructor
    public T3TextInterface(TicTacToe g)
    {
        game = g;
    }

    @Override
    //displays the current state of the game to the user
    public void paint()
    {
        System.out.printf(" _______________________ \n");
        System.out.printf("|       |       |       |\n");
        System.out.printf("|   %c   |   %c   |   %c   |\n", getChar(game.getCell(0,0)),getChar(game.getCell(0,1)),getChar(game.getCell(0,2)));
        System.out.printf("|_______|_______|_______|\n");
        System.out.printf("|       |       |       |\n");
        System.out.printf("|   %c   |   %c   |   %c   |\n", getChar(game.getCell(1,0)),getChar(game.getCell(1,1)),getChar(game.getCell(1,2)));
        System.out.printf("|_______|_______|_______|\n");
        System.out.printf("|       |       |       |\n");
        System.out.printf("|   %c   |   %c   |   %c   |\n", getChar(game.getCell(2,0)),getChar(game.getCell(2,1)),getChar(game.getCell(2,2)));
        System.out.printf("|_______|_______|_______|\n");

        //print whose turn it is or who won
        if (game.getGameOver())
        {
            //draw?
            if (game.getWinner() == TicTacToe.Cell.EMPTY)
                System.out.printf("The game is a draw.\n");
            
            //not a draw
            else
                System.out.printf("Player %c wins.\n", getChar(game.getWinner()));
        }
        else
            System.out.printf("Player %c's turn.\n", getChar(game.whoseTurn()));

    }

    @Override
    //gets input from the user and messages that back to the game
    public void getInput()
    {
        Scanner input = new Scanner(System.in);
        int row, col;
       
        //make sure we loop until we get proper input
        while(true)
        {
            //loop until a row has been entered successfully
            while(true)
            {
                System.out.printf("Player %c: Enter row ( 0, 1 or 2 ): ", getChar(game.whoseTurn()));
                try
                {
                    row = input.nextInt();
                    break;
                }
                catch (Exception e)
                {
                    System.out.printf("Error: please enter in the correct format\n");
                    input.nextLine();
                }
            }

            //loop until a column has been entered successfully
            while(true)
            {
                System.out.printf("Player %c: Enter column ( 0, 1 or 2 ): ", getChar(game.whoseTurn()));
                try
                {
                    col = input.nextInt();
                    break;
                }
                catch (Exception e)
                {
                    System.out.printf("Error: please enter in the correct format\n");
                    input.nextLine();
                }

            }

           
            //check to see valid input range and if cell is empty
            if (game.setCell(row, col))
                return;

            System.out.printf("Error: Out of bounds or Cell has already been marked.  Try Again.\n");
        }

    }

    //returns the character associated with the cell value
    public static char getChar(TicTacToe.Cell mark)
    {
        if (mark == TicTacToe.Cell.X)
            return 'X';
        else if ( mark == TicTacToe.Cell.O)
            return 'O';
        else
            return ' ';
    }

}

