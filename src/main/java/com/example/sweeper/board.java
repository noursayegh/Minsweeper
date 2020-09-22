package com.example.sweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Toast;

import androidx.core.content.ContextCompat;


public class board extends View {
        private boolean flag=false;
        public boolean end=false;
        private static int numColumns, numRows,bombCount;
        private int cellWidth, cellHeight;
        private static Cell[][] grid;
        private Context context;

    public boolean isFlagging() {
        return flag;
    }

    public void toggleFlag() {
        this.flag =!this.flag;
    }



        public board(Context context) {
            this(context, null);
        }

        public board(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        public int getBombCount() {
            return bombCount;
        }

        public void setBombCount(int bombCount) {
         this.bombCount = bombCount;
        }

         public void setNumColumns(int numColumns) {
            this.numColumns = numColumns;
            calculateDimensions();
         }

        public int getNumColumns() {
            return numColumns;
        }

        public void setNumRows(int numRows) {
            this.numRows = numRows;
            calculateDimensions();
        }

        public int getNumRows() {
            return numRows;
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            calculateDimensions();
        }

        private void calculateDimensions() {
            if (numColumns < 1 || numRows < 1) {
                return;
            }

            cellWidth = (getWidth() / numColumns);
            cellHeight = (getHeight() / numRows);
            cellHeight=cellWidth;
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            grid = Grid.getGrid(numRows, numColumns, bombCount);
            canvas.drawColor(Color.WHITE);

            if (numColumns == 0 || numRows == 0) {
                return;
            }


            for (int i = 0; i < numColumns; i++) {
                for (int j = 0; j < numRows; j++) {
                        Drawable drawable;
                        drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
                    if (end||grid[i][j].isVisible()) {
                        switch (grid[i][j].getContent()){
                            case -1:
                                if(grid[i][j].isClicked())
                                    drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
                                else
                                    drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
                                break;
                            case 0:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                                break;
                            case 1:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
                                break;
                            case 2:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
                                break;
                            case 3:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                                break;
                            case 4:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
                                break;
                            case 5:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
                                break;
                            case 6:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
                                break;
                            case 7:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
                                break;
                            case 8:
                                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
                                break;

                        }
                    }
                    if(grid[i][j].isFlagged())
                        drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
                    drawable.setBounds(i*cellWidth,j*cellHeight,i*cellWidth+cellWidth,j*cellHeight+cellHeight);
                    drawable.draw(canvas);

                }
            }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int column = (int) (event.getX() / cellWidth);
                    int row = (int) (event.getY() / cellHeight);
                    if ((column > -1 && row > -1 && column < numColumns && row < numRows)) {
                        if (!isFlagging()) {
                            if(!Grid.reveal(numColumns, numRows, column, row)){
                                invalidate();
                                gameLost();
                                return true;
                            }
                            grid = Grid.getGrid();
                            invalidate();
                            checkResult();
                            invalidate();
                        }
                        else {
                            Grid.setFlag(column,row);
                            invalidate();

                        }
                    }

            }

            return true;
        }
        public  void checkResult(){
            for(int j=0;j<numColumns;j++)
                for (int k=0;k<numRows;k++)
                    if(!(grid[j][k].isVisible()||grid[j][k].isFlagged()) && grid[j][k].getContent()!=-1){
                        return;
                    }
                gameWon();

        }
        public void gameLost(){
            Toast.makeText(context,"Game Lost",Toast.LENGTH_LONG).show();
            Timer.endGameTimer();
            end=true;
            invalidate();

        }
        public void gameWon(){
            Toast.makeText(context,"Game Won",Toast.LENGTH_LONG).show();
            Timer.endGameTimer();
            end=true;
            invalidate();

        }
        public void setContext(Context context){
            this.context=context;
        }
    }

