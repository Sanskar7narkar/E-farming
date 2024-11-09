package com.example.efarm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
ImageButton buttonDrawerToggle;
NavigationView navigationView;
private BottomNavigationView bottomNavigationView;
private FrameLayout frameLayout;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationview);


        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });


        View headerView=navigationView.getHeaderView(0);
        ImageView UserImage = headerView.findViewById(R.id.UserImage);
        TextView textUsername = headerView.findViewById(R.id.textUsername);

        UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, textUsername.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId=item.getItemId();

                if(itemId == R.id.Weather){
                    Toast.makeText(MainActivity.this, "Weather clicked", Toast.LENGTH_SHORT).show();
                }
                if(itemId == R.id.terms_and_conditions){
                    Toast.makeText(MainActivity.this, "Terms and conditions clicked", Toast.LENGTH_SHORT).show();
                } if(itemId == R.id.feedback){
                    Toast.makeText(MainActivity.this, "Feedback clicked", Toast.LENGTH_SHORT).show();
                } if(itemId == R.id.Contact){
                    Toast.makeText(MainActivity.this, "Weather clicked", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.close();
                return false;
            }
        });

        //not in project
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        //***************

        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout=findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if(itemId==R.id.home){
                  loadFragment(new HomeFragment(),false);

                }else if (itemId==R.id.water){
                    loadFragment(new WaterFragment(),false);

                }else if(itemId==R.id.order){
                    loadFragment(new CartFragment(),false);

                }else{ // profile
                    loadFragment(new ProfileFragment(),false);

                }

                return true;
            }
        });
        loadFragment(new HomeFragment(),true);

    }
    private void loadFragment(Fragment fragment,boolean isAppInitialize){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        if(isAppInitialize){
            fragmentTransaction.add(R.id.frameLayout,fragment);
        }else{
            fragmentTransaction.replace(R.id.frameLayout,fragment);
        }


        fragmentTransaction.commit();
    }

}