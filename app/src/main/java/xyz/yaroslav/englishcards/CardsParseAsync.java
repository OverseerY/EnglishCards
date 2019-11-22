package xyz.yaroslav.englishcards;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CardsParseAsync extends AsyncTask<String, Void, List<CardItem>> {
    @Override
    protected List<CardItem> doInBackground(String... strings) {
        try {
            JSONObject top = new JSONObject(strings[0]);
            JSONArray jsonArray = top.getJSONArray("cards");
            List<CardItem> list = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nestedObject = jsonArray.getJSONObject(i);
                String category = nestedObject.getString("category");
                String value = nestedObject.getString("value");
                CardItem tagItem = new CardItem(category, value);
                list.add(tagItem);
            }

            if (!list.isEmpty()) {
                return list;
            }
        } catch (JSONException e) {
            Log.e("CARDS_JSON_PARSE", "JSON Exception: " + e.getMessage());
        }
        return null;
    }
}
