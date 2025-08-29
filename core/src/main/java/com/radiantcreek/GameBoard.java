package com.radiantcreek;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameBoard {
    private int[][] board; //your data structure
    private int numBombs; //the number of bombs in the grid
    private int numFlags; //the number of flags that STILL have to be placed by the player
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
    

    public GameBoard(GameplayScreen gameplayScreen) {
        this.gameplayScreen = gameplayScreen;
        board = new int[16][30];
        numBombs = 50;
        numFlags = numBombs;
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
            board[x][y] = -1;
            i++;
        }
    }
    
    //loop thru entire board and count and place the correct number in each non bomb space
    public void initBoardNumbers() {
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[1].length; j++) {
                if (board[i][j] != -1 && surroundingBombs(i, j) != 0) {
                    board[i][j] = surroundingBombs(i,j);
                }
            }
        }
    }

    private int surroundingBombs(int i,int j) {
        int result = 0;
        if (i>0 && j> 0 && i<board.length-1 && j<board[0].length-1) {
            if (board[i-1][j-1] == -1) {
                result++;
            }
            if (board[i-1][j+1] == -1) {
                result++;
            }
            if (board[i-1][j] == -1) {
                result++;
            }
            if (board[i+1][j-1] == -1) {
                result++;
            }
            if (board[i+1][j+1] == -1) {
                result++;
            }
            if (board[i+1][j] == -1) {
                result++;
            }
            if (board[i][j-1] == -1) {
                result++;
            }
            if (board[i][j+1] == -1) {
                result++;
            }
        }
        else if (i==0 && j>0 && j<board[0].length-1) {
            if (board[i+1][j-1] == -1) {
                result++;
            }
            if (board[i+1][j+1] == -1) {
                result++;
            }
            if (board[i+1][j] == -1) {
                result++;
            }
            if (board[i][j-1] == -1) {
                result++;
            }
            if (board[i][j+1] == -1) {
                result++;
            }
        }
        else if (i==board.length-1 && j>0 && j<board[0].length-1) {
            if (board[i-1][j-1] == -1) {
                result++;
            }
            if (board[i-1][j+1] == -1) {
                result++;
            }
            if (board[i-1][j] == -1) {
                result++;
            }
            if (board[i][j-1] == -1) {
                result++;
            }
            if (board[i][j+1] == -1) {
                result++;
            }
        }
        else if (j==0 && i>0 && i<board.length-1) {
            if (board[i-1][j+1] == -1) {
                result++;
            }
            if (board[i-1][j] == -1) {
                result++;
            }
            if (board[i+1][j+1] == -1) {
                result++;
            }
            if (board[i+1][j] == -1) {
                result++;
            }
            if (board[i][j+1] == -1) {
                result++;
            }
        }
        else if (j==board[0].length-1 && i>0 && i<board.length-1) {
            if (board[i-1][j-1] == -1) {
                result++;
            }

            if (board[i-1][j] == -1) {
                result++;
            }
            if (board[i+1][j-1] == -1) {
                result++;
            }
            
            if (board[i+1][j] == -1) {
                result++;
            }
            if (board[i][j-1] == -1) {
                result++;
            }
            
        }
        else if (i == 0 && j == 0) {
            if (board[i+1][j+1] == -1) {
                result++;
            }
            if (board[i+1][j] == -1) {
                result++;
            }
            if (board[i][j+1] == -1) {
                result++;
            }
        }
        else if (i == board.length-1 && j == 0) {
            if (board[i][j+1] == -1) {
                result++;
            }
            if (board[i-1][j+1] == -1) {
                result++;
            }
            if (board[i-1][j] == -1) {
                result++;
            }
        }
        else if (i == 0 && j == board[0].length-1) {
            if (board[i+1][j] == -1) {
                result++;
            }
            if (board[i+1][j-1] == -1) {
                result++;
            }
            if (board[i][j-1] == -1) {
                result++;
            }
        }
        else if (i == board.length-1 && j == board[0].length-1) {
            if (board[i][j-1] == -1) {
                result++;
            }
            if (board[i-1][j] == -1) {
                result++;
            }
            if (board[i-1][j-1] == -1) {
                result++;
            }
        }
        return result;
    }

    public void draw(SpriteBatch spriteBatch) {
        int xOffset = 100;
        int yOffset = 600;
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j<board[1].length; j++) {
                if (board[i][j] == 9) {
                    spriteBatch.draw(bombTile, j*25+xOffset, yOffset-i*25);
                }
                //temp
                if (board[i][j] == -1) {
                    spriteBatch.draw(bombTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 10) {
                    spriteBatch.draw(emptyFloorTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 11 || board[i][j] == 1) {
                    spriteBatch.draw(oneTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 12 || board[i][j] == 2) {
                    spriteBatch.draw(twoTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 13 || board[i][j] == 3) {
                    spriteBatch.draw(threeTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 14 || board[i][j] == 4) {
                    spriteBatch.draw(fourTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 15 || board[i][j] == 5) {
                    spriteBatch.draw(fiveTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 16 || board[i][j] == 6) {
                    spriteBatch.draw(sixTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 17 || board[i][j] == 7) {
                    spriteBatch.draw(sevenTile, j*25+xOffset, yOffset-i*25);
                }
                else if (board[i][j] == 18 || board[i][j] == 8) {
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
