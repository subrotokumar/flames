package com.subrotokumar.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showresult(View view)
    {
        String flames[]={"SIBLING","FRIEND","LOVER","AFFECTIONATE","MARRIAGE","ENEMY"};
        ArrayList<String> a=new ArrayList<>();
        for(String st:flames)
            a.add(st);
        String s1=((EditText)findViewById(R.id.nameinput1)).getText().toString().toUpperCase();
        String s2=((EditText)findViewById(R.id.nameinput2)).getText().toString().toUpperCase();
        if(s1.length()>0&&s2.length()>0) {
            int ch1[] = new int[24];
            int ch2[] = new int[24];
            for (int i = 0; i < s1.length(); i++) {
                char ch=s1.charAt(i);
                if (ch>=65&&ch<=90)
                    ch1[(int) (ch - 'A')]++;
            }
            for (int i = 0; i < s2.length(); i++) {
                char ch=s2.charAt(i);
                if (ch>=65&&ch<=90)
                    ch2[(int)(ch - 'A')]++;
            }
            int sum = 0;
            for (int i = 0; i < 24; i++)
                sum += (Math.abs(ch1[i] - ch2[i]));
            if(sum==0)
                ((TextView) findViewById(R.id.result)).setText("COMPLICATED");
            else {
                int pos = 0;
                while (a.size() > 1) {
                    pos = (pos + sum) % a.size();
                    a.remove(pos);
                    pos--;
                }
                ((TextView) findViewById(R.id.result)).setText(a.get(0));
            }
        }
        else {
            Toast.makeText(this, "Please enter the name!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clear(View view) {
        ((EditText)findViewById(R.id.nameinput1)).setText("");
        ((EditText)findViewById(R.id.nameinput2)).setText("");
        ((TextView)findViewById(R.id.result)).setText("");
    }
}