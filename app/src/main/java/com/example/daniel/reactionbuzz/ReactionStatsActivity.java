package com.example.daniel.reactionbuzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReactionStatsActivity extends AppCompatActivity {

    ListView listView;

    private static final String FILENAME = "reactStat.sav";

    private ArrayAdapter<Long> reactionAdapter;
    private StatsReaction statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reactionstats);
        loadFromFile();
        listView = (ListView) findViewById(R.id.statsView);
        reactionAdapter = new ArrayAdapter<Long>(this, android.R.layout.simple_list_item_1, statistics.returnList());
        listView.setAdapter(reactionAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_stats, menu);
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

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015/09/23
            statistics = gson.fromJson(in, StatsReaction.class);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            statistics = new StatsReaction();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}