package com.example.sweeper;



public class Generator {
    static Cell [][]grid;
    static String s="";

    public static void generate(int width,int height,int bombCount) {
        try {
            s="";
            grid = Grid.getGrid(width, height, bombCount);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    s += String.valueOf(grid[i][j].getContent());
                    s += "||";
                }
                s += "\n";
            }
        } catch (Exception e){
            s="fail";
        }
    }
    public static void destroy(){
        Grid.grid=null;
    }
}
