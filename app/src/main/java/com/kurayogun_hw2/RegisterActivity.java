package com.kurayogun_hw2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends AppCompatActivity {

    private ExpandListAdapter expAdapter;
    private ArrayList<Group> expListItems;
    private ExpandableListView ExpandList;

    Spinner spinner;
    com.github.clans.fab.FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.spinner);
        fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        ExpandList = (ExpandableListView) findViewById(R.id.exp_list);
        expListItems = setExpandView();
        expAdapter = new ExpandListAdapter(RegisterActivity.this, expListItems);
        ExpandList.setAdapter(expAdapter);
        ExpandList.expandGroup(0);

        ExpandList.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        ExpandList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //Filling spinner
        fillSpinner();
        countryOpen();
    }


    public ArrayList<Group> setExpandView() {

        String group_names[] = {getString(R.string.general),getString(R.string.personal)};

        String country_names[] = {getString(R.string.username),
                                  getString(R.string.password),
                                  getString(R.string.age),
                                  getString(R.string.blood_type)};

        int Images[] = { R.drawable.ic_person_dark , R.drawable.ic_pw,R.drawable.ic_person_dark , R.drawable.ic_pw};

        ArrayList<Child> ch_list;
        ArrayList<Group> list = new ArrayList<Group>();


        int size = 2;
        int j = 0;
        for (String group_name : group_names) {
            Group gru = new Group();
            gru.setName(group_name);

            ch_list = new ArrayList<Child>();
            for (; j < size; j++) {
                Child ch = new Child();
                ch.setName(country_names[j]);
                ch.setImage(Images[j]);
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);

            size += 2;
        }

        return list;
    }

    public void fillSpinner(){
        List<String> spinnerArray1 = new ArrayList<String>();
        spinnerArray1.add(getString(R.string.male));
        spinnerArray1.add(getString(R.string.female));

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.custom_spinner, spinnerArray1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter1);
    }

    public void countryOpen(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveShared();
                Intent i = new Intent(getApplicationContext(), CountryActivity.class);
                startActivity(i);
            }
        });
    }

    public void saveShared(){
        String spinnerGender = spinner.getSelectedItem().toString();
        EditText editText = (EditText) findViewById(R.id.child_name);

        String username = editText.getText().toString();
        Log.d("MyApp", "Username : " + username);
        Log.d("MyApp", "Gender : "+spinnerGender);

        SharedPreferences prefs = getSharedPreferences("user_Email", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user_gender", spinnerGender);
        editor.putString("username",username);
        editor.apply(); // This line is IMPORTANT. If you miss this one its not gonna work!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
