package com.subrotokumar.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.subrotokumar.flames.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String flames[]={"SIBBLING","FRIEND","LOVER","AFFECTIONATE","MARRIAGE","ENEMY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<String> a = new ArrayList<>();
                    for (String st : flames)
                        a.add(st);
                    String s1 = binding.nameinput1.getText().toString().toUpperCase();
                    String s2 = binding.nameinput2.getText().toString().toUpperCase();

                    if (s1.length() > 0 && s2.length() > 0) {
                        int ch1[] = new int[26];
                        int ch2[] = new int[26];
                        for (int i = 0; i < s1.length(); i++) {
                            char ch = s1.charAt(i);
                            if (ch >= 'A' && ch <= 'Z')
                                ch1[(int)ch - 65]++;
                        }
                        for (int i = 0; i < s2.length(); i++) {
                            char ch = s2.charAt(i);
                            if (ch >= 'A' && ch <= 'Z')
                                ch2[(int)ch - 65]++;
                        }
                        int sum = 0;
                        for (int i = 0; i < 26; i++)
                            sum += Math.abs(ch1[i]-ch2[i]);
                        if (sum == 0)
                            binding.result.setText("COMPLICATED");
                        else {
                            int pos = 0;
                            while (a.size() > 1) {
                                pos = (pos + sum) % a.size();
                                a.remove(pos);
                                pos--;
                            }
                            binding.result.setText(a.get(0));
                            Log.d("Name Display", "\n" + s1 + "\n" + s2 + " " + a.get(0));
                            a.clear();
                        }
                    } else
                        Toast.makeText(MainActivity.this, "Please enter the name!!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("App Crash", String.valueOf(e));
                    e.printStackTrace();
                }
            }
        });
    }

    public void clear(View view) {
        ((EditText)findViewById(R.id.nameinput1)).setText("");
        ((EditText)findViewById(R.id.nameinput2)).setText("");
        ((TextView)findViewById(R.id.result)).setText("");
    }
}