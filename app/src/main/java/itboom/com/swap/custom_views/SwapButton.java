package itboom.com.swap.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.google.android.material.button.MaterialButton;

public class SwapButton extends MaterialButton {
    public SwapButton(Context context) {
        super(context);

        setUpView(context);
    }

    public SwapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        setUpView(context);
    }

    public SwapButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setUpView(context);
    }

    private void setUpView(Context context){
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo-regular.ttf"));
    }
}
