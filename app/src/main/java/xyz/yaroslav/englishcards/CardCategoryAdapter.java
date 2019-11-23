package xyz.yaroslav.englishcards;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardCategoryAdapter extends RecyclerView.Adapter<CardCategoryAdapter.ViewHolder> {
    private ArrayList<String> categories;
    private List<CardItem> cards;
    private Context context;

    public CardCategoryAdapter(Context context, ArrayList<String> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card_category, parent, false);

        cards = new ArrayList<>();
        prepareCards();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String category = categories.get(position);

        holder.tagTitle.setText(category);
        holder.itemView.setOnClickListener(v -> {
            displayCardFragment(category);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tagTitle;

        ViewHolder(View view) {
            super(view);

            tagTitle = view.findViewById(R.id.categoryTitle);
        }
    }

    private void displayCardFragment(String category) {
        ArrayList<String> card_items = new ArrayList<>();
        for (CardItem cardItem : cards) {
            if (cardItem.getTitleCategory().equals(category)) {
                card_items.add(cardItem.getBodyValue());
            }
        }

        Collections.shuffle(card_items);

        CardFragment fragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putString(context.getString(R.string.bundle_cat_key), category);
        bundle.putStringArrayList(context.getString(R.string.bundle_card_items_key), card_items);
        fragment.setArguments(bundle);
        FragmentTransaction ft = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void prepareCards() {
        try {
            String json = new ReadJsonFileAsync(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "data.json").get();
            if (json != null && !json.isEmpty()) {
                List<CardItem> items = new CardsParseAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, json).get();
                if (items != null) {
                    cards = items;
                } else {
                    Log.e("PREPARE_CARDS", "List is null");
                }
            } else {
                Log.e("PREPARE_CARDS", "Json is null or empty");
            }
        } catch (InterruptedException | ExecutionException e) {
            Log.e("PREPARE_CARDS", "Exception: " + e.getMessage());
        }
    }
}

































