package william1099.com.foodorder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentB extends Fragment{

    RecyclerView recyclerView;
    View v;
    Context context;
    TextView txt;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> map;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_b, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view2);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        data = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "<your-server.com>/FoodOrderWeb/public/paket/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            Log.d("success", "success : ");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                map = new HashMap<>();
                                map.put("ID", jsonObject.getString("ID_Makanan"));
                                map.put("Nama", jsonObject.getString("Nama"));
                                map.put("Gambar", jsonObject.getString("Gambar"));
                                map.put("Harga", jsonObject.getString("Harga"));
                                data.add(map);
                            }

                            myAdapter2 adapter = new myAdapter2(data, context);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);

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
