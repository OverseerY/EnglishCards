package xyz.yaroslav.englishcards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CardFragment extends Fragment {
    private String title = "";

    private TextView cardValue;
    private ImageView cardPlayPause;
    private TextView cardCounter;

    private Timer timer;
    private int counter;

    private boolean is_paused;

    private ArrayList<String> cards;
    private int cardIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cards = new ArrayList<>();
        cardIndex = 0;
        counter = 0;
        is_paused = false;
        prepareCards();
    }

    @Override
    public void onStop() {
        super.onStop();
        cardPlayPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play, getContext().getTheme()));
        is_paused = true;
        if (timer != null) {
            timer.cancel();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_card, container, false);

        ImageView titleBack = ((MainActivity) getContext()).findViewById(R.id.menu_back);
        titleBack.setVisibility(View.VISIBLE);
        titleBack.setOnClickListener(v -> ((MainActivity)getContext()).getSupportFragmentManager().popBackStack());
        TextView titleMenu = ((MainActivity) getContext()).findViewById(R.id.menu_title);
        titleMenu.setText(title.toUpperCase());

        TextView cardTitle = root.findViewById(R.id.card_cat);
        cardValue = root.findViewById(R.id.card_body);
        ImageView cardNext = root.findViewById(R.id.card_next);
        cardPlayPause = root.findViewById(R.id.card_pause);

        cardCounter = root.findViewById(R.id.card_counter);

        cardTitle.setText(title);
        cardValue.setText(cards.get(cardIndex));

        cardNext.setOnClickListener(v -> {
            changeCard(cardIndex);
        });

        cardPlayPause.setOnClickListener(v -> {
            if (is_paused){
                cardPlayPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause, getContext().getTheme()));
                is_paused = false;
                runTimer();
            } else {
                cardPlayPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play, getContext().getTheme()));
                is_paused = true;
                timer.cancel();
            }
        });

        runTimer();

        return root;
    }

    private void prepareCards() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            title = bundle.getString(getString(R.string.bundle_cat_key));
            cards = bundle.getStringArrayList(getString(R.string.bundle_card_items_key));
        }
    }

    private void changeCard(int index) {
        counter = 0;
        timer.cancel();
        if ((index + 1) < cards.size()) {
            cardValue.setText(cards.get(index + 1));
            cardIndex++;
            cardPlayPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause, getContext().getTheme()));
            is_paused = false;
            runTimer();
        } else {
            ((MainActivity)getContext()).getSupportFragmentManager().popBackStack();
        }
    }

    private void runTimer() {
        Timer timer = new Timer();
        this.timer = timer;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter++;
                if (counter >= 10) {
                    cardCounter.setText("00:" + counter);
                } else {
                    cardCounter.setText("00:0" + counter);
                }
                if (counter == 60) {
                    changeCard(cardIndex);
                }
            }
        }, 0, 1000);
    }
}






































