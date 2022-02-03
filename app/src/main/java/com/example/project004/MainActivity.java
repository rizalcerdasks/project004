package com.example.project004;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.project004.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    Fragment fragment_kanan = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }
    //menampilkan option menu pada toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment_kanan = new HomeFragment();
                getSupportActionBar().setTitle("Home");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment_kanan);
                break;
            case R.id.nav_store:
                fragment_kanan = new ContactUsFragment();
                getSupportActionBar().setTitle("Store");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment_kanan);
                break;
            case R.id.nav_event:
                fragment_kanan = new ContactUsFragment();
                getSupportActionBar().setTitle("Event");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment_kanan);
                break;
            case R.id.nav_facebook:
                Intent myImplicitIntent = new Intent(Intent.ACTION_VIEW);
                myImplicitIntent.setData(Uri.parse("https://facebook.com"));
                startActivity(myImplicitIntent);
                break;
            case R.id.nav_instagram:
                Intent myImplicitIntent2 = new Intent(Intent.ACTION_VIEW);
                myImplicitIntent2.setData(Uri.parse("https://instagram.com"));
                startActivity(myImplicitIntent2);
                break;
            case R.id.nav_topup:
                fragment_kanan = new ContactUsFragment();
                getSupportActionBar().setTitle("Top Up");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment_kanan);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        //custom toolbar
        setSupportActionBar(binding.toolbar);

        //default fragment dibuka pertama kali
        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, new HomeFragment())
                .commit();
        binding.navView.setCheckedItem(R.id.nav_home);

        //membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
                R.string.open, R.string.close);

        //drawer back button
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        //singkronisasi drawer
        toggle.syncState();

        //salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        getSupportActionBar().setTitle("Home");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_contact_us:
                        fragment = new ContactUsFragment();
                        getSupportActionBar().setTitle("Store");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_about_us:
                        fragment = new ContactUsFragment();
                        getSupportActionBar().setTitle("Event");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_community:
                        fragment = new ContactUsFragment();
                        getSupportActionBar().setTitle("Community");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_topup:
                        fragment = new ContactUsFragment();
                        getSupportActionBar().setTitle("Top Up");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                }
                return true;
            }
        });



    }

    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
                );
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}