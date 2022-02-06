package com.subrotokumar.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.subrotokumar.flames.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<String> a=new ArrayList<>();
    private String flames[]={"SIBLING","FRIEND","LOVER","AFFECTIONATE","MARRIAGE","ENEMY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void showresult(View view)
    {
        a=new ArrayList<>();
        for(String st:flames)
            a.add(st);
        String s1=binding.nameinput1.getText().toString().toUpperCase();
        String s2=binding.nameinput2.getText().toString().toUpperCase();
        if(s1.length()>0&&s2.length()>0) {
            int ch1[] = new int[24];
            int ch2[] = new int[24];
            for (int i = 0; i < s1.length(); i++) {
                char ch=s1.charAt(i);
                if (Character.isAlphabetic(ch))
                    ch1[(int)ch-65]++;
            }
            for (int i = 0; i < s2.length(); i++) {
                char ch=s2.charAt(i);
                if (Character.isAlphabetic(ch))
                    ch2[(int)ch-65]++;
            }
            int sum = 0;
            for (int i = 0; i < 24; i++)
                sum += (Math.abs(ch1[i] - ch2[i]));
            if(sum==0)
                binding.result.setText("COMPLICATED");
            else {
                int pos = 0;
                while (a.size() > 1) {
                    pos = (pos + sum) % a.size();
                    a.remove(pos);
                    pos--;
                }
                binding.result.setText(a.get(0));
                a.clear();
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