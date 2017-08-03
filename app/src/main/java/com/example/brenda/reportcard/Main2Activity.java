package com.example.brenda.reportcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    DbAdapter helper;
    EditText updateOldName,updateNewName,updateOldSurname,updateNewSurname,updateOldAge,updatNewAge,updateOldGrade,updateNewGrade,OldMaths,NewMaths,OldSci,NewScie,OldEng,NewEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Database Object
        helper = new DbAdapter(this);

        updateOldName= (EditText) findViewById(R.id.Old_studentNumber);
        updateNewName= (EditText) findViewById(R.id.New_StudentNumber);

        updateOldSurname= (EditText) findViewById(R.id.Old_StudentName);
        updateNewSurname= (EditText) findViewById(R.id.New_StudentName);

        updateOldAge = (EditText) findViewById(R.id.OldMath);
        updatNewAge = (EditText) findViewById(R.id.NewMath);

        updateOldGrade = (EditText) findViewById(R.id.oldBusinessMarks);
        updateNewGrade = (EditText) findViewById(R.id.NewBusinessMarks);

        OldMaths = (EditText) findViewById(R.id.oldAccountingMarks);
        NewMaths = (EditText) findViewById(R.id.NewAccountingMarks);

        OldSci = (EditText) findViewById(R.id.oldScienceMarks);
        NewScie = (EditText) findViewById(R.id.NewScienceMarks);

        OldEng = (EditText) findViewById(R.id.oldEnglishMarks);
        NewEng = (EditText) findViewById(R.id.NewEnglishMarks);


        ArrayList<String> array;
        array = helper.getData();




    }

    public void update( View view)
    {

        String u1 = updateOldName.getText().toString();
        String u2 = updateNewName.getText().toString();



        String u3 = updateOldSurname.getText().toString();
        String u4 = updateNewSurname.getText().toString();

        String u5 = updateOldAge.getText().toString();
        String u6 = updatNewAge.getText().toString();

        String u7 = updateOldGrade.getText().toString();
        String u8 = updateNewGrade.getText().toString();

        String u9 = OldMaths.getText().toString();
        String u10 = NewMaths.getText().toString();

        String u11 = OldSci.getText().toString();
        String u12 = NewScie.getText().toString();

        String u13 = OldEng.getText().toString();
        String u14 = NewEng.getText().toString();

        System.out.println("U1: " +u1 + " U2: " + u2 + "U3: " +u3 + "U4: " + u4+ "U5: " + u5+ "U6: " + u6+ "U7: " + u7+ "U8: " + u8+ "U9: " + u9+ "U10: " + u10+ "U11: " + u11+ "U12: " + u12+ "U13: " + u13+ "U14: " + u14);

        if(u1.isEmpty() || u2.isEmpty() )
        {
            Message.message(getApplicationContext(),"Enter Data");
        }
        else {
            int a = helper.updateName(u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11,u12,u13,u14);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");

                updateOldName.setText("");
                updateNewName.setText("");


                updateOldSurname.setText("");
                updateNewSurname.setText("");


                updateOldAge.setText("");
                updatNewAge.setText("");

                updateOldGrade.setText("");
                updateNewGrade.setText("");

                OldMaths.setText("");
                NewMaths.setText("");

                OldSci.setText("");
                NewScie.setText("");

                OldEng.setText("");
                NewEng.setText("");

            } else {
                Message.message(getApplicationContext(), "Updated");

                updateOldName.setText("");
                updateNewName.setText("");


                updateOldSurname.setText("");
                updateNewSurname.setText("");


                updateOldAge.setText("");
                updatNewAge.setText("");

                updateOldGrade.setText("");
                updateNewGrade.setText("");

                OldMaths.setText("");
                NewMaths.setText("");

                OldSci.setText("");
                NewScie.setText("");

                OldEng.setText("");
                NewEng.setText("");
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
