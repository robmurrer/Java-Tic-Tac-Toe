/*  TicTacToe.java
 *  Simulates a game of TicTacToe between two players
 
 *  Rob Murrer
 *  November 9, 2012 
 *  Section Leader: Gurrala Chandan Reddy
 */


public class TicTacToe
{
    //Data structure for cell contents
    public enum Cell
    {
        X,
        O,
        EMPTY
    }
   
    //variables used to store game data
    private Cell[][] board = new Cell[3][3];
    private Cell whoseTurn = Cell.X;
    private Cell winner = Cell.EMPTY;
    private boolean gameOver = false;
    private T3Interface t3interface;
    
    //constructor 
    public TicTacToe()
    {
        //set board to all EMPTY
        initBoard();
    }

    //sets which interface to use
    public void setInterface(T3Interface i)
    {
        t3interface = i;
    }


    //play game
    public void play()
    {
        //game loop
        while(true)
        {
            //updates interface
            t3interface.paint();

            //gets user input
            t3interface.getInput();
           
            //check if game is over
            if (gameIsOver())
            {
                gameOver = true;
                break;
            }
        } //game loop

        //one last update to show who won
        t3interface.paint();
    }
    
    //set board to be all EMPTY
    private void initBoard()
    {
        for (int i=0; i < 3; i++)
            for (int j=0; j < 3; j++)
                board[i][j] = Cell.EMPTY;
    }


    //checks to see if game is over
    private boolean gameIsOver()
    {
        //check for horizontal 3 in a row
        if (horizontalWin())
            return true;
        
        //check if vertical 3 in a row
        if (verticalWin())
            return true;

        //check for diagnal
        if (diagnalWin())
            return true;

        //make sure this is checked last because full board won't set a winner
        //this is only for ties
        if (fullBoard())
            return true;

        //game is not over if all checks return false
        return false;
    }

    //check for horizontal win
    //if there is a winner the winner variable is set
    private boolean horizontalWin()
    {
        //loops through each row
        for (int i=0; i < 3; i++)
        {   
            //set possible winner to first mark in row
            Cell possible_winner = board[i][0];

            //if it is empty then obviously not a winning row
            //continue on to next row
            if (possible_winner == Cell.EMPTY)
                continue;

            //iterate through each cell in row and see if it matches possible winner
            for(int j=1; j < 3; j++)
            {
                //next cell equal to previous?
                if (board[i][j] == possible_winner)
                {
                    //last cell in row?
                    if (j == 2)
                    {
                        //set winner and return true
                        winner = possible_winner;
                        return true;
                    }
                }
                
                //if possible_winner and next cell aren't the same don't bother checking rest of
                //of the cells. just continue to next row
                else
                    break;
            }
        }

        return false;
    }
    
    //check for vertical win 
    //if there is a winner the winner variable is set
    private boolean verticalWin()
    {
        //loops through each column
        for (int i=0; i < 3; i++)
        {   
            //set possible winner to first mark in column
            Cell possible_winner = board[0][i];

            //if it is empty then obviously not a winning col
            //continue on to next row
            if (possible_winner == Cell.EMPTY)
                continue;

            //iterate through each cell in col and see if it matches possible winner
            for(int j=1; j < 3; j++)
            {
                //next cell equal to previous?
                if (board[j][i] == possible_winner)
                {
                    //last cell in row?
                    if (j == 2)
                    {
                        //set winner and return true
                        winner = possible_winner;
                        return true;
                    }
                }
                
                //if possible_winner and next cell aren't the same don't bother checking rest of
                //of the cells. just continue to next row
                else
                    break;
            }
        }

        return false;
    }


    //checks if there is a diagnol win
    //if there is the winner variable is set
    private boolean diagnalWin()
    {
        //check left to right
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) 
        {
            //make sure we don't have an empty top left corner
            if (board[0][0] != Cell.EMPTY)
            {
                winner = board[0][0];
                return true;
            }
        }
        
        //check right to left
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) 
        {
            //make sure we don't have an empty top right corner
            if (board[0][2] != Cell.EMPTY)
            {
                winner = board[0][2];
                return true;
            }
        }

        return false;
    }

   
    //checks for a full board
    //if full board exists and this method has been called
    //then it must be a tie.
    //winner is left a empty
    private boolean fullBoard()
    {
        //all places filled?
        //iterate through every square making sure
        //no empties exist, if they do then game is still on
        for (int i=0; i < 3; i++)
            for(int j=0; j < 3; j++)
                if (board[i][j] == Cell.EMPTY)
                    return false;

        //must be full if we made it this far
        return true;
    }
    
    
    //public interfaces
    
    public Cell getCell(int row, int col)
    {
        return board[row][col];
    }


    //set value of cell
    //making sure it is empty first
    //automatically selects the correct player
    public boolean setCell(int row, int col)
    {
        //check for out of bounds on array
        if (row < 0 || row > 2 || col < 0 || col > 2)
            return false;

        //System.out.println("Cell " + row + " " + col + " set");


        if (board[row][col] == Cell.EMPTY)
        {
            board[row][col] = whoseTurn;

            //update whose turn it is
            if (whoseTurn == Cell.X)
                whoseTurn = Cell.O;
            else
                whoseTurn = Cell.X;

            //success seting value
            return true;
        }

        //couldn't set that cell because it was already taken
        return false;
    }


    public Cell whoseTurn()
    {
        return whoseTurn;
    }


    public Cell getWinner()
    {
        return winner;
    }
    
    public boolean getGameOver()
    {
        return gameOver;
    }

        
}
