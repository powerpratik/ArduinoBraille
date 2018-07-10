package com.example.pratik.arduinobraille;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;



import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = "bluetooth2";

    Button btnOn, btnOff;
    TextView txtArduino;
    Handler h;

    final int RECIEVE_MESSAGE = 1;		// Status  for Handler
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder sb = new StringBuilder();

    private ConnectedThread mConnectedThread;

    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // MAC-address of Bluetooth module (you must edit this line)
    private static String address = "20:16:08:01:87:58";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnOn = (Button) findViewById(R.id.button1);					// button LED ON
        btnOff = (Button) findViewById(R.id.button2);				// button LED OFF
        txtArduino = (TextView) findViewById(R.id.textview);		// for display the received data from the Arduino

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case RECIEVE_MESSAGE:													// if receive massage
                        byte[] readBuf = (byte[]) msg.obj;
                        String strIncom = new String(readBuf, 0, msg.arg1);					// create string from bytes array
                        sb.append(strIncom);												// append string
                        int endOfLineIndex = sb.indexOf("\r\n");							// determine the end-of-line
                        if (endOfLineIndex > 0) { 											// if end-of-line,
                            String sbprint = sb.substring(0, endOfLineIndex);				// extract string
                            sb.delete(0, sb.length());										// and clear
                            txtArduino.setText("Data from Arduino: " + sbprint); 	        // update TextView
                            playsound(sbprint);
                            btnOff.setEnabled(true);
                            btnOn.setEnabled(true);
                        }
                        //Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
                        break;
                }
            };
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter();		// get Bluetooth adapter
        checkBTState();

        btnOn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                btnOn.setEnabled(false);
                mConnectedThread.write("1");	// Send "1" via Bluetooth
                //Toast.makeText(getBaseContext(), "Turn on LED", Toast.LENGTH_SHORT).show();
            }
        });

        btnOff.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                btnOff.setEnabled(false);
                mConnectedThread.write("0");	// Send "0" via Bluetooth
                //Toast.makeText(getBaseContext(), "Turn off LED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void playsound(String sbprint) {

        int value= Integer.parseInt(sbprint);
        switch(value) {
            case 2:
                //  playplay(R.raw.a_2);
                final MediaPlayer a_2 = MediaPlayer.create(this, R.raw.a_2);
                a_2.start();
                break;
            case 231:
                final MediaPlayer a_231 = MediaPlayer.create(this, R.raw.a_231);
                a_231.start();
                break;
            case 15:
                final MediaPlayer a_15 = MediaPlayer.create(this, R.raw.a_15);
                a_15.start();
                break;
            case 77:
                final MediaPlayer a_77 = MediaPlayer.create(this, R.raw.a_77);
                a_77.start();
                break;
            case 286:
                final MediaPlayer a_286 = MediaPlayer.create(this, R.raw.a_286);
                a_286.start();
                break;
            case 910:
                final MediaPlayer a_910 = MediaPlayer.create(this, R.raw.a_910);
                a_910.start();
                break;

            case 14:
                final MediaPlayer a_14 = MediaPlayer.create(this, R.raw.a_14);
                a_14.start();
                break;
            case 33:
                final MediaPlayer a_33 = MediaPlayer.create(this, R.raw.a_33);
                a_33.start();
                break;
            case 154:
                final MediaPlayer a_154 = MediaPlayer.create(this, R.raw.a_154);
                a_154.start();
                break;
            case 195:
                final MediaPlayer a_195 = MediaPlayer.create(this, R.raw.a_195);
                a_195.start();
                break;
            case 22:
                final MediaPlayer a_22 = MediaPlayer.create(this, R.raw.a_22);
                a_22.start();
                break;
            case 39:
                final MediaPlayer a_39 = MediaPlayer.create(this, R.raw.a_39);
                a_39.start();
                break;
            case 210:
                final MediaPlayer a_210 = MediaPlayer.create(this, R.raw.a_210);
                a_210.start();
                break;
            case 130:
                final MediaPlayer a_130 = MediaPlayer.create(this, R.raw.a_130);
                a_130.start();
                break;
            case 429:
                final MediaPlayer a_429 = MediaPlayer.create(this, R.raw.a_429);
                a_429.start();
                break;
            case 6:
                final MediaPlayer a_6 = MediaPlayer.create(this, R.raw.a_6);
                a_6.start();
                break;
            case 26:
                final MediaPlayer a_26 = MediaPlayer.create(this, R.raw.a_26);
                a_26.start();
                break;

            case 105:
                final MediaPlayer a_105 = MediaPlayer.create(this, R.raw.a_105);
                a_105.start();
                break;
            case 1001:
                final MediaPlayer a_1001 = MediaPlayer.create(this, R.raw.a_1001);
                a_1001.start();
                break;
            case 35:
                final MediaPlayer a_35 = MediaPlayer.create(this, R.raw.a_35);
                a_35.start();
                break;
            case 15015:
                final MediaPlayer a_15015 = MediaPlayer.create(this, R.raw.a_15015);
                a_15015.start();
                break;
            case 1365:
                final MediaPlayer a_1365 = MediaPlayer.create(this, R.raw.a_1365);
                a_1365.start();
                break;
            case 390:
                final MediaPlayer a_390 = MediaPlayer.create(this, R.raw.a_390);
                a_390.start();
                break;
            case 30020:
                final MediaPlayer a_30020 = MediaPlayer.create(this, R.raw.a_30020);
                a_30020.start();
                break;
            case 1155:
                final MediaPlayer a_1155 = MediaPlayer.create(this, R.raw.a_1155);
                a_1155.start();
                break;
            case 546:
                final MediaPlayer a_546 = MediaPlayer.create(this, R.raw.a_546);
                a_546.start();
                break;
            case 42:
                final MediaPlayer a_42 = MediaPlayer.create(this, R.raw.a_42);
                a_42.start();
                break;
            case 2145:
                final MediaPlayer a_2145 = MediaPlayer.create(this, R.raw.a_2145);
                a_2145.start();
                break;

            case 462:
                final MediaPlayer a_462 = MediaPlayer.create(this, R.raw.a_462);
                a_462.start();
                break;
            case 330:
                final MediaPlayer a_330 = MediaPlayer.create(this, R.raw.a_330);
                a_330.start();
                break;
            case 385:
                final MediaPlayer a_385 = MediaPlayer.create(this, R.raw.a_154);
                a_385.start();
                break;
            case 10:
                final MediaPlayer a_10 = MediaPlayer.create(this, R.raw.a_10);
                a_10.start();
                break;
            case 21:
                final MediaPlayer a_21 = MediaPlayer.create(this, R.raw.a_21);
                a_21.start();
                break;
            case 66:
                final MediaPlayer a_66 = MediaPlayer.create(this, R.raw.a_66);
                a_66.start();
                break;
            case 6006:
                final MediaPlayer a_6006 = MediaPlayer.create(this, R.raw.a_6006);
                a_6006.start();
                break;
            case 770:
                final MediaPlayer a_770 = MediaPlayer.create(this, R.raw.a_770);
                a_770.start();
                break;
            case 110:
                final MediaPlayer a_110 = MediaPlayer.create(this, R.raw.a_110);
                a_110.start();
                break;
            case 1430:
                final MediaPlayer a_1430 = MediaPlayer.create(this, R.raw.a_1430);
                a_1430.start();
                break;
            case 78:
                final MediaPlayer a_78 = MediaPlayer.create(this, R.raw.a_78);
                a_78.start();
                break;

            case 4290:
                final MediaPlayer a_4290 = MediaPlayer.create(this, R.raw.a_4290);
                a_4290.start();
                break;
            case 165:
                final MediaPlayer a_165 = MediaPlayer.create(this, R.raw.a_165);
                a_165.start();
                break;
            case 70:
                final MediaPlayer a_70 = MediaPlayer.create(this, R.raw.a_70);
                a_70.start();
                break;
            case 2310:
                final MediaPlayer a_2310 = MediaPlayer.create(this, R.raw.a_2310);
                a_2310.start();
                break;
            case 182:
                final MediaPlayer a_182 = MediaPlayer.create(this, R.raw.a_182);
                a_182.start();
                break;
            case 000:
                final MediaPlayer notrans= MediaPlayer.create(this,R.raw.notranslation);
                notrans.start();

        }
    }



    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if(Build.VERSION.SDK_INT >= 10){
            try {
                final Method  m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
                return (BluetoothSocket) m.invoke(device, MY_UUID);
            } catch (Exception e) {
                Log.e(TAG, "Could not create Insecure RFComm Connection",e);
            }
        }
        return  device.createRfcommSocketToServiceRecord(MY_UUID);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "...onResume - try connect...");

        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

    /*try {
      btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
    } catch (IOException e) {
      errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
    }*/

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        Log.d(TAG, "...Connecting...");
        try {
            btSocket.connect();
            Log.d(TAG, "....Connection ok...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        // Create a data stream so we can talk to server.
        Log.d(TAG, "...Create Socket...");

        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "...In onPause()...");

        try     {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // Emulator doesn't support Bluetooth and will return null
        if(btAdapter==null) {
            errorExit("Fatal Error", "Bluetooth not support");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private void errorExit(String title, String message){
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);		// Get number of bytes and message in "buffer"
                    h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();		// Send to message queue Handler
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String message) {
            Log.d(TAG, "...Data to send: " + message + "...");
            byte[] msgBuffer = message.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) {
                Log.d(TAG, "...Error data send: " + e.getMessage() + "...");
            }
        }
    }
}