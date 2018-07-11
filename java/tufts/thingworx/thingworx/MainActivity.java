package tufts;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

import fi.iki.elonen.*;

import java.lang.reflect.*;
import java.util.*;

import thingworx.*;
import thingworx.tufts.*;

import thingworx.Properties;

import java.io.*;

public class WebServer extends AppCompatActivity {
    EditText propertyEditText;
    TextView resultTextView;
    EditText valueEditText;
    EditText getPropertyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webserver);
        propertyEditText = (EditText) findViewById(R.id.property);
        getPropertyEditText = (EditText) findViewById(R.id.getProperty);
        resultTextView = (TextView) findViewById(R.id.result);
        valueEditText = (EditText) findViewById(R.id.value);
    }

    public void addProperty(View v) {
        String property = propertyEditText.getText().toString();
        String value = valueEditText.getText().toString();
        Thingworx tw = new Thingworx("CEEO_Summer", this);
        tw.addProperty(property, value);
    }

    public void getValue(View v) {
        String property = propertyEditText.getText().toString();
        Thingworx tw = new Thingworx("CEEO_Summer", this);
        tw.getProperties(property, new CallbackInterface() {
            @Override
            public void onRequestFinish(Properties result) {
                resultTextView.setText(result.getValue());
            }
        });
    }

    public void getBaseType(View v) {
        String property = propertyEditText.getText().toString();
        Thingworx tw = new Thingworx("CEEO_Summer", this);
        tw.getProperties(property, new CallbackInterface() {
            @Override
            public void onRequestFinish(Properties result) {
                resultTextView.setText(result.getBaseType());
            }
        });
    }

    public void getDescription(View v) {
        String property = propertyEditText.getText().toString();
        Thingworx tw = new Thingworx("CEEO_Summer", this);
        tw.getProperties(property, new CallbackInterface() {
            @Override
            public void onRequestFinish(Properties result) {
                resultTextView.setText(result.getDescription());
            }
        });
    }

    public void updateProperty(View v) {
        String property = propertyEditText.getText().toString();
        String value = valueEditText.getText().toString();
        Thingworx tw = new Thingworx("CEEO_Summer", this);
        tw.updateProperty(property, value);
    }
}
