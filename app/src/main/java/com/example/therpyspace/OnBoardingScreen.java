package com.example.therpyspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OnBoardingScreen extends AppCompatActivity  implements View.OnClickListener {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private TextView[] mDots;
    private SliderAdapter sliderAdapter;
    private Button buttonNext;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        mSlideViewPager = findViewById(R.id.slide_viewpager);
        mDotsLayout = findViewById(R.id.dots_layout);
        buttonNext = findViewById(R.id.btn_next);



        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        buttonNext.setOnClickListener(this);


    }

    public void addDotsIndicator(int position){

        mDots = new TextView[4];
        mDotsLayout.removeAllViews(); //without this multiple number of dots will be created

        for (int i = 0; i< mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;")); //code for the dot icon like thing
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.black));

            mDotsLayout.addView(mDots[i]);
        }

        if (mDots.length>0){
            mDots[position] .setTextColor(getResources().getColor(com.google.android.material.R.color.mtrl_chip_background_color));//setting currently selected dot to white
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);

            mCurrentPage = position;

            if (position == 0){//we are on first page
                buttonNext.setEnabled(true);
                buttonNext.setText("Next");
            } else if (position == mDots.length - 1){ //last page
                buttonNext.setEnabled(true);
                buttonNext.setText("Finish");
            } else { //neither on first nor on last page
                buttonNext.setEnabled(true);
                buttonNext.setText("Next");

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                if (buttonNext.getText().toString().equalsIgnoreCase("next")){
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                    Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getApplicationContext(), DesbordActivity.class));
                }
                break;

            default: break;
        }
    }
}