package com.jaehyun.jhpaint;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BaseActivity extends AppCompatActivity {
    private boolean drawYn = false;

    public void callDrawFragment(View view) {
        if( !drawYn ){
            FragmentManager fm = getSupportFragmentManager();
            PaintFragment paintFragment = PaintFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("buttonId", view.getId());
            paintFragment.setArguments(bundle);
            fm.beginTransaction().addToBackStack("drawFragment").add(android.R.id.content,paintFragment).commit();
            Log.d("test","callDrawFragment");
            drawYn = true;
            view.setVisibility(View.GONE);
        }
    }

    public void closeDrawFragment(View view) {
        if( drawYn ) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment paintFragment = PaintFragment.newInstance();
            fm.beginTransaction().remove(paintFragment).commit();
            drawYn = false;
            view.setVisibility(View.VISIBLE);
        }
    }
}
