package com.example.sweeper;

import java.util.Random;

public class Grid {

    static Cell [][]grid;

    static void createGrid(int width,int height){

        Cell [][]grid1=new Cell[height][width];
        for(int i=0;i<height;i++)
            for (int j=0;j<width;j++)
                grid1[i][j] = new Cell(0);

        grid=grid1;

    }

    public static Cell[][] getGrid(int width,int height,int bombCount) {
        if(grid==null) {
            createGrid(width, height);
            while (bombCount > 0) {
                int x = random_generator(height);
                int y = random_generator(width);
                if (grid[x][y].getContent() != -1) {
                    grid[x][y].setContent(-1);
                    bombCount--;
                }
            }

            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    fill(grid, i, j, width, height);

        }
            return grid;

    }
    public static Cell[][] getGrid() {
        return grid;

    }

    private Grid(){}

    private static int  random_generator(int n){
        Random r=new Random();
        return r.nextInt(n);
    }
    private static void fill(Cell [][]grid,int i,int j,int rows,int cols){
        int count=0;
        if(grid[i][j].isBomb()) return;
        if(i-1>-1 && j-1>-1 && grid[i-1][j-1].isBomb())count++;
        if(i-1>-1 && grid[i-1][j].isBomb())count++;
        if(i-1>-1 && j+1<rows && grid[i-1][j+1].isBomb())count++;
        if(j-1>-1 && grid[i][j-1].isBomb())count++;
        if(j+1<rows && grid[i][j+1].isBomb())count++;
        if(j-1>-1 && i+1<cols && grid[i+1][j-1].isBomb())count++;
        if(i+1<cols && grid[i+1][j].isBomb())count++;
        if(i+1<cols && j+1<rows && grid[i+1][j+1].isBomb())count++;
        grid[i][j].setContent(count);
    }

    public static boolean reveal(int cols,int rows,int i,int j){
        if(!grid[i][j].isVisible()&&!grid[i][j].isFlagged()){
            grid[i][j].setVisible();
            grid[i][j].setClicked();
            if(grid[i][j].getContent()==0){
               if(i+1<cols)
                    reveal(cols,rows,i+1,j);
               if(i-1>-1)
                    reveal(cols,rows,i-1,j);
               if(j+1<rows)
                    reveal(cols,rows,i,j+1);
               if(j-1>-1)
                    reveal(cols,rows,i,j-1);
               if(i+1<cols&&j+1<rows)
                    reveal(cols,rows,i+1,j+1);
                if(i-1>-1&&j-1>-1)
                    reveal(cols,rows,i-1,j-1);
                if(i+1<cols&&j-1>-1)
                    reveal(cols,rows,i+1,j-1);
                if(i-1>-1&&j+1<rows)
                    reveal(cols,rows,i-1,j+1);
            }
            if (grid[i][j].getContent()==-1) {
                grid[i][j].setVisible();
                grid[i][j].setClicked();
                return false;
            }

        }
        return true;
    }
    public static void setFlag(int i,int j){
        if(!grid[i][j].isVisible())
            grid[i][j].setFlag();
    }
}








