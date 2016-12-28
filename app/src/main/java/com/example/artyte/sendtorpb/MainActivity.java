package com.example.artyte.sendtorpb;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void showDialog(View v) {
        EditText text = (EditText) findViewById(R.id.input_freq_blink);
        if(text.getText().toString().length() == 0) return;
        int i = Integer.parseInt(text.getText().toString());

        boolean id;
        if(v.getId() == R.id.on) id = true;
        else id = false;

        CheckBox[] rgb = new CheckBox[3];
        int[] numRGB = {0,0,0};
        rgb[0] = (CheckBox) findViewById(R.id.red);
        rgb[1] = (CheckBox) findViewById(R.id.blue);
        rgb[2] = (CheckBox) findViewById(R.id.green);

        if(rgb[0].isChecked()) numRGB[0]=1;
        if(rgb[1].isChecked()) numRGB[1]=1;
        if(rgb[2].isChecked()) numRGB[2]=1;

        DialogFragment newFragment = DialogPop.newInstance(i, id, numRGB);
        newFragment.show(getFragmentManager(), "dialog");
    }
}
