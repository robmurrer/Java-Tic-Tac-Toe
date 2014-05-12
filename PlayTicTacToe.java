/*  PlayTicTacToe.java
 *  Plays a game of TicTacToe
 * 
 *  Rob Murrer
 *  November 9, 2012 
 *  Section Leader: Gurrala Chandan Reddy
 */

public class PlayTicTacToe
{

    public static void main(String args[])
    {
        //set up game
        TicTacToe game = new TicTacToe();

        //load interface
        //T3Interface t3interface = new T3TextInterface(game);
        T3Interface t3interface = new T3GraphicInterface(game);
        
        //set interface
        game.setInterface(t3interface);

        //play game
        game.play();

    }
}

