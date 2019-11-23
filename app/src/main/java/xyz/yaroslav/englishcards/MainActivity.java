package xyz.yaroslav.englishcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setScreenOrientationToPortrait();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categories = new ArrayList<>();
        prepareCategories();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            getSupportFragmentManager().popBackStack();
        }
    }

    private void setScreenOrientationToPortrait() {
        switch (getResources().getConfiguration().orientation){
            case Configuration.ORIENTATION_PORTRAIT:

            case Configuration.ORIENTATION_LANDSCAPE:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }
    }

    private void displayCategoriesFragment() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(getString(R.string.bundle_cards_key), categories);
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void prepareCategories() {
        Handler handler = new Handler();
        handler.post(() -> {
            try {
                String json = new ReadJsonFileAsync(getApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "cat.json").get();
                if (json != null && !json.isEmpty()) {
                    ArrayList<String> items = new CategoriesParseAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, json).get();
                    if (items != null) {
                        categories = items;
                        displayCategoriesFragment();
                    } else {
                        Log.e("PREPARE_CARDS", "List is null");
                    }
                } else {
                    Log.e("PREPARE_CARDS", "Json is null or empty");
                }
            } catch (InterruptedException | ExecutionException e) {
                Log.e("PREPARE_CARDS", "Exception: " + e.getMessage());
            }
        });
    }
}
