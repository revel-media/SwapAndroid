package itboom.com.swap.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class SwapEditText extends EditText  {

    public SwapEditText(Context context) {
        super(context);
        setUpView(context);
    }

    public SwapEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView(context);
    }

    public SwapEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context);
    }

    private void setUpView(Context context){
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo-regular.ttf"));
    }
}
