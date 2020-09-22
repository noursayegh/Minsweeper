package com.example.sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button easy,medium,hard;
    Switch toggle;
    FirebaseDatabase database;
    DatabaseReference myRef;
    User user1=new User();
    LinearLayout boardLayout;
    board myBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easy=findViewById(R.id.easy);
        medium=findViewById(R.id.medium);
        hard=findViewById(R.id.hard);
        boardLayout=findViewById(R.id.view);
        boardLayout.setPadding(50,50,50,50);
        toggle=findViewById(R.id.toggle);
        ViewGroup.LayoutParams params=boardLayout.getLayoutParams();
        params.height=getScreenWidth()-100;
        params.width=getScreenWidth();

        Timer.setTimerView(easy);





 /*
        try {
            ArrayList<User> users=new ArrayList<>();
            User user2=new User();
            user2.setTime("400");
            user2.setName("user2");

            database = FirebaseDatabase.getInstance();
            myRef = database.getReference().child("Easy").child("testttttt66");
            user1.setName("user6");
            user1.setTime("1:000");
            users.add(user2);
            users.add(user1);
           myRef.child("testttttt").setValue(users);
        }catch (Exception e){
            Toast.makeText(this,"fail",Toast.LENGTH_LONG).show();
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue().toString();
                easy.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                easy.setText("fail");
            }
        });
*/
        }


        public void test(View view){
            int bc=0,cols=0,rows=0;
            switch (view.getId()){
                case R.id.easy:
                    bc=10;
                    cols=rows=8;
                    break;
                case R.id.medium:
                    bc=40;
                    cols=rows=16;
                    break;
                case R.id.hard:
                    bc=100;
                    cols=16;
                    rows=30;

            }
            Timer.createTimer();
            Timer.startTimer();
        if (Grid.grid==null) {
            myBoard = new board(MainActivity.this);
            myBoard.setBombCount(bc);
            myBoard.setNumColumns(cols);
            myBoard.setNumRows(rows);
            myBoard.setContext(this);
            boardLayout.addView(myBoard);
            view.setVisibility(View.GONE);
            easy.setText("NewGame");
            medium.setText("NewGame");
            hard.setText("NewGame");
            boardLayout.setOrientation(LinearLayout.VERTICAL);

        }
        else{
            Grid.grid=null;
            myBoard.end=false;
            myBoard.invalidate();
        }
        }
        public void test2(View view){
            myBoard.toggleFlag();
        }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    }

