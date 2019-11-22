package xyz.yaroslav.englishcards;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesParseAsync extends AsyncTask<String, Void, ArrayList<String>> {
    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        try {
            JSONObject top = new JSONObject(strings[0]);
            JSONArray jsonArray = top.getJSONArray("categories");
            ArrayList<String> list = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nestedObject = jsonArray.getJSONObject(i);
                String tagName = nestedObject.getString("name");
                list.add(tagName);
            }

            if (!list.isEmpty()) {
                return list;
            }
        } catch (JSONException e) {
            Log.e("CATS_JSON_PARSE", "JSON Exception: " + e.getMessage());
        }
        return null;
    }
}
