package com.ellis.memberplanet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.activity.activitypay.ActivityAirtelPay;
import com.ellis.memberplanet.activity.activitypay.ActivityMtnPay;
import com.ellis.memberplanet.activity.activitypay.ActivityTigoPay;
import com.ellis.memberplanet.fragment.FragmentCategory;
import com.ellis.memberplanet.fragment.FragmentEvent;
import com.ellis.memberplanet.fragment.FragmentHome;
import com.ellis.memberplanet.fragment.FragmentMyMembers;
import com.ellis.memberplanet.fragment.TransFragment;
import com.ellis.memberplanet.object.ObjectUser;
import com.ellis.memberplanet.session.SharedPrefManager;
import com.github.clans.fab.FloatingActionMenu;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityHome extends AppCompatActivity {

    Menu mNavMenu;
    View navHeader;
    boolean clicked;
    ObjectUser user;
    Toolbar mToolbar;
    TextView fullName;
    TextView farmName;
    ImageView profilepic;
    FloatingActionButton fab;
    DrawerLayout mDrawerLayout;
    CircleImageView userPicture;
    NavigationView mNavigationView;
    ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialize widgets and components
        initialize();

        Intent intent = getIntent();
        String screen = intent.getStringExtra("Screen");

        if (screen != null) {

            setHomeFragment(screen);

        } else {

            setHomeFragment("News Feeds");
            mToolbar.setTitle("News Feeds");
        }

        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {

            mNavigationView.inflateMenu(R.menu.logged_in_nav_drawer_menu);

        } else {

            startActivity(new Intent(getApplicationContext(), ActivityLogin.class));

        }

        ////////////////////fabclan_again
        materialDesignFAM = findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = findViewById(R.id.material_design_floating_action_menu_item4);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityMtnPay.class));
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityTigoPay.class));

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityAirtelPay.class));

            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked

            }
        });

        String imageurl =SharedPrefManager.getInstance(getApplicationContext()).getobjectUser().getImage();
        Glide.with(getApplicationContext()).load(imageurl).into(profilepic);
        /////////////////

        String fullName = SharedPrefManager.getInstance(getApplicationContext()).getobjectUser().getFullname();
        String email = SharedPrefManager.getInstance(getApplicationContext()).getobjectUser().getEmail();


        this.fullName.setText(fullName);
        farmName.setText(email);


        // Replace action bar and set custom mToolbar as actionbar
        setSupportActionBar(mToolbar);


        // Setting up the Up button
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        // Setting up the mDrawerLayout's app bar listener
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        // Setting up the itemSelectedListener
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                displaySelectedScreen(item.getItemId());

                return true;
            }

        });

    }

    private void gotoActivity(AppCompatActivity activity) {

        startActivity(new Intent(this, activity.getClass()));

    }


    public void setHomeFragment(String TAG) {

        FragmentHome fragmentHome = new FragmentHome();

        switch (TAG) {
            case "News Feeds":
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.homeLayout, fragmentHome, TAG)
                        .commit();

                mToolbar.setTitle(TAG);
                mCurrentFragment = fragmentHome;
                break;

            case "Events":
                FragmentEvent fragmentEvent = new FragmentEvent();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.homeLayout, fragmentEvent, TAG)
                        .commit();

                mToolbar.setTitle(TAG);
                mCurrentFragment = fragmentEvent;
                break;

            case "Year Groups":
                FragmentCategory fragmentCategory = new FragmentCategory();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.homeLayout, fragmentCategory, TAG)
                        .commit();

                mToolbar.setTitle(TAG);
                mCurrentFragment = fragmentCategory;
                break;


            case "Members":
                FragmentMyMembers fragmentMyMembers = new FragmentMyMembers();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.homeLayout, fragmentMyMembers, TAG)
                        .commit();

                mToolbar.setTitle(TAG);
                mCurrentFragment = fragmentMyMembers;
                break;


            case "Transactions":
                TransFragment transFragment = new TransFragment();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.homeLayout, transFragment, TAG)
                        .commit();

                mToolbar.setTitle(TAG);
                mCurrentFragment = transFragment;
                break;


            default:
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.homeLayout, fragmentHome)
                        .commit();

                mCurrentFragment = fragmentHome;
                break;

        }

    }


    private void displaySelectedScreen(@IdRes int itemId) {

        switch (itemId) {

            case R.id.nav_item_home:
                setHomeFragment("News Feeds");
                break;

            case R.id.nav_item_events:
                setHomeFragment("Events");
                break;

            case R.id.nav_item_members:
                setHomeFragment("Members");
                break;

            case R.id.nav_item_category:
                setHomeFragment("Year Groups");
                break;

            case R.id.nav_item_transaction:
                setHomeFragment("Transactions");
                break;

            case R.id.nav_item_account:
                gotoActivity(new ActivityMyAccount());
                break;

            case R.id.nav_item_scan:

                gotoActivity(new ActivityScan());
                break;

            case R.id.nav_item_login:
                gotoActivity(new ActivityLogin());
                clicked = false;
                break;

            case R.id.nav_item_logout:
                logout();
                clicked = true;
                break;

        }


        // Finally, close the drawer
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    private void logout() {

        SharedPrefManager.getInstance(this).logout();
        finish();
        gotoActivity(new ActivityLogin());

    }


    private void initialize() {

        user = new ObjectUser();
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mFragmentManager = getSupportFragmentManager();
        mNavigationView = findViewById(R.id.navigationView);

        mNavMenu = mNavigationView.getMenu();
        navHeader = mNavigationView.getHeaderView(0);
        profilepic = navHeader.findViewById(R.id.navDrawerHeader_UserImage);
        fullName = navHeader.findViewById(R.id.navDrawerHeader_UserFullName);
        farmName = navHeader.findViewById(R.id.navDrawerHeader_UserFarmName);

    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.findFragmentByTag("News Feeds") == mCurrentFragment) {

            this.finishAffinity();

        } else {

            gotoActivity(new ActivityHome());

        }
    }
}
