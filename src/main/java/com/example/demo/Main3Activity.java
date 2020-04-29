package com.example.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText name1,email,pass1,mobile,pass2;
    Button clk,update,viewone;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        name1=(EditText) findViewById(R.id.name1);
        pass1=(EditText) findViewById(R.id.pass1);
        pass2=(EditText) findViewById(R.id.pass2);
        email=(EditText) findViewById(R.id.email);
        mobile=(EditText) findViewById(R.id.mobile);
        clk=(Button) findViewById(R.id.reg);
        viewone = (Button) findViewById(R.id.viewone);
        update=(Button) findViewById(R.id.update);
        myDb = new DatabaseHelper(this);
        clickListerns();
        updateData();
        ViewAll();
        clk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namevalue=name1.getText().toString();
                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                intent.putExtra("Name" ,namevalue);
                startActivity(intent);
            }
        });

    }
    public void ViewAll(){
        viewone.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount()==0){
                            //show message
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("UserName :" + res.getString(0)+"\n");
                            buffer.append("Password :" + res.getString(1)+"\n");
                            buffer.append("email :" + res.getString(2)+"\n");
                            buffer.append("mobile :" + res.getString(3)+"\n");


                        }
                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void updateData(){
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updatedata(name1 . getText() . toString(),
                                pass1 . getText() . toString(),
                                email . getText() . toString(), mobile.getText().toString());
                        if(isUpdate==true)
                            Toast.makeText(Main3Activity.this,"Data Updated",Toast.LENGTH_SHORT).show();


                        else {
                            Toast.makeText(Main3Activity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                        }


                    }

                }
        );
    }
    public void clickListerns() {

        clk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String stname = name1.getText().toString();
                        String stpass = pass1.getText().toString();
                        String stpass1 = pass2.getText().toString();
                        String stmail = email.getText().toString();
                        String stmobile = mobile.getText().toString();

                        boolean isInserted = myDb.insertData(name1 . getText() . toString(),
                                pass1 . getText() . toString(),email.getText().toString(),mobile.getText().toString()) ;

                        if(isInserted==false)
                            Toast.makeText(Main3Activity.this,"Please provide Name and PassWord",Toast.LENGTH_SHORT).show();


                        else if(stpass1==stpass) {
                            Intent in = new Intent(Main3Activity.this, Main2Activity.class);
                            startActivity(in);
                        }
                        else
                            Toast.makeText(Main3Activity.this,"Password Mismatch",Toast.LENGTH_SHORT).show();


                    }
                }
        );

    }




}
