package xyz.yaroslav.englishcards;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardFragment extends Fragment {
    private String title = "";

    TextView cardTitle;
    TextView cardValue;
    ImageView cardNext;
    TextView cardCounter;

    ArrayList<String> cards;
    CountDownTimer countDownTimer;
    private int cardIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cards = new ArrayList<>();
        cardIndex = 0;
        prepareCards();
    }

    @Override
    public void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        countDownTimer.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_card, container, false);

        cardTitle = root.findViewById(R.id.card_cat);
        cardValue = root.findViewById(R.id.card_body);
        cardNext = root.findViewById(R.id.card_next);
        cardCounter = root.findViewById(R.id.card_counter);

        cardTitle.setText(title);
        cardValue.setText(cards.get(cardIndex));

        cardNext.setOnClickListener(v -> {
            changeCard(cardIndex);
        });

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long value = millisUntilFinished / 1000;

                if (value >= 10) {
                    cardCounter.setText("00:" + value);
                } else {
                    cardCounter.setText("00:0" + value);
                }
            }

            @Override
            public void onFinish() {
                changeCard(cardIndex);
            }
        }.start();

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
        if ((index + 1) < cards.size()) {
            cardValue.setText(cards.get(index + 1));
            cardIndex++;
            countDownTimer.start();
        } else {
            cardIndex = 0;
            cardValue.setText(cards.get(cardIndex));
            countDownTimer.start();
        }
    }

}






































