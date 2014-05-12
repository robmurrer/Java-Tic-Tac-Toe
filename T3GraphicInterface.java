import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

//graphical interface for the TicTacToe game class
class T3GraphicInterface extends T3Interface
{
    TicTacToe game;
    DrawPanel panel;
    JFrame application;

    //constructor
    public T3GraphicInterface(TicTacToe g)
    {
        //save the game to be use later
        game = g;

        //create a panel that we can draw on
        panel = new DrawPanel();

        //create the window
        application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add panel to window
        application.add(panel);

        //set display
        application.setSize(500,500);
        application.setVisible(true);
        application.setTitle("TicTacToe");
    }

    @Override
    public void getInput()
    {
        //empty because GUI is quite different than text and is unneccessary
    }


    @Override
    //this function gets called by the TicTacToe game letting us know to update the board
    public void paint()
    {
        //set title to give some user feedback as to who has won or whose turn it is
        application.setTitle("Player " + T3TextInterface.getChar(game.whoseTurn()) + "'s turn");
        if (game.getGameOver())
            if (game.getWinner() != TicTacToe.Cell.EMPTY)
                application.setTitle("Player " + T3TextInterface.getChar(game.getWinner()) + " won!");
            else
                application.setTitle("Game is a draw");

        //make sure we update the display
        panel.repaint();
    }


    //the surface that will be drawn to
    private class DrawPanel extends JPanel 
    {

        //constructor that adds a mouse listener
        public DrawPanel()
        {
            addMouseListener(new MouseClickHandler());
        }


        //method that paints the window
        public void paintComponent(Graphics g)
        {
            //paint the stuff in the super class
            super.paintComponent(g);

            //draw the cross marks
            drawCross(g);

            //draw the player positions
            drawMarks(g);
        }


        //draws the player positions 
        private void drawMarks(Graphics g)
        {
            //loop through each cell and draw if it isn't empty
            for (int i=0; i < 3; i++)
                for (int j=0; j < 3; j++)
                    if (game.getCell(i,j) != TicTacToe.Cell.EMPTY) 
                    {
                        //make sure to draw the correct player
                        if (game.getCell(i,j) == TicTacToe.Cell.X)
                            drawX(g, i, j);
                        else
                            drawO(g, i, j);
                    }
        }


        //draws the O, x,y is the array position not actual screen coordinate
        private void drawO(Graphics g, int x, int y)
        {
            //get dimensions of window
            int w = getWidth();
            int h = getHeight();

            //for top left corner of circle
            int xPos = 0, yPos = 0;

            //determine where the coordinate of the x component of the circle should be
            switch (x)
            {
                case 0:
                    xPos = 0;
                    break;
                
                case 1:
                    xPos = w/3;
                    break;

                case 2:
                    xPos = w*2/3;
            }
           
            
            //determine where the coordinate of the y component of the circle should be
            switch (y)
            {
                case 0:
                    yPos = 0;
                    break;
                case 1:
                    yPos = h/3;
                    break;
                case 2:
                    yPos = h*2/3;
                    break;
            }


            //draw the circle on the panel
            g.drawOval(xPos, yPos, w/3, h/3);
        }


        //draws the x on the board. x, y parameters are array indexes
        private void drawX(Graphics g, int x, int y)
        {    
            //window dimensions
            int w = getWidth();
            int h = getHeight();

            //arrays to hold points for the start and end of x's
            //the corners of the X's are determined like this:
            //  0   3
            //  2   1
            int[] xPoints = new int[4];
            int[] yPoints = new int[4];

            //determine x coordinate
            switch (x)
            {
                case 0:
                    xPoints[0] = xPoints[2] = 0;
                    xPoints[1] = xPoints[3] = w/3;
                break;

                case 1:
                    xPoints[0] = xPoints[2] = w/3;
                    xPoints[1] = xPoints[3] = w*2/3;
                break;

                case 2:
                    xPoints[0] = xPoints[2] = w*2/3;
                    xPoints[1] = xPoints[3] = w;
                break;
            }


            //determine y coordinates
            switch (y)
            {
                case 0:
                    yPoints[0] = yPoints[3] = 0;
                    yPoints[1] = yPoints[2] = h/3;
                    break;

                case 1:
                    yPoints[0] = yPoints[3] = h/3;
                    yPoints[1] = yPoints[2] = h*2/3;
                    break;

                case 2:
                    yPoints[0] = yPoints[3] = h*2/3;
                    yPoints[1] = yPoints[2] = h;
                    break;
            }

            //draw lines to make x
            g.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
            g.drawLine(xPoints[2], yPoints[2], xPoints[3], yPoints[3]);

        }


        //draws the board cross marks
        private void drawCross(Graphics g)
        {
            //window dimensions
            int w = getWidth();
            int h = getHeight();
            
            //vertical bars
            g.drawLine(w/3, 0, w/3, h);
            g.drawLine(w*2/3, 0, w*2/3, h);
            
            //horizontal bars
            g.drawLine(0, h/3, w, h/3);
            g.drawLine(0, h*2/3, w, h*2/3);

        }
        
      
        //processes mouse events and sends them to the gam
        private class MouseClickHandler extends MouseAdapter
        {
            public void mouseClicked (MouseEvent e)
            {
                //get the position of the mouse
                int x = e.getX();
                int y = e.getY();

                //window dimensions
                int w = getWidth();
                int h = getHeight();

                //System.out.println("test " + x + " " + y);
                //determine where the mouse was clicked and send it to the right cell in the game
                //left column
                if (x <= w/3)
                {
                    if (y <= h/3)
                    {
                        //first level
                        game.setCell(0,0);
                    }
                    else if (y <= h*2/3)
                    {
                        //second level
                        game.setCell(0,1);
                    }
                    else
                    {
                        //third level
                        game.setCell(0,2);
                    }
                }

                //center column
                else if (x <= w*2/3)
                {
                    if (y <= h/3)
                    {
                        //first level
                        game.setCell(1,0);
                    }
                    else if (y <= h*2/3)
                    {
                        //second level
                        game.setCell(1,1);
                    }
                    else
                    {
                        //third level
                        game.setCell(1,2);
                    }
                }

                //right column
                else 
                {
                    if (y <= h/3)
                    {
                        //first level
                        game.setCell(2,0);
                    }
                    else if (y <= h*2/3)
                    {
                        //second level
                        game.setCell(2,1);
                    }
                    else
                    {
                        //third level
                        game.setCell(2,2);
                    }
                }

            }
        }

    }
}

