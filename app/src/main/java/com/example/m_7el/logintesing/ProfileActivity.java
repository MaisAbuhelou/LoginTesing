package com.example.m_7el.logintesing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.m_7el.logintesing.di.MyApp;
import com.example.m_7el.logintesing.modules.MySharedPreferences;

import javax.inject.Inject;

public class ProfileActivity extends AppCompatActivity {
    @Inject
    MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ((MyApp) getApplicationContext()).getMyComponent().inject(this);

        TextView userName = findViewById(R.id.user_name);
        userName.setText(mySharedPreferences.getData("LoginInfo"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();


                break;
            default:
                break;
        }
        return true;
    }

    private void logout() {
        mySharedPreferences.ClearData();
        Toast.makeText(this, R.string.logout_message, Toast.LENGTH_LONG).show();
        Intent intent=new Intent(ProfileActivity.this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}