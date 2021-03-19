package com.example.matthieugedeon.android_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.matthieugedeon.android_project.R;
import com.example.matthieugedeon.android_project.classes.AsyncCourseFetcher;
import com.example.matthieugedeon.android_project.classes.AsyncWalletFetcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Finding the fetch button by it's id and linking a tailored onClickListener
        Button b1=(Button)findViewById(R.id.fetch_button);
        b1.setOnClickListener(new AddressDetailsOnClickListener());

        b1=(Button)findViewById(R.id.open_login);
        b1.setOnClickListener(new LogInOnClickListener());

        b1=(Button)findViewById(R.id.open_signup);
        b1.setOnClickListener(new SignUpOnClickListener());

        AsyncCourseFetcher fetcher = new AsyncCourseFetcher(R.id.course,this);
        fetcher.execute("https://blockchain.info/ticker");

        populateView();

    }

    private void populateView(){

        //Populate Spinner (Android Developers)
        Spinner spinner = (Spinner) findViewById(R.id.currency_course);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        SpinnerListener sl = new SpinnerListener();
        spinner.setOnItemSelectedListener(sl);

        //Populate Spinner (Android Developers)
        spinner = (Spinner) findViewById(R.id.coin_course);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.coin_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(sl);
    }

    //Tailored onClickListener that launch our parameterized AsyncTask
    class AddressDetailsOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.i("Button","Clicked");
            Intent intent = new Intent(MainActivity.this, AddressDetailsActivity.class);
            intent.putExtra("address", "https://blockchain.info/rawaddr/1F1tAaz5x1HUXrCNLbtMDqcw6o5GNn4xqX");
            startActivity(intent);

        }
    }

    public class SpinnerListener implements AdapterView.OnItemSelectedListener {

        /*
        int spinnerID;

        public SpinnerListener(int spinnerID){
            super();
            this.spinnerID = spinnerID;
        }
         */

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {

            if (parent.getId() == R.id.coin_course) {
                ImageView iv = (ImageView) findViewById(R.id.course_icon);
                String s = (String)parent.getItemAtPosition(pos);

                switch (s) {
                    case "1 BTC":
                        iv.setImageDrawable(getDrawable(R.drawable.btc));
                        break;
                    case "1 ETH":
                        iv.setImageDrawable(getDrawable(R.drawable.eth));
                        break;
                    default: break;
                }
            }
            else if(parent.getId() == R.id.currency_course)
            {
                //do this
            }
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    class SignUpOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.i("Signup","Clicked");
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);

        }
    }

    class LogInOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.i("Login","Clicked");
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);

        }
    }


}