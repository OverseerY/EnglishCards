package xyz.yaroslav.englishcards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.ViewHolder> {
    private List<CardItem> cardItems;

    public CardItemAdapter(List<CardItem> cardItems) {
        this.cardItems = cardItems;
    }

    @NonNull
    @Override
    public CardItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItem item = cardItems.get(position);

        holder.cardTitle.setText(item.getTitleCategory());
        holder.cardBody.setText(item.getBodyValue());
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle, cardBody;

        ViewHolder(View view) {
            super(view);

            cardTitle = view.findViewById(R.id.cardTitle);
            cardBody = view.findViewById(R.id.cardBody);
        }
    }
}















































