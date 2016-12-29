package com.example.artyte.sendtorpb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Artyte on 2016/12/18.
 */
public class DialogPop extends DialogFragment {

    static DialogPop newInstance(int i, boolean id, int[] rgb) {
        DialogPop f = new DialogPop();

        Bundle args = new Bundle();
        args.putInt("hertz", i);
        args.putBoolean("id", id);
        args.putInt("red",rgb[0]);
        args.putInt("blue",rgb[1]);
        args.putInt("green",rgb[2]);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Data frame sent is [hertz[0] rgb[0], rgb[1], rgb[2]]
        if(getArguments().getBoolean("id")) {
            builder.setMessage("Confirm On with: " + String.valueOf(getArguments().getInt("hertz")) + "Hz?")
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            int frame = (getArguments().getInt("hertz") % 10) * 1000 +
                                        (getArguments().getInt("red")) * 100 +
                                        (getArguments().getInt("blue")) * 10 +
                                        (getArguments().getInt("green"));
                            Client myClient = new Client("123", 6066, frame);
                            myClient.execute();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
        }
        else {
            builder.setMessage("Confirm Off?")
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Client myClient = new Client("123", 6066, 0000);
                            myClient.execute();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
        }
        return builder.create();
    }

}
