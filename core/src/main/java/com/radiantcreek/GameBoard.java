package com.radiantcreek;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameBoard {
    private int[][] board; //your data structure
    private int numBombs; //the number of bombs in the grid
    private int numFlags; //the number of flags that have been placed
    public static final int BOMB = -1; //help with readability
    private GameplayScreen gameplayScreen;

    private Texture emptyTile;
    private Texture emptyFloorTile;
    private Texture oneTile;
    private Texture twoTile;
    private Texture threeTile;
    private Texture fourTile;
    private Texture fiveTile;
    private Texture sixTile;
    private Texture sevenTile;
    private Texture eightTile;
    private Texture bombTile;
    private Texture flagTile;

    private boolean firstClick = false;
    

    public GameBoard(GameplayScreen gameplayScreen) {
        this.gameplayScreen = gameplayScreen;
        board = new int[16][30];
        numBombs = 50;
        numFlags = 0;
        loadGraphics();
        addBombs();
        initBoardNumbers();
    }

    public GameBoard(GameplayScreen gameplayScreen, int numRows, int numCols, int numBombs) {
        this.gameplayScreen = gameplayScreen;
        board = new int[numRows][numCols];
        this.numBombs = numBombs;
        this.numFlags = this.numBombs;
        loadGraphics();
        addBombs();
        initBoardNumbers();
    }

    public void loadGraphics() {
        emptyTile = new Texture("assets/emptyTile.jpg");
        emptyFloorTile = new Texture("assets/empty floor.jpg");
        oneTile = new Texture("assets/oneTile.jpg");
        twoTile = new Texture("assets/twoTile.jpg");
        threeTile = new Texture("assets/threeTile.jpg");
        fourTile = new Texture("assets/fourTile.jpg");
        fiveTile = new Texture("assets/fiveTile.jpg");
        sixTile = new Texture("assets/sixTile.jpg");
        sevenTile = new Texture("assets/sevenTile.jpg");
        eightTile = new Texture("assets/eightTile.jpg");
        flagTile = new Texture("assets/flagTile.jpg");
        bombTile = new Texture("assets/bomb.jpg");
    }

    //add numBombs bombs to the gameboard
    //you need to make sure that the correct number of bombs are placed
    public void addBombs() {
        int i = 0;
        while (i < numBombs) {
            Random random = new Random();
            int x = random.nextInt(board.length);
            int y = random.nextInt(board[0].length);
            if (board[x][y] == -1) {
                i--;
            }
            else if (board[x][y] <10) {
                board[x][y] = -1;
            }
            else {
                i--;
            }
            i++;
        }
    }

    //return the Location on the GameBoard corresponding to a given mouseX, MouseY position
    public Location getTileAt(int mouseX, int mouseY) {
        System.out.println(mouseX/25-4);
        System.out.println(mouseY/25-4);
        Location loc = new Location(mouseX/25-4, mouseY/25-4);
        System.out.println(loc);
        if (loc.getRow() < 0 || loc.getRow() > board[0].length-1 ||loc.getCol() < 0 || loc.getCol() > board.length-1) {
            return null;
        }
        System.out.println(loc.getRow() + " " + loc.getCol());
        return loc;
    }

    public void handleLeftClick(int x, int y) {
        Location loc = getTileAt(x, y);
        if (loc != null && firstClick == false && board[loc.getCol()][loc.getRow()] == -1) {
            //firstClick = true;
            board[loc.getCol()][loc.getRow()] = surroundingBombs(loc.getCol(), loc.getRow());
            int i = 0;
            while (i < 1) {
                Random random = new Random();
                int a = random.nextInt(board.length);
                int b = random.nextInt(board[0].length);
                if (board[a][b] <10 && board[a][b] > -1) {
                    board[a][b] = -1;
                    if (a > 0 && b < board[0].length -1 && board[a-1][b+1] != -1) {
                        board[a-1][b+1] = surroundingBombs(a-1, b+1);
                    }
                    if (b > 0 && a < board.length -1 && board[a+1][b-1] != -1) {
                        board[a+1][b-1] = surroundingBombs(a+1, b-1);
                    }
                    if (a > 0 && b > 0 && board[a-1][b-1] != -1) {
                        board[a-1][b-1] = surroundingBombs(a-1, b-1);
                    }
                    if (a < board.length-1 && b < board[0].length-1 && board[a+1][b+1] != -1) {
                        board[a+1][b+1] = surroundingBombs(a+1, b+1);
                    }
                    if (a < board.length-1 && board[a+1][b] != -1) {
                        board[a+1][b] = surroundingBombs(a+1, b);
                    }
                    if (b < board[0].length-1 && board[a][b+1] != -1) {
                        board[a][b+1] = surroundingBombs(a, b+1);
                    }
                    if (a> 0 && board[a-1][b] != -1) {
                        board[a-1][b] = surroundingBombs(a-1, b);
                    }
                    if (b> 0 && board[a][b-1] != -1) {
                        board[a][b-1] = surroundingBombs(a, b-1);
                    }
                    
                    a = loc.getCol();
                    b= loc.getRow();


                    if (a > 0 && b < board[0].length -1 && board[a-1][b+1] != -1) {
                        board[a-1][b+1] = surroundingBombs(a-1, b+1);
                    }
                    if (b > 0 && a < board.length -1 && board[a+1][b-1] != -1) {
                        board[a+1][b-1] = surroundingBombs(a+1, b-1);
                    }
                    if (a > 0 && b > 0 && board[a-1][b-1] != -1) {
                        board[a-1][b-1] = surroundingBombs(a-1, b-1);
                    }
                    if (a < board.length-1 && b < board[0].length-1 && board[a+1][b+1] != -1) {
                        board[a+1][b+1] = surroundingBombs(a+1, b+1);
                    }
                    if (a < board.length-1 && board[a+1][b] != -1) {
                        board[a+1][b] = surroundingBombs(a+1, b);
                    }
                    if (b < board[0].length-1 && board[a][b+1] != -1) {
                        board[a][b+1] = surroundingBombs(a, b+1);
                    }
                    if (a> 0 && board[a-1][b] != -1) {
                        board[a-1][b] = surroundingBombs(a-1, b);
                    }
                    if (b> 0 && board[a][b-1] != -1) {
                        board[a][b-1] = surroundingBombs(a, b-1);
                    }
                    i++;
                }
            }
        }
        if (loc!=null && board[loc.getCol()][loc.getRow()] == 0) {
            firstClick = true;
            clearNeighbours(loc);
        }

        else if (loc != null && board[loc.getCol()][loc.getRow()] < 9) {
            if (board[loc.getCol()][loc.getRow()] == -1) {
                gameplayScreen.setGameOn(false);
            }
            board[loc.getCol()][loc.getRow()] += 10;
            System.out.println(board[loc.getCol()][loc.getRow()]);
        }
        boolean found = false;
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j< board[0].length; j++) {
                if (board[i][j] == -1) {
                    found = true;
                }
            }
        }

        // if there are no bombs left
        if (!found) {
            gameplayScreen.setGameOn(found);
            gameplayScreen.setBombsFound(found);
        }
    }

    public void clearNeighbours(Location loc) {

        //Find the neighbours
        ArrayList<Location> neighbours = new ArrayList<>();
        neighbours.add(new Location(loc.getRow()-1, loc.getCol()));
        neighbours.add(new Location(loc.getRow(),   loc.getCol()-1));
        neighbours.add(new Location(loc.getRow(),   loc.getCol()+1));
        neighbours.add(new Location(loc.getRow()+1, loc.getCol()));

        neighbours.add(new Location(loc.getRow()+1, loc.getCol()+1));
        neighbours.add(new Location(loc.getRow()+1, loc.getCol()-1));
        neighbours.add(new Location(loc.getRow()-1, loc.getCol()-1));
        neighbours.add(new Location(loc.getRow()-1, loc.getCol()+1));

        //uncover the trigger tile
        board[loc.getCol()][loc.getRow()] += 10;

        for (int i = 0; i<neighbours.size(); i++) {
            Location current = neighbours.get(i);
            if (current.getRow() < 0 || current.getRow() > board[0].length-1 ||current.getCol() < 0 || current.getCol() > board.length-1) {
                current = null;
            }
            
            if (current != null && board[current.getCol()][current.getRow()] > 0 && board[current.getCol()][current.getRow()] < 9) {
                board[current.getCol()][current.getRow()] += 10;
            }
            //if location exists and an uncovered blank tile
            if (i<4 && current!=null && board[current.getCol()][current.getRow()] == 0) {
                clearNeighbours(current);
            }
        }

    }

    public void handleRightClick(int x, int y) {
        Location loc = getTileAt(x, y);
        if (loc != null) {
            if (board[loc.getCol()][loc.getRow()] < 9) {
                board[loc.getCol()][loc.getRow()] += 20;
                numFlags++;
            }
            else if (board[loc.getCol()][loc.getRow()] >= 19){
                board[loc.getCol()][loc.getRow()] -= 20;
                numFlags--;
            }
            System.out.println(board[loc.getCol()][loc.getRow()]);
        }
    }

    public int getNumFlagsPlaced() {
        return numFlags;
    }

    public int getTotalBombs() {
        return numBombs;
    }

    //loop thru entire board and count and place the correct number in each non bomb space
    public void initBoardNumbers() {
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[1].length; j++) {
                if (board[i][j] != -1 && surroundingBombs(i, j) != 0) {
                    board[i][j] = surroundingBombs(i,j);
                    System.out.print(board[i][j]);
                }
                else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

    private int surroundingBombs(int i,int j) {
        int result = 0;
        if (i>0 && board[i-1][j] == BOMB) {
            result++;
        }
        if (i<board.length-1 && board[i+1][j] == BOMB) {
            result++;
        }
        if (j>0 && board[i][j-1] == BOMB) {
            result++;
        }
        if (j<board[0].length-1 && board[i][j+1]==BOMB) {
            result++;
        }
        if (i>0 && j > 0 && board[i-1][j-1]==BOMB) {
            result++;
        }  
        if (i>0 && j < board[0].length-1 && board[i-1][j+1]==BOMB) {
            result++;
        }
        if (i<board.length-1 && j > 0 && board[i+1][j-1]==BOMB) {
            result++;
        }
        if (i<board.length-1 && j < board[0].length-1 && board[i+1][j+1]==BOMB) {
            result++;
        }
        return result;
    }

    public void draw(SpriteBatch spriteBatch) {
        int xOffset = 100;
        int yOffset = 600;
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j<board[1].length; j++) {
                if (board[i][j] == 9){// || board[i][j] == -1) {
                    spriteBatch.draw(bombTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 10) {
                    spriteBatch.draw(emptyFloorTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 11){// || board[i][j] == 1) {
                    spriteBatch.draw(oneTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 12){// || board[i][j] == 2) {
                    spriteBatch.draw(twoTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 13){// || board[i][j] == 3) {
                    spriteBatch.draw(threeTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 14){// || board[i][j] == 4) {
                    spriteBatch.draw(fourTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 15){// || board[i][j] == 5) {
                    spriteBatch.draw(fiveTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 16){// || board[i][j] == 6) {
                    spriteBatch.draw(sixTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 17){// || board[i][j] == 7) {
                    spriteBatch.draw(sevenTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 18){// || board[i][j] == 8) {
                    spriteBatch.draw(eightTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] >= 19 && board[i][j] <= 28) {
                    spriteBatch.draw(flagTile, j*25+xOffset, yOffset-i*25);
                }
                else {
                    spriteBatch.draw(emptyTile, j*25+xOffset, yOffset-i*25);
                }
            }
        }
    }
}