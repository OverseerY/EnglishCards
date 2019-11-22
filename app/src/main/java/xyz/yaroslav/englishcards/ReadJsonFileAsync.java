package xyz.yaroslav.englishcards;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ReadJsonFileAsync extends AsyncTask<String, Void, String> {
    private Context context;

    public ReadJsonFileAsync(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            InputStream is = context.getAssets().open(strings[0]);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Log.e("READ_JSON", "Exception: " + ex.getMessage());
        }
        return null;
    }
}
