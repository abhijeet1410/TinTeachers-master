package com.abhijeet14.tinteachers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.abhijeet14.tinteachers.fragments.AboutFragment;
import com.abhijeet14.tinteachers.fragments.AttendanceFragment;
import com.abhijeet14.tinteachers.fragments.HelpFragment;
import com.abhijeet14.tinteachers.fragments.HomeFragment;
import com.abhijeet14.tinteachers.fragments.NoticeBoardFragment;
import com.abhijeet14.tinteachers.fragments.ProfileFragment;
import com.abhijeet14.tinteachers.fragments.StudentsFragment;
import com.abhijeet14.tinteachers.fragments.SyllabusFragment;
import com.abhijeet14.tinteachers.fragments.TeachersFragment;
import com.abhijeet14.tinteachers.fragments.TimeTableFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment f = null;
    String tag = "others";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new HomeFragment(),"home");
        ft.commit();

        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().findFragmentById(R.id.container).getTag().equals("home")){
                super.onBackPressed();
            }else{
                f = new HomeFragment();
                tag = "home";
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,f,tag);
                ft.commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        tag="other";
        if (id == R.id.nav_home) {
            f = new HomeFragment();
            tag = "home";
        } else if (id == R.id.nav_profile) {
            f = new ProfileFragment();
        } else if(id == R.id.nav_attendance){
            f = new AttendanceFragment();
        } else if(id == R.id.nav_noticeBoard){
            f = new NoticeBoardFragment();
        } else if (id == R.id.nav_students){
            f = new StudentsFragment();
        } else if (id == R.id.nav_time_table){
            f = new TimeTableFragment();
        } else if (id == R.id.nav_syllabus){
            f = new SyllabusFragment();
        } else if (id == R.id.nav_about){
            f = new AboutFragment();
        } else if (id == R.id.nav_help){
            f = new HelpFragment();
        } else if (id == R.id.nav_teachers){
            f = new TeachersFragment();
        }
        ft.replace(R.id.container, f, tag);
        ft.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
