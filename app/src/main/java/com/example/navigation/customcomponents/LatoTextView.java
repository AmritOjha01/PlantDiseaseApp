package com.example.navigation.customcomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.example.navigation.R;

;

/**
 * Created by CSharp05-user on 07/06/2017.
 */

public class LatoTextView extends android.support.v7.widget.AppCompatTextView {

    private int textStyle;
    private Context context;

    static Typeface thin, light, medium, regular, bold, italic, lightItalic, mediumItalic, boldItalic, thinItalic, black;

    public LatoTextView(Context context) {
        super(context);
        this.context = context;
    }

    public LatoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public LatoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attributeset) {
        initDefaultAttributes(attributeset);
        initCustomAttributes(attributeset);

    }

    private void initDefaultAttributes(AttributeSet attributeset) {
        textStyle = 1;
    }

    private void initCustomAttributes(AttributeSet attributeset) {
        TypedArray attributes =
                getContext().obtainStyledAttributes(attributeset, R.styleable.text_view);
        textStyle = attributes.getInt(R.styleable.text_view_textStyle1, 1);
        String fontPath = "";
        switch (textStyle) {
            case 0:
                if (thin != null) {
                    this.setTypeface(thin);
                } else {
                    thin = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Thin.ttf");
                    this.setTypeface(thin);
                }
                //fontPath = "fonts/Lato/Lato-Thin.ttf";
                break;
            case 1:
                if (light != null) {
                    this.setTypeface(light);
                } else {
                    light = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Light.ttf");
                    this.setTypeface(light);
                }
                //fontPath = "fonts/Lato/Lato-Light.ttf";
                break;
            case 2:
                if (medium != null) {
                    this.setTypeface(medium);
                } else {
                    medium = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Medium.ttf");
                    this.setTypeface(medium);
                }
                //fontPath = "fonts/Lato/Lato-Medium.ttf";
                break;
            case 3:
                if (regular != null) {
                    this.setTypeface(regular);
                } else {
                    regular = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Regular.ttf");
                    this.setTypeface(regular);
                }
                //fontPath = "fonts/Lato/Lato-Regular.ttf";
                break;
            case 4:
                if (bold != null) {
                    this.setTypeface(bold);
                } else {
                    bold = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Bold.ttf");
                    this.setTypeface(bold);
                }
                //fontPath = "fonts/Lato/Lato-Bold.ttf";
                break;
            case 5:
                if (italic != null) {
                    this.setTypeface(italic);
                } else {
                    italic = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Italic.ttf");
                    this.setTypeface(italic);
                }
                // fontPath = "fonts/Lato/Lato-Italic.ttf";
                break;
            case 6:
                if (lightItalic != null) {
                    this.setTypeface(lightItalic);
                } else {
                    lightItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-LightItalic.ttf");
                    this.setTypeface(lightItalic);
                }
                //fontPath = "fonts/Lato/Lato-LightItalic.ttf";
                break;
            case 7:
                if (mediumItalic != null) {
                    this.setTypeface(mediumItalic);
                } else {
                    mediumItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-MediumItalic.ttf");
                    this.setTypeface(mediumItalic);
                }
                //fontPath = "fonts/Lato/Lato-MediumItalic.ttf";
                break;
            case 8:
                if (boldItalic != null) {
                    this.setTypeface(boldItalic);
                } else {
                    boldItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-BoldItalic.ttf");
                    this.setTypeface(boldItalic);
                }
                //fontPath = "fonts/Lato/Lato-BoldItalic.ttf";
                break;
            case 9:
                if (thinItalic != null) {
                    this.setTypeface(thinItalic);
                } else {
                    thinItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-ThinItalic.ttf.ttf");
                    this.setTypeface(thinItalic);
                }
                //fontPath = "fonts/Lato/Lato-ThinItalic.ttf.ttf";
                break;
            case 10:
                if (black != null) {
                    this.setTypeface(black);
                } else {
                    black = Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Black.ttf");
                    this.setTypeface(black);
                }
                //fontPath = "fonts/Lato/Lato-Black.ttf";
                break;
        }
        //Typeface face = Typeface.createFromAsset(context.getAssets(), fontPath);
        //this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
