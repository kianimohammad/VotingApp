package com.w20.votingnapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    List<String> ids;
//    String[] candidates;
    List<String> candidates;

    int[] votes = new int[3];

    EditText etcan1, etcan2, etcan3, winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ids = new ArrayList<>();
//        candidates = getResources().getStringArray(R.array.candidates);
        candidates = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.candidates)));

        etcan1 = findViewById(R.id.et_can1);
        etcan2 = findViewById(R.id.et_can2);
        etcan3 = findViewById(R.id.et_can3);
        winner = findViewById(R.id.winner);
    }

    public void startVoting(View view) {
        Intent intent = new Intent(this, VoteActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String candidate = data.getExtras().getString("candidate");
                String id = data.getExtras().getString("id");
                Log.d(TAG, "vote1: " + id + ", " + candidate);
                // check if the id is not in the ids
                if (!ids.contains(id)) {
                    ids.add(id);
                    incrementVote(candidate);
                }
            }
        }
    }

    private void incrementVote(String candidate) {

        switch (candidate) {
            case "Candidate 1":
                votes[0]++;
                break;
            case "Candidate 2":
                votes[1]++;
                break;
            case "Candidate 3":
                votes[2]++;
                break;
        }

        etcan1.setText(String.valueOf(votes[0]));
        etcan2.setText(String.valueOf(votes[1]));
        etcan3.setText(String.valueOf(votes[2]));
        displayWinner(votes);

    }

    private void displayWinner(int[] votes) {
        int index = getIndexOfLargest(votes);
        switch (index) {
            case 0:
                winner.setText("Candidate 1");
                break;
            case 1:
                winner.setText("Candidate 2");
                break;
            case 2:
                winner.setText("Candidate 3");
                break;

            default:
                winner.setText("N/A");
                break;
        }
    }

    public int getIndexOfLargest(int[] array) {
        if (array == null || array.length == 0)
            return -1; // null or empty

        int maxAt = 0;

        for (int i = 0; i < array.length; i++) {
            maxAt = array[i] > array[maxAt] ? i : maxAt;
        }
        // position of the first largest found
        return maxAt;
    }
}
