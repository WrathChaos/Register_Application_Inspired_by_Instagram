package com.kurayogun_hw2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryActivity extends AppCompatActivity {

    final List<Country> countryList=new ArrayList<>();
    ListView listView ;
    ListViewAdapter adapter;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);




        listView = (ListView) findViewById(R.id.country_list);
        adapter = new ListViewAdapter(this, countryList);
        listView.setAdapter(adapter);

        fillCountryList();




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //DialogBox will be here
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle(getString(R.string.info));

                //SharedPreferences Getting Items
                SharedPreferences prefs = getSharedPreferences("user_Email", MODE_PRIVATE);
                String e_mail = prefs.getString("user_Email",
                        "No E-Mail found, we might missed it, Sorry :(");
                String gender = prefs.getString("user_gender", "No Gender found, we might missed it, Sorry :(");
                String username = prefs.getString("username", "No Username found, we might missed it, Sorry :(");
                //Get the clicked country name
                TextView tv = (TextView)view.findViewById(R.id.country_name);
                String country = tv.getText().toString();

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("E-Mail : "+e_mail +"\nUsername : " + username + "\nGender : " + gender + "\nCountry : " + country);
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.ic_info);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dialog.dismiss();
                     }
                  });
              dialog.show();
            }

        });
    }

    public void fillCountryList(){
        String[] countries = Locale.getISOCountries();

        int j = 0;
        // Loop each country
        for(int i = 0; i < countries.length; i++) {

            String country = countries[i];
            Locale locale = new Locale("en", country);

            // Get the country name by calling getDisplayCountry()
            String countryName = locale.getDisplayCountry();

            // Printing the country name on the console
            countryList.add(new Country(countryName));
            j++;
        }
        Log.d("MyApp", "Total Country # : " + j);
    }

    public void openSetting(View view){
        Intent i = new Intent(this,SettingsActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_country, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
