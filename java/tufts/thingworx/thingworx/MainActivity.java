package tufts.thingworx.thingworx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import thingworx.Thingworx;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pushValue(View v){

        EditText property = findViewById(R.id.property);
        TextView result = findViewById(R.id.result);
        EditText value = findViewById(R.id.value);
        Thingworx tw = new Thingworx("CEEO_Summer", this, result);
        tw.addProperty(property.getText().toString(), value.getText().toString());
    }

    public void getValue(View v) throws ExecutionException, InterruptedException {
        EditText property = findViewById(R.id.property);
        TextView result = findViewById(R.id.result);
        Thingworx tw = new Thingworx("CEEO_Summer", this, result);
        tw.getProperties(property.getText().toString());
    }

    public void putValue(View v){
        EditText property = findViewById(R.id.property);
        EditText value = findViewById(R.id.value);
        TextView result = findViewById(R.id.result);
        Thingworx tw = new Thingworx("CEEO_Summer", this, result);
        tw.updateProperty(property.getText().toString(), value.getText().toString());
    }
}


