package com.subrotokumar.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.subrotokumar.flames.databinding.ActivityHomeBinding;
import com.subrotokumar.flames.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private final String[] flames = {"SIBLING", "FRIEND", "LOVER", "AFFECTIONATE", "MARRIAGE", "ENEMY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSubmit.setOnClickListener(v -> {
            try {
                ArrayList<String> a = new ArrayList<>(Arrays.asList(flames));
                String s1 = binding.nameinput1.getText().toString().trim().toUpperCase();
                String s2 = binding.nameinput2.getText().toString().trim().toUpperCase();
                binding.nameinput1.setText(s1);
                binding.nameinput2.setText(s2);
                if (s1.length() > 0 && s2.length() > 0) {
                    int[] ch1 = new int[26];
                    int[] ch2 = new int[26];
                    for (int i = 0; i < s1.length(); i++) {
                        char ch = s1.charAt(i);
                        if (ch >= 'A' && ch <= 'Z')
                            ch1[(int) ch - 65]++;
                    }
                    for (int i = 0; i < s2.length(); i++) {
                        char ch = s2.charAt(i);
                        if (ch >= 'A' && ch <= 'Z')
                            ch2[(int) ch - 65]++;
                    }
                    int sum = 0;
                    for (int i = 0; i < 26; i++)
                        sum += Math.abs(ch1[i] - ch2[i]);
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
                    Toast.makeText(HomeActivity.this, "Please enter the name!!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("App Crash", String.valueOf(e));
                e.printStackTrace();
            }
        });

        binding.btnClear.setOnClickListener(v -> {
                    binding.nameinput1.setText("");
                    binding.nameinput2.setText("");
                    binding.result.setText("");
                }
        );
    }
}