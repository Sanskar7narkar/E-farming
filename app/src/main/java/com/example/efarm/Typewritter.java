package com.example.efarm;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.os.Handler;
import androidx.annotation.Nullable;

public class Typewritter extends androidx.appcompat.widget.AppCompatTextView {
    private CharSequence Mytext;
    private int myindex;
    private long mydelay=100;

    public Typewritter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler myhandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
        setText(Mytext.subSequence(0,myindex++));
        if(myindex<=Mytext.length()) {
            myhandler.postDelayed(characterAdder, mydelay);
        }
        }
    };
   public void animateText(CharSequence myTxt){
       Mytext = myTxt;

       myindex=0;

       setText("");
       myhandler.removeCallbacks(characterAdder);
       myhandler.postDelayed(characterAdder,mydelay);
   }

    public void setCharacterDelay(long n){
       mydelay = n;
    }
}
