package com.example.sweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class Cell {
    private int content;
    private boolean visible=false,isFlagged=false,clicked;
    Cell(int content){
        this.content=content;

    }
    boolean isClicked(){return clicked;}
    void setClicked(){clicked=true;}
    boolean isBomb(){
        return content==-1;
    }
    boolean isFlagged(){
        return isFlagged;
    }
    boolean isVisible(){
        return visible;
    }
    void setVisible(){this.visible=true;}
    void setContent(int content){
        this.content=content;
    }
    int getContent(){
        return this.content;
    }
    void setFlag(){
        if(!visible)
            isFlagged=!isFlagged;
    }



}

