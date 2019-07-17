package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;

    private TodoDbHelper todoDbHelper = null;
    private SQLiteDatabase sqLiteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim(), getSelectedPriority());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        todoDbHelper = new TodoDbHelper(this);
        sqLiteDatabase = todoDbHelper.getWritableDatabase();

        ((RadioButton)findViewById(R.id.rb_low)).setChecked(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean saveNote2Database(String content, Priority priority) {
        // TODO 插入一条新数据，返回是否插入成功
        if (sqLiteDatabase != null && content != ""){
            ContentValues values = new ContentValues();
            values.put(TodoContract.ToDoEntry.COLUMN_CONTENT, content);
            values.put(TodoContract.ToDoEntry.COLUMN_DATE, System.currentTimeMillis());
            values.put(TodoContract.ToDoEntry.COLUMN_STATE, State.TODO.intValue);
            values.put(TodoContract.ToDoEntry.COLUMN_PRIORITY, priority.value);
            long rowId = sqLiteDatabase.insert(TodoContract.ToDoEntry.TABLE_NAME, null, values);
            if(rowId != -1) return true;
        }
        return false;
    }

    private Priority getSelectedPriority() {
        RadioGroup radioGroup = findViewById(R.id.rg_priority);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_high:
                return Priority.High;
            case R.id.rb_medium:
                return Priority.Medium;
            default:
                return Priority.Low;
        }
    }
}
