package com.visapps.spacex.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visapps.spacex.R;
import com.visapps.spacex.adapters.LaunchesAdapter;
import com.visapps.spacex.dialogs.YearSelectionDialog;
import com.visapps.spacex.loaders.LaunchesLoader;
import com.visapps.spacex.models.Launches;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Launches>, LaunchesAdapter.LaunchesAdapterCallback{

    private RecyclerView laucnheslist;
    private TextView errortext;
    private SwipeRefreshLayout refresher;
    private LinearLayout errorstate;

    private Loader<Launches> loader;
    private LaunchesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        laucnheslist = findViewById(R.id.launcheslist);
        errortext = findViewById(R.id.errortext);
        refresher = findViewById(R.id.refresher);
        refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLaunches(true);
            }
        });
        errorstate = findViewById(R.id.errorstate);
        adapter = new LaunchesAdapter(this);
        adapter.setCallback(this);
        laucnheslist.setLayoutManager(new LinearLayoutManager(this));
        laucnheslist.setAdapter(adapter);
        loadLaunches(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_selectyear) {
            showYearSelectionDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Launches> onCreateLoader(int id, Bundle args) {
        errorstate.setVisibility(View.GONE);
        adapter.clearLaunches();
        return new LaunchesLoader(this, args);
    }

    @Override
    public void onLoadFinished(Loader<Launches> loader, Launches launches) {
        if(launches == null){
            errorstate.setVisibility(View.VISIBLE);
            laucnheslist.setVisibility(View.GONE);
            errortext.setText(getString(R.string.loadingerror));
        }
        else{
            if(launches.getLaunches().size() > 0){
                adapter.setLaunches(launches);
                errorstate.setVisibility(View.GONE);
                laucnheslist.setVisibility(View.VISIBLE);
            }
            else{
                errorstate.setVisibility(View.VISIBLE);
                laucnheslist.setVisibility(View.GONE);
                errortext.setText(getString(R.string.nolaunches));
            }
        }
        refresher.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Launches> loader) {

    }

    @Override
    public void onOpenLink(String link){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }

    @Override
    public void onOpenTabs(String link){
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(this.getResources()
                        .getColor(R.color.colorPrimary))
                .setShowTitle(true)
                .build();
        customTabsIntent.launchUrl(this,Uri.parse(link));
    }

    private void loadLaunches(boolean restart){
        refresher.setRefreshing(true);
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        int year = sharedPreferences.getInt("year", 2017);
        Bundle b = new Bundle();
        b.putInt("year", year);
        if(restart){
            loader = getSupportLoaderManager().restartLoader(1,b,this);
        }
        else{
            loader = getSupportLoaderManager().initLoader(1,b,this);
        }
    }

    private void showYearSelectionDialog(){
        YearSelectionDialog dialog = new YearSelectionDialog();
        dialog.setCallback(new YearSelectionDialog.YearSelectionCallback() {
            @Override
            public void onSelectYear() {
               loadLaunches(true);
            }
        });
        dialog.show(getSupportFragmentManager(),"yearselection");
    }

}
