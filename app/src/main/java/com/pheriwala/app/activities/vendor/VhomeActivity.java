package com.pheriwala.app.activities.vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pheriwala.app.R;
import com.pheriwala.app.fragments.customers.ChomeFragment;
import com.pheriwala.app.fragments.customers.LoginFragment;
import com.pheriwala.app.fragments.customers.ProductFragment;
import com.pheriwala.app.fragments.customers.ProfileFragment;
import com.pheriwala.app.fragments.customers.VendorFragment;


public class VhomeActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    Fragment fragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chome);

        mAuth = FirebaseAuth.getInstance();

        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.chome_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment fragment = new ChomeFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.chome_fr_container,fragment);
        transaction.addToBackStack(fragment.toString());
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        // Find our drawer view
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.chome_nav_view);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUser = mAuth.getCurrentUser();
        if (mUser == null) {
            Toast.makeText(this, "please log in ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(VhomeActivity.this, LoginFragment.class));
        }
    }



    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.cust_side_home:
                fragmentClass = ChomeFragment.class;
                break;
            case R.id.cust_side_product:
                fragmentClass = ProductFragment.class;
                break;
            case R.id.cust_side_vendors:
                fragmentClass = VendorFragment.class;
                break;
            case R.id.cust_side_profile:
                fragmentClass = ProfileFragment.class;
                break;




            default:
                fragmentClass = ChomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.chome_fr_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }


}

























//    class HomeActivity extends VenHomeActivity implements FragmentChangeListener{
//
//
//
//        @Override
//        public void replaceFragment(Fragment fragment) {
//
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.ven_home_frag_container,fragment,fragment.toString());
//            transaction.addToBackStack(fragment.toString());
//            transaction.commit();
//
//
//        }
//    }
