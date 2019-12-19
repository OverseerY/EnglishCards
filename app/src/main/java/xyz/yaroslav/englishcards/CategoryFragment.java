package xyz.yaroslav.englishcards;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        ImageView titleBack = ((MainActivity) getContext()).findViewById(R.id.menu_back);
        titleBack.setVisibility(View.GONE);
        titleBack.setOnClickListener(v -> ((MainActivity)getContext()).getSupportFragmentManager().popBackStack());
        TextView titleMenu = ((MainActivity) getContext()).findViewById(R.id.menu_title);
        titleMenu.setText(getString(R.string.app_name));


        progressBar = root.findViewById(R.id.cat_progressbar);
        recyclerView = root.findViewById(R.id.categories_items);

        prepareCategories();

        return root;
    }

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
        handler.post(() -> {
            categoryAdapter = new CardCategoryAdapter(getContext(), list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(categoryAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        });
    }
}










































