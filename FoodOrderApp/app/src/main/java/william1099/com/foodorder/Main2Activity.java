package william1099.com.foodorder;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         tabLayout = (TabLayout) findViewById(R.id.tablayout);
         viewPager = (ViewPager) findViewById(R.id.viewpager);
         floatingActionButton = (FloatingActionButton) findViewById(R.id.tohome);

         floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                 startActivity(intent);

             }
         });

         adapter = new ViewPagerAdapter(getSupportFragmentManager());
         adapter.addFragment(new FragmentA(), "Menu Hari Ini");
         adapter.addFragment(new FragmentB(), "Minuman");
         viewPager.setAdapter(adapter);
         tabLayout.setupWithViewPager(viewPager);

        }
}
