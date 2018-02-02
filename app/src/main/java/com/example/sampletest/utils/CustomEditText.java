package com.example.sampletest.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.example.sampletest.R;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class CustomEditText extends RelativeLayout {

    RelativeLayout editTextContainingLayout;
    RelativeLayout mainLayout;
    EditText editText;
    TextView prefilledText, errorTextTv;
    ToggleButton editTextToggle;
    Context context;

    public CustomEditText(Context context) {
        super(context, null);
        this.context = context;
    }

    public CustomEditText(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomEditTextStyle, 0, 0);
        String hintText = a.getString(R.styleable.CustomEditTextStyle_hintText);
//        String errorText="";
//        errorText = a.getString(R.styleable.CustomEditTextStyle_errorText);
        int imeOptions = a.getInt(R.styleable.CustomEditTextStyle_imeOptions, 0);
        int editTextType = a.getInt(R.styleable.CustomEditTextStyle_type, 0);
        int length = a.getInt(R.styleable.CustomEditTextStyle_length, 60);
        int errorTextColor = a.getInt(R.styleable.CustomEditTextStyle_errorTextColor, 0);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_password_edittext, this);

        editTextContainingLayout = (RelativeLayout) view.findViewById(R.id.editText_containing_layout);
        editText = (EditText) view.findViewById(R.id.editText);
        editTextToggle = (ToggleButton) view.findViewById(R.id.editText_toggle);
        prefilledText = (TextView) view.findViewById(R.id.mobile_number_pre_filled_text);
        errorTextTv = (TextView) view.findViewById(R.id.editTextError);
        mainLayout = (RelativeLayout) view.findViewById(R.id.mainLayout);

        editText.setHint(hintText);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});

        switch (imeOptions) {
            case 0:
                editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                break;

            case 1:
                editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                break;

            case 2:
                editText.setImeOptions(EditorInfo.IME_ACTION_GO);
                break;

        }

        switch (editTextType) {
            case 0:
//                Its a password
                prefilledText.setVisibility(GONE);
                editTextToggle.setVisibility(VISIBLE);
                editText.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;

            case 1:
//                Its a Mobile Edittext
                editTextToggle.setVisibility(GONE);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER |
                        InputType.TYPE_NUMBER_FLAG_DECIMAL);
                editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                prefilledText.setVisibility(VISIBLE);
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(length);
                editText.setFilters(new InputFilter[]{
                        new InputFilter() {
                            public CharSequence filter(CharSequence src, int start,
                                                       int end, Spanned dst, int dstart, int dend) {
                                if (src.equals("")) {
                                    return src;
                                }
                                if (src.toString().matches("[0-9]+") && dst.length() < 10) {
                                    return src;
                                }
                                return "";
                            }
                        }
                });
                break;
            case 2:
//                Its a Normal Edittext
                prefilledText.setVisibility(GONE);
                editTextToggle.setVisibility(GONE);
                editText.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                break;
            case 3:
//                Its a Email Edittext
                prefilledText.setVisibility(GONE);
                editTextToggle.setVisibility(GONE);
                editText.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setFilters(new InputFilter[]{
                        new InputFilter() {
                            public CharSequence filter(CharSequence src, int start,
                                                       int end, Spanned dst, int dstart, int dend) {
                                if (src.equals("")) {
                                    return src;
                                }
                                if (src.toString().matches("[a-zA-Z@_.0-9]+")) {
                                    return src;
                                }
                                return "";
                            }
                        }
                });
                break;
            case 4:
//                Its a Number Edittext
                editTextToggle.setVisibility(GONE);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER |
                        InputType.TYPE_NUMBER_FLAG_DECIMAL);
                editText.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                prefilledText.setVisibility(GONE);
                break;

            case 5:
//                Its a Text Edittext
                editTextToggle.setVisibility(GONE);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                LayoutParams lparams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 315); // Width , height or (LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                editText.setLayoutParams(lparams);
                mainLayout.setLayoutParams(lparams);
                editTextContainingLayout.setLayoutParams(lparams);
                prefilledText.setVisibility(GONE);
                break;

        }


        editTextToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                editText.setSelection(editText.getText().length());
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                errorTextTv.setVisibility(INVISIBLE);
                mainLayout.setBackground(null);
            }
        });


    }


    public void hideView() {
        editTextContainingLayout.setVisibility(GONE);
    }

    public void showView() {
        editTextContainingLayout.setVisibility(VISIBLE);
    }

    public void setHintInEdittext(String hintInEdittext) {
        editText.setHint(hintInEdittext);
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setEnable(boolean value) {
        editText.setEnabled(value);
        editTextToggle.setEnabled(value);
    }

    public void setErrorMsg(String errorMessage) {
        errorTextTv.setVisibility(VISIBLE);
        errorTextTv.setText(errorMessage);
        mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.error_edittext_background));
        editText.requestFocus();
    }

    public void removeErrorMsg() {
        errorTextTv.setText("");
        errorTextTv.setVisibility(INVISIBLE);
        mainLayout.setBackground(null);
    }

    public int getInputType() {
        return editText.getInputType();
    }

    public void setInputType(int typeNull) {
        editText.setInputType(typeNull);
    }

    public EditText getEditText() {
        return editText;
    }

}