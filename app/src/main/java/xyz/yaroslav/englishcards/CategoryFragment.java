package xyz.yaroslav.englishcards;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    private CardCategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        progressBar = root.findViewById(R.id.cat_progressbar);
        recyclerView = root.findViewById(R.id.categories_items);

        prepareCategories();

        return root;
    }

    /*
    private void parseCategories() {
        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            try {
                String result = new ReadJsonFileAsync(getContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "cat.json").get();
                if (result != null && !result.isEmpty()) {
                    try {
                        cardCategories = new CategoriesParseAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, result).get();
                        if (cardCategories != null && !cardCategories.isEmpty()) {
                            displayCategories(cardCategories);
                        } else {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        Log.e("WHITELIST_PARSE", "Exception: " + e.getMessage());
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            } catch (InterruptedException | ExecutionException e) {
                Log.e("PARSE_JSON_FILE", "Exception: " + e.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, 100);
    }
    */

    private void prepareCategories() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ArrayList<String> cardCategories = bundle.getStringArrayList(getString(R.string.bundle_cards_key));
            if (cardCategories != null && !cardCategories.isEmpty()) {
                displayCategories(cardCategories);
            }
        }
    }

    private void displayCategories(ArrayList<String> list) {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            categoryAdapter = new CardCategoryAdapter(getContext(), list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(categoryAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        }, 100);
    }
}










































