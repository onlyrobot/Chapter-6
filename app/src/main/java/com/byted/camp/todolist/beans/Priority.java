package com.byted.camp.todolist.beans;

import android.app.Notification;
import android.graphics.Color;
import android.preference.PreferenceActivity;

public enum Priority {
    Low(0, Color.WHITE), Medium(1, Color.GREEN), High(2, Color.RED);

    public final int value;
    public final int color;
    Priority(int value, int color) {
        this.value = value;
        this.color = color;
    }
    public static Priority getPriority(int value){
        switch (value){
            case 0: return Low;
            case 1: return Medium;
            case 2: return High;
            default: return Low;
        }
    }
}
