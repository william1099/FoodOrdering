package william1099.com.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Main3Activity extends AppCompatActivity {
    TextView nama, harga, bahan, deskripsi;
    ImageView gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nama = (TextView) findViewById(R.id.nama);
        bahan = (TextView) findViewById(R.id.bahan);
        deskripsi = (TextView) findViewById(R.id.desc);
        harga = (TextView) findViewById(R.id.harga);
        gambar = (ImageView) findViewById(R.id.gambar);

        String key = getIntent().getExtras().getString("ID");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "<your-server.com>/FoodOrderWeb/public/food/" + key,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            nama.setText(jsonObject.getString("Nama"));
                            harga.setText(jsonObject.getString("Harga"));
                            bahan.setText(jsonObject.getString("Bahan_Pokok"));
                            deskripsi.setText(jsonObject.getString("Deskripsi"));
                            Picasso.with(Main3Activity.this).load(jsonObject.getString("Gambar")).into(gambar);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "error : " + error.getMessage());
            }
        });
        RequestQueue requestQueue = (mySingleton.getInstance()).mRequestQueue;
        requestQueue.add(stringRequest);

    }
}

