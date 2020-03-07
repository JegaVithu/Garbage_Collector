package com.example.garbagcollector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SCREEN_SPLASH = 5000;

    //variable
    Animation topanim, bottomanim;
    ImageView logo;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        logo = findViewById(R.id.appCompatImageView);
        title = findViewById(R.id.textView);

        //set animation
        logo.setAnimation(topanim);
        title.setAnimation(bottomanim);

        //delay screen change with time duration
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                Pair[] pair = new Pair[2];
                pair[0] = new Pair<View,String>(logo,"logo_image");
                pair[1] = new Pair<View,String>(title,"logo_title");

                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.LOLLIPOP)
                {ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pair);
                    startActivity(intent,options.toBundle());
                }

            }
        },SCREEN_SPLASH);
    }
}
