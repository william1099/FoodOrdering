package william1099.com.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main5Activity extends AppCompatActivity {
    TextView harga;
    EditText nama, makanan, desc, jumlah;
    String ID, hargaa, food;
    String ID_Cust;
    String waktu;
    int res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        ID = getIntent().getExtras().getString("ID");
        hargaa = getIntent().getExtras().getString("Harga");
        food = getIntent().getExtras().getString("Makanan");
        ID_Cust = "1";
        nama = (EditText) findViewById(R.id.nama);
        makanan = (EditText) findViewById(R.id.food);
        desc = (EditText) findViewById(R.id.desc);
        jumlah = (EditText) findViewById(R.id.jumlah);
        harga = (TextView) findViewById(R.id.price);
        harga.setText("Rp " + hargaa);
        makanan.setText(food);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        waktu = sdf.format(new Date());

        jumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!jumlah.getText().toString().matches("")) {
                    //String price = hargaa.substring(0,2) + hargaa.substring(3);
                    res = Integer.parseInt(hargaa);
                    res = res * Integer.parseInt(jumlah.getText().toString());
                    harga.setText("Rp " + res);
                }

            }
        });

    }

    public void send(View v) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "<your-server.com>/FoodOrderWeb/public/order/add",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("1");
                            if (code.equals("sukses")) {
                                Toast.makeText(Main5Activity.this, "Pesanan berhasil dikirim", Toast.LENGTH_SHORT).show();
                                desc.setText("");
                                jumlah.setText("");
                                makanan.setText("");
                                nama.setText("");
                            } else {
                                Toast.makeText(Main5Activity.this, "Terjadi Masalah, Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "error : " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID_Makanan", ID);
                params.put("ID_Customer", ID_Cust);
                params.put("Deskripsi", desc.getText().toString());
                params.put("Jumlah_Pemesanan", jumlah.getText().toString());
                params.put("Total_Harga", ""+res );
                params.put("Tanggal_Pemesanan", waktu);
                return params;
            }
        };
        RequestQueue requestQueue = (mySingleton.getInstance()).mRequestQueue;
        requestQueue.add(stringRequest);

    }
}
