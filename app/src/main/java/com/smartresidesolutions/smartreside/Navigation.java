package com.smartresidesolutions.smartreside;

import android.os.Bundle;
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
import android.widget.TextView;

import com.smartresidesolutions.model.User;
import com.smartresidesolutions.model.UserMenu;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        User user=(User)getIntent().getSerializableExtra("userLoginData");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager=getSupportFragmentManager();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //To Set Role in Header
        View header=navigationView.getHeaderView(0);
        TextView roleTv=header.findViewById(R.id.roleTextView);
        roleTv.setText(user.getUserRole());
        //set UserMenu Items
        Menu menu=navigationView.getMenu();
        menu.clear();
        for(UserMenu userMenu:user.getMenuList()) {
            menu.add(Menu.NONE,userMenu.getMenuId(),userMenu.getSequence(),userMenu.getMenuName()).
                    setIcon(selectMenuIcon(userMenu.getMenuName()));
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    private int selectMenuIcon(String title) {

        switch (title){
            case "Home":return R.drawable.ic_home_black_24dp;
            case "Access Control":return R.drawable.ic_book_black_24dp;
            case "Maintainance Details":return R.drawable.ic_mode_edit_black_24dp;
            case "Contact Us":return R.drawable.ic_email_black_24dp;
        }
        return 0;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass=null;
        if (id == R.id.access_control) {

          //  fragmentClass=AccessControl.class;
        } else if (id == R.id.add_role) {

        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_contactUs) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
