package com.w20.votingnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class VoteActivity extends AppCompatActivity {

    private static final String TAG = "VoteActivity";

    Spinner spinner;
    EditText etId;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        spinner = findViewById(R.id.candidate);
        etId = findViewById(R.id.et_id);
        toggleButton = findViewById(R.id.toggle);
    }

    public void vote(View view) {
        Intent intent = new Intent();
        String candidate = getCandidate();
        String id = etId.getText().toString();
        if (candidate != null && !id.isEmpty() && toggleButton.isChecked()) {
            intent.putExtra("candidate", candidate);
            intent.putExtra("id", id);
            Log.d(TAG, "vote: " + id + ", " + candidate);

            setResult(RESULT_OK, intent);
        }
    }

    private String getCandidate() {
        if (spinner.getSelectedItemPosition() != 0) {
            return spinner.getSelectedItem().toString();
        }
        return null;
    }
}
