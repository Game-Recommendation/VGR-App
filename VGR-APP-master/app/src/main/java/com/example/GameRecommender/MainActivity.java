package com.example.GameRecommender;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.GameRecommender.Fragments.ComposeFragment;
import com.example.GameRecommender.Fragments.PostsFragment;
import com.example.GameRecommender.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private BottomNavigationView botNav;
    private DrawerLayout drawer;
    private NavigationView draw;
    Drawable ic_chat;
    Drawable ic_message;
    Drawable ic_profile;
    //It's the right code :)
    MenuItem item;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment = null;
    Class fragmentClass;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //botNav = findViewById(R.id.bot_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        draw = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setupDrawerContent(draw);

        /*drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (item.getItemId()) {
                    case R.id.ic_chat:
                        fragment = new PostsFragment();
                        break;
                    case R.id.ic_message:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.ic_profile:
                    default:
                        fragment = new ProfileFragment();
                        break;
                }

            }
        });
//        queryPosts();

        /*botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.ic_chat:
                        fragment = new PostsFragment();
                        break;
                    case R.id.ic_message:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.ic_profile:
                    default:
                        fragment = new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });
        botNav.setSelectedItemId(R.id.action_compose);*/
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        switch (menuItem.getItemId()) {
            case R.id.ic_profile:
                fragmentClass = ProfileFragment.class;
                break;
            case R.id.ic_filter:
                fragmentClass = ComposeFragment.class;
                break;
            case R.id.ic_chat:
                fragmentClass = PostsFragment.class;
                break;
            default:
                fragmentClass = ProfileFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
    }
}
