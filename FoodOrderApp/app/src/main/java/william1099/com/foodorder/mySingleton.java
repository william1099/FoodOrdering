package william1099.com.foodorder;


import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class mySingleton {
    private static mySingleton mInstance = null;
    public RequestQueue mRequestQueue;
    public ImageLoader imageLoader;
    private mySingleton() {
        mRequestQueue = Volley.newRequestQueue(myApplication.getAppContext());

    }
    public static mySingleton getInstance() {
        if (mInstance == null) {
            mInstance = new mySingleton();
        }
        return mInstance;
    }

}
