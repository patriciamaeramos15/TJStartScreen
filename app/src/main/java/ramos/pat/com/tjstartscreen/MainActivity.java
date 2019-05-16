package ramos.pat.com.tjstartscreen;

import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    Button obconbutton;
//    private LottieAnimationView LottieECalendar;

    private TextView[] mDots;

    private  SliderAdapter sliderAdapter;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////        alertDialog=new AlertDialog.Builder(MainActivity.this).create();
////        alertDialog.setTitle("Hello");
////        alertDialog.setMessage("This is an alert dialog");
////        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Show", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                Toast.makeText(MainActivity.this, "Show Button Pressed",Toast.LENGTH_LONG).show();
////                dialog.dismiss();
////            }
//        });

        obconbutton = findViewById(R.id.obcontbutton);
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);


        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);


    }

    public void contAnim(View v)
    {
//        alertDialog.show();
        onfirst();
    }
    public void onfirst()
    {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if(isFirstRun) {
            //place your dialog code here to dislay the dialog
            LayoutInflater inflater= LayoutInflater.from(this);
            View view=inflater.inflate(R.layout.privacypolicy, null);
            TextView textview=(TextView)view.findViewById(R.id.privacy);
            textview.setText(R.string.privacy);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("        P r i v a c y       P o l i c y")
//                    .setMessage(R.string.privacy)
                    .setView(view)
                    .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    })
                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                                    .edit()
                                    .putBoolean("isFirstRun", false)
                                    .apply(); //
                        }
                    }).show();


        }
    }




    public void addDotsIndicator(int position){

        mDotLayout.removeAllViews();

        mDots = new TextView[5];

        for(int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));


            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0) {

            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));

        }






    }

//    private void loadLastScreen(){
//        obconbutton.setVisibility(View.VISIBLE);
//
//    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }


    };

    public void onClick(View v) {

        

    }
}
