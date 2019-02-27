package william1099.com.foodorder;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.viewHolder>{
    ArrayList<HashMap<String, String>> datasource;
    Context context;
    Button tambah;
    EditText text;
    static ProgressDialog progressDialog;
    public myAdapter2(ArrayList<HashMap<String, String>> datasource, Context context) {
        this.datasource = datasource;
        this.context = context;

    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycle2, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        holder.title.setText(datasource.get(position).get("Nama"));
        holder.harga.setText(datasource.get(position).get("Harga"));
        Picasso.with(context).load(datasource.get(position).get("Gambar")).into(holder.gambar);


        holder.tombol.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context, MainActivity.class);
                //intent.putExtra("link", datasource.get(position).get("link"));
                //activity.startActivityForResult(intent, 1);
            }
        });

        holder.gambar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context, Main4Activity.class);
                intent.putExtra("ID", datasource.get(position).get("ID"));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return datasource.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        public TextView title, harga;
        public ImageView gambar;
        public Button tombol;
        public RelativeLayout menu;

        public viewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.harga = (TextView) itemView.findViewById(R.id.harga);
            this.gambar = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.tombol = (Button) itemView.findViewById(R.id.buy);
            this.menu = (RelativeLayout) itemView.findViewById(R.id.menu);
        }
    }

}
