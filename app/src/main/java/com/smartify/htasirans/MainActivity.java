package com.smartify.htasirans;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    private static final String TAG = "LoginActivity";

    private EditText userName;
    private EditText password;
    private TextView tvSignUp;
    private TextInputLayout username;
    private TextInputLayout passWord;
    private Button login;
    private ImageView ivLogo;
    private ImageView ivStaticLogo;
    private Boolean ANIMATION_ENDED = false;
    private Boolean START_ANIMATION = true;
    boolean isOpened = false;
    private String inputUser;
    private String inputPass;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ivLogo = (ImageView) findViewById(R.id.ivLoginMain);
        //if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)

        username = (TextInputLayout) findViewById(R.id.inputMail);
        passWord = (TextInputLayout) findViewById(R.id.inputPassword);
        login = (Button) findViewById(R.id.connect);
        ivStaticLogo = (ImageView) findViewById(R.id.ivLogin);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);

        myDialog = new Dialog(this);

        if(START_ANIMATION) {
            //if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)

            username.setVisibility(View.GONE);
            passWord.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
            ivStaticLogo.setVisibility(View.GONE);
            tvSignUp.setVisibility(View.GONE);

            Animation moveFBLogoAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
            moveFBLogoAnimation.setFillAfter(true);
            moveFBLogoAnimation.setAnimationListener(this);
            ivLogo.startAnimation(moveFBLogoAnimation);
        }

        else {
            ivLogo.setVisibility(View.GONE);
        }


        final View activityRootView = findViewById(R.id.activity_login);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(ANIMATION_ENDED || !START_ANIMATION) {
                    int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                    if (heightDiff > dpToPx(MainActivity.this, 100)) {
                        //Soft keyboard is shown
                        //if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                        ivStaticLogo .setVisibility(View.GONE);

                    } else {
                        //Soft keyboard is hidden
                        //if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                        ivStaticLogo.setVisibility(View.VISIBLE);

                    }
                }
            }
        });


    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        ivStaticLogo.setVisibility(View.VISIBLE);
        ivLogo.clearAnimation();
        ivLogo.setVisibility(View.GONE);
        username.setAlpha(0f);
        username.setVisibility(View.VISIBLE);
        passWord.setAlpha(0f);
        passWord.setVisibility(View.VISIBLE);
        login.setAlpha(0f);
        login.setVisibility(View.VISIBLE);
        tvSignUp.setAlpha(0f);
        tvSignUp.setVisibility(View.VISIBLE);

        int mediumAnimationTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);

        username.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        passWord.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        login.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        tvSignUp.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        ANIMATION_ENDED = true;

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void showChoice(View view) {


        TextView txtclose;
        Button agent;
        myDialog.setContentView(R.layout.popupchoice);
        //txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        //txtclose.setText("M");
        agent = (Button) myDialog.findViewById(R.id.agent);
        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public void toMain(View view) {

        Intent i = new Intent(MainActivity.this,MainAgent.class);
        startActivity(i);
    }
}
