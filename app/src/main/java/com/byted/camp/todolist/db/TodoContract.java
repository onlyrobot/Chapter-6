package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量

    private TodoContract() {
    }
    public static class ToDoEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_DATE = "data";
        public static final String COLUMN_STATE = "state";
    }
    public static final String SQL_CREATE_ENTITIES =
            "CREATE TABLE " + ToDoEntry.TABLE_NAME + "(" +
                    ToDoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ToDoEntry.COLUMN_CONTENT + " TEXT," +
                    ToDoEntry.COLUMN_PRIORITY + " INTEGER," +
                    ToDoEntry.COLUMN_DATE + " INTEGER," +
                    ToDoEntry.COLUMN_STATE + " INTEGER)";

    public static final String ADD_PRIORITY_COLUMN =
            "ALTER TABLE " + ToDoEntry.TABLE_NAME + " ADD " + ToDoEntry.COLUMN_PRIORITY + " INTEGER";

}
