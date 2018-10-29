package itboom.com.swap.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class SwapTextView extends TextView {
    public SwapTextView(Context context) {
        super(context);

        setUpView(context);
    }

    public SwapTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setUpView(context);
    }

    public SwapTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setUpView(context);
    }

    private void setUpView(Context context){
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo-regular.ttf"));
    }
}
