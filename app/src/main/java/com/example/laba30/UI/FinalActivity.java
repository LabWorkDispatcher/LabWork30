package com.example.laba30.UI;

import static com.example.laba30.data.Constants.APP_KEY_GROUP;
import static com.example.laba30.data.Constants.APP_KEY_NAME;
import static com.example.laba30.data.Constants.APP_KEY_SURNAME;
import static com.example.laba30.data.Constants.APP_KEY_WORKS_ACCEPTED_AMOUNT;
import static com.example.laba30.data.Constants.APP_KEY_WORKS_ACCEPTED_DATES;
import static com.example.laba30.data.Constants.APP_KEY_WORKS_COMPLETED_AMOUNT;
import static com.example.laba30.data.Constants.APP_KEY_WORKS_COMPLETED_DATES;
import static com.example.laba30.utils.Utils.moveToActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.laba30.adapters.MyRecyclerViewAdapter;
import com.example.laba30.adapters.RecyclerViewItem;
import com.example.laba30.data.Date;
import com.example.laba30.databinding.ActivityFinalBinding;

import java.util.ArrayList;

@SuppressLint("SetTextI18n")
public class FinalActivity extends AppCompatActivity {
    private ActivityFinalBinding binding;
    private Bundle prevActivityBundle;
    private ArrayList<RecyclerViewItem> recyclerViewList;
    private ArrayList<Date> acceptedWorks, completedWorks;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prevActivityBundle = getIntent().getExtras();
        binding.textView1.setText(prevActivityBundle.getString(APP_KEY_NAME) + " " + prevActivityBundle.getString(APP_KEY_SURNAME) + "\n" + prevActivityBundle.getString(APP_KEY_GROUP));
        acceptedWorks = prevActivityBundle.getParcelableArrayList(APP_KEY_WORKS_ACCEPTED_DATES);
        completedWorks = prevActivityBundle.getParcelableArrayList(APP_KEY_WORKS_COMPLETED_DATES);

        binding.leaveButton.setOnClickListener(view -> {
            this.finish();
            System.exit(0);
        });

        binding.goBackButton.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra(APP_KEY_NAME, prevActivityBundle.getString(APP_KEY_NAME));
            i.putExtra(APP_KEY_SURNAME, prevActivityBundle.getString(APP_KEY_SURNAME));
            i.putExtra(APP_KEY_GROUP, prevActivityBundle.getString(APP_KEY_GROUP));
            i.putExtra(APP_KEY_WORKS_COMPLETED_AMOUNT, prevActivityBundle.getInt(APP_KEY_WORKS_COMPLETED_AMOUNT));
            i.putExtra(APP_KEY_WORKS_ACCEPTED_AMOUNT, prevActivityBundle.getInt(APP_KEY_WORKS_ACCEPTED_AMOUNT));
            moveToActivity(this, i);
        });


        recyclerViewList = new ArrayList<>();
        for (int i = 0; i < acceptedWorks.size(); i++) {
            recyclerViewList.add(new RecyclerViewItem(acceptedWorks.get(i).year, acceptedWorks.get(i).month, acceptedWorks.get(i).day, i+1, true));
        }
        for (int i = 0; i < completedWorks.size(); i++) {
            recyclerViewList.add(new RecyclerViewItem(completedWorks.get(i).year, completedWorks.get(i).month, completedWorks.get(i).day, acceptedWorks.size()+i+1, false));
        }

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter();
        adapter.differ.submitList(recyclerViewList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }
}
