package com.example.artyte.sendtorpb;

/**
 * Created by Artyte on 2016/12/29.
 */

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.AsyncTask;

public class Client extends AsyncTask<Void, Void, Void> {

    private String dstAddress;
    private int dstPort;
    private int dstFrame;
    private String response;

    Client(String addr, int port, int frame) {
        dstAddress = addr;
        dstPort = port;
        dstFrame = frame;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            OutputStream outToServer = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF(Integer.toString(dstFrame));

        } catch (UnknownHostException e) {
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}