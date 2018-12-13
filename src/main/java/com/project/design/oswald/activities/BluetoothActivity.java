

package com.project.design.oswald.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.design.oswald.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity extends Activity
{
    TextView myLabel;
    EditText myTextbox;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;

    InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    int counter;
    volatile boolean stopWorker;
    public final String email = getIntent().getStringExtra("EMAIL");
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ImageButton alphabets = findViewById(R.id.alphabets);
        ImageButton colors = findViewById(R.id.colors);
        ImageButton numbers = findViewById(R.id.numbers);
        ImageButton sentences = findViewById(R.id.sentences);
        ImageButton words = findViewById(R.id.words);
        ImageButton poems = findViewById(R.id.poems);
        ImageButton manners = findViewById(R.id.manners);
        ImageButton specialedition = findViewById(R.id.specialedition);
       // myLabel = (TextView)findViewById(R.id.label);
       // myTextbox = (EditText)findViewById(R.id.entry);
        findBT();
        try {
            openBT();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void findBT()
    {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null)
        {
            myLabel.setText("No bluetooth adapter available");
        }

        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName().equals("HC-05"))
                {
                    mmDevice = device;
                    break;
                }
            }
        }
        myLabel.setText("Bluetooth Device Found");
    }

    void openBT() throws IOException
    {
        final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standard SerialPortService ID
        mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
        mmSocket.connect();

        mmInputStream = mmSocket.getInputStream();

        beginListenForData();

        myLabel.setText("Bluetooth Opened");
    }

    void beginListenForData()
    {
        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        int bytesAvailable = mmInputStream.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                            myLabel.setText(data);
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                        String dataGot = readBuffer.toString();
                        if (dataGot == "1") {
                            Intent i  = new Intent(BluetoothActivity.this,AlphabetActivity.class);
                            i.putExtra("EMAIL",email);
                            startActivity(i);
                        }
                        else if(dataGot == "2"){
                            Intent i  = new Intent(BluetoothActivity.this,ColorsActivity.class);
                            i.putExtra("EMAIL",email);
                            startActivity(i);
                        }
                        else if(dataGot == "3"){
                            Intent i  = new Intent(BluetoothActivity.this,NumbersActivity.class);
                            i.putExtra("EMAIL",email);
                            startActivity(i);
                        }
                        else if(dataGot == "4"){
                            startActivity(new Intent(BluetoothActivity.this,MannersActivity.class));
                            Intent i  = new Intent(BluetoothActivity.this,MannersActivity.class);
                            i.putExtra("EMAIL",email);
                            startActivity(i);
                        }
                        else if(dataGot == "5"){
                            Intent i  = new Intent(BluetoothActivity.this,SentenceActivity.class);
                            i.putExtra("EMAIL",email);
                            startActivity(i);
                        }
                        else if(dataGot == "6"){
                            Intent i  = new Intent(BluetoothActivity.this,PoemsActivity.class);
                            i.putExtra("EMAIL",email);
                            startActivity(i);
                        }
                        else if(dataGot == "7"){
                            Intent i  = new Intent(BluetoothActivity.this,WordsActivity.class);
                            i.putExtra("EMAIL",email);
                            startActivity(i);
                        }
                        else if(dataGot == "8"){
                            Intent i  = new Intent(BluetoothActivity.this,SpecialActivity.class);
                            i.putExtra("EMAIL",email);
                            startActivity(i);

                        }

                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }

    void sendData() throws IOException
    {
        String msg = myTextbox.getText().toString();
        msg += "\n";

        myLabel.setText("Data Sent");
    }

    void closeBT() throws IOException
    {
        stopWorker = true;

        mmInputStream.close();
        mmSocket.close();
        myLabel.setText("Bluetooth Closed");
    }
}
