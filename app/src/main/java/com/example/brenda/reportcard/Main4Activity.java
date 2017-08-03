package com.example.brenda.reportcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {

    EditText Name, Surname, Age,Grade,Mathematics,myScience,myEnglish;
    DbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Name= (EditText) findViewById(R.id.Old_StudentName);
        Surname= (EditText) findViewById(R.id.New_StudentName);
        Age = (EditText)findViewById(R.id.mathEdit) ;
        Grade = (EditText)findViewById(R.id.BusinesEdit) ;
        Mathematics = (EditText)findViewById(R.id.AccoutningEdit) ;
        myScience = (EditText)findViewById(R.id.ScienceEdit) ;
        myEnglish = (EditText)findViewById(R.id.EnglishEdit) ;

        helper = new DbAdapter(this);
    }


    public void addUser(View view)
    {
        String t1 = Name.getText().toString();
        String t2 = Surname.getText().toString();
        String t3 = Age.getText().toString();
        String t4 = Grade.getText().toString();
        String t5 = Mathematics.getText().toString();
        String t6 = myScience.getText().toString();
        String t7 = myEnglish.getText().toString();

        if(t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t4.isEmpty() || t5.isEmpty() || t6.isEmpty() || t7.isEmpty())
        {
            Message.message(getApplicationContext(),"Fill all the field above!");
        }
        else
        {
            long id = helper.insertData(t1,t2,t3,t4,t5,t6,t7);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Record Added Unsuccessful");
                Name.setText("");
                Surname.setText("");
                Age.setText("");
                Grade.setText("");
                Mathematics.setText("");
                myScience.setText("");
                myEnglish.setText("");

            } else
            {
                Message.message(getApplicationContext(),"Record Added Successful");
                Name.setText("");
                Surname.setText("");
                Age.setText("");
                Grade.setText("");
                Mathematics.setText("");
                myScience.setText("");
                myEnglish.setText("");
            }
        }
        viewdata();

    }

    public void viewdata()
    {

        Intent y = new Intent(this,MainActivity.class);
        startActivity(y);



    }



}
