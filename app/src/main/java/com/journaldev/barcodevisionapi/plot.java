package com.journaldev.barcodevisionapi;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ufobeaconsdk.callback.OnFailureListener;
import com.ufobeaconsdk.callback.OnScanSuccessListener;
import com.ufobeaconsdk.callback.OnSuccessListener;
import com.ufobeaconsdk.main.UFOBeaconManager;
import com.ufobeaconsdk.main.UFODevice;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class plot extends AppCompatActivity implements SensorEventListener {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    public BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    public Button startexplore, stopexplore, prev, next, read;
    public UFOBeaconManager ufoBeaconManager;
    public String lastBeacon="akshay";
    public TextToSpeech textToSpeech;
    Bitmap myBitmap;
    Canvas tempCanvas;
    Paint myPaint1, myPaint2, myPaint3;
    List<String> categories;
    String value = "";
    String nextway = "";
    String prevway = "";
    String val[];
    String nextie[];
    String previe[];
    String vale[];
    public double d1, d11;
    public double d2, d12;
    public double d3, d13;
    public double d4, d14;
    public double d5, d15;
    public double d6, d16;
    public double d7,d17;
    public double a, b;
    public String kuku;
    SensorManager sensorManager;
    TextView display, textexplore;
    float[] mGravity;
    float[] mGeomagnetic;
    double positions[][] = {{7.3, 16.93}, {19.5, 16.93}, {13.4, 23.5}, {9.32, 9.7}, {18.26, 9.7},{3.32,9.7},{20.65,9.7}};
    double distances[] = new double[7];
    double positions1[][]= new double[3][2];
    double distances1[]= new double[3];
    String proximitybeacon;
    int c1 = 0;
    int c2 = 0;
    int c3 = 0;
    int c4 = 0;
    int c5 = 0;
    int c6 = 0;
    int c7=0;
    int p1 = 0;
    ImageView map;
    HashMap<String, Double> budget = new HashMap<>();
    TextView inspect;
    HashMap<String , String>  beacons = new HashMap<String,String>();

    public final String beacon1 = "55:46:4F:D2:6A:CF";
    public final String beacon2 = "55:46:4F:D2:6A:DD";
    public final String beacon3 = "55:46:4F:11:87:50";
    public final String beacon4 = "55:46:4F:11:88:22";
    public final String beacon5 = "55:46:4F:11:87:13";
    public final String beacon6 = "55:46:4F:11:88:59";
    public final String beacon7 = "55:46:4F:11:88:1B";

    private void explor() {
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magnometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(plot.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(plot.this, magnometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String string = bundle.getString("myKey1");
            String[] str = string.split(",");
            if (str[0].equals(beacon1)) {
                d1 = Double.parseDouble(str[1]);
                if (d1 != 0) {
                    c1++;
                    d11 = d11 + d1;
                }
                if(c1>2 && d11/c1<1)
                {
                    //Toast.makeText(plot.this, "You can now use Explore Feature!", Toast.LENGTH_SHORT).show();
                    //alert();
                    display.setText("Beacon 1");
                    c1=0;
                    d11=0;
                    d11 = 0;
                    d12 = 0;
                    d13 = 0;
                    c1 = 0;
                    c2 = 0;
                    c3 = 0;
                    d14 = 0;
                    d15 = 0;
                    d16 = 0;
                    d17=0;
                    c4 = 0;
                    c5 = 0;
                    c6 = 0;
                    c7=0;

                }
            }
            else if (str[0].equals(beacon2)) {
                d2 = Double.parseDouble(str[1]);
                if (d2 != 0) {
                    c2++;
                    d12 = d12 + d2;
                    c1=0;
                    d11=0;
                }
                if(c2>2 && d12/c2<1)
                {
                    display.setText("Beacon 2");
                    c2=0;
                    d12=0;
                    d11 = 0;
                    d12 = 0;
                    d13 = 0;
                    c1 = 0;
                    c2 = 0;
                    c3 = 0;
                    d14 = 0;
                    d15 = 0;
                    d16 = 0;
                    d17=0;
                    c4 = 0;
                    c5 = 0;
                    c6 = 0;
                    c7=0;
                }
            }
            else if (str[0].equals(beacon3)) {
                d3 = Double.parseDouble(str[1]);
                if (d3 != 0) {
                    c3++;
                    d13 = d13 + d3;
                }
                if(c3>2 && d13/c3<1)
                {
                    //Toast.makeText(plot.this, "You can now use Explore Feature!", Toast.LENGTH_SHORT).show();
                    //alert();
                    display.setText("Beacon 3");
                    c3=0;
                    d13=0;
                    d11 = 0;
                    d12 = 0;
                    d13 = 0;
                    c1 = 0;
                    c2 = 0;
                    c3 = 0;
                    d14 = 0;
                    d15 = 0;
                    d16 = 0;
                    d17=0;
                    c4 = 0;
                    c5 = 0;
                    c6 = 0;
                    c7=0;
                }
            }
            else if (str[0].equals(beacon4)) {
                d4 = Double.parseDouble(str[1]);
                if (d4 != 0) {
                    c4++;
                    d14 = d14 + d4;
                }
                if(c4>2 && d14/c4<1)
                {
                    //Toast.makeText(plot.this, "You can now use Explore Feature!", Toast.LENGTH_SHORT).show();
                    //alert();
                    display.setText("Beacon 4");
                    c4=0;
                    d14=0;
                    d11 = 0;
                    d12 = 0;
                    d13 = 0;
                    c1 = 0;
                    c2 = 0;
                    c3 = 0;
                    d14 = 0;
                    d15 = 0;
                    d16 = 0;
                    d17=0;
                    c4 = 0;
                    c5 = 0;
                    c6 = 0;
                    c7=0;
                }
            }
            else if (str[0].equals(beacon5)) {
                d5 = Double.parseDouble(str[1]);
                if (d5 != 0) {
                    c5++;
                    d15 = d15 + d5;
                }
                if(c5>2 && d15/c5<1)
                {
                    //Toast.makeText(plot.this, "You can now use Explore Feature!", Toast.LENGTH_SHORT).show();
                    //alert();
                    display.setText("Beacon 5");
                    c5=0;
                    d15=0;
                    d11 = 0;
                    d12 = 0;
                    d13 = 0;
                    c1 = 0;
                    c2 = 0;
                    c3 = 0;
                    d14 = 0;
                    d15 = 0;
                    d16 = 0;
                    d17=0;
                    c4 = 0;
                    c5 = 0;
                    c6 = 0;
                    c7=0;
                }
            }
            else if (str[0].equals(beacon6)) {
                d6 = Double.parseDouble(str[1]);
                if (d6 != 0) {
                    c6++;
                    d16 = d16 + d6;
                }
                if(c6>2 && d16/c6<1)
                {
                    //Toast.makeText(plot.this, "You can now use Explore Feature!", Toast.LENGTH_SHORT).show();
                    //alert();
                    display.setText("Beacon 6");
                    c6=0;
                    d16=0;
                    d11 = 0;
                    d12 = 0;
                    d13 = 0;
                    c1 = 0;
                    c2 = 0;
                    c3 = 0;
                    d14 = 0;
                    d15 = 0;
                    d16 = 0;
                    d17=0;
                    c4 = 0;
                    c5 = 0;
                    c6 = 0;
                    c7=0;
                }
            }
            else if (str[0].equals(beacon7)) {
                d7 = Double.parseDouble(str[1]);
                if (d7 != 0) {
                    c7++;
                    d17 = d17 + d7;
                }
                if(c7>2 && d17/c7<1)
                {
                    //Toast.makeText(plot.this, "You can now use Explore Feature!", Toast.LENGTH_SHORT).show();
                    //alert();
                    display.setText("Beacon 7");
                    c7=0;
                    d17=0;
                    d11 = 0;
                    d12 = 0;
                    d13 = 0;
                    c1 = 0;
                    c2 = 0;
                    c3 = 0;
                    d14 = 0;
                    d15 = 0;
                    d16 = 0;
                    d17=0;
                    c4 = 0;
                    c5 = 0;
                    c6 = 0;
                    c7=0;
                }
            }
//            t++;
//            if (t>19) {
//                if(c1>5)
//                distances[0] = d11 / c1;
//                else
//                    distances[0] =0;
//                if(c2>5)
//                distances[1] = d12 / c2;
//                else
//                    distances[1] =0;
//                if(c3>5)
//                distances[2] = d13 / c3;
//                else
//                    distances[2] =0;
//                if(c4>5)
//                distances[3] = d14 / c4;
//                else
//                    distances[3] =0;
//                if(c5>5)
//                distances[4] = d15 / c5;
//                else
//                    distances[4] =0;
//                if(c6>5)
//                distances[5]= d16/c6;
//                else
//                    distances[5] =0;
//                if(c7>5)
//                distances[6]= d17/c7;
//                else
//                    distances[6] =0;
//                for(int i=0;i<7;i++)
//                {
//                    if(distances[i]!=0)
//                    budget.put("beacon"+(i+1),distances[i]);
//                }
//                int k=0;
//                Map<String,Double> hm = sortByValue(budget);
//                for (Map.Entry<String, Double> en : hm.entrySet()) {
//                    if (k > 2)
//                        break;
//                    else {
//                        if(k==0)
//                        {
//                            proximitybeacon=en.getKey();
//                            if(en.getValue()<1.2)
//                            {
//                                Toast.makeText(plot.this, "You can now use Explore Feature!", Toast.LENGTH_SHORT).show();
//                                alert();
//                                display.setText(en.getKey());
//                            }
//                        }
//                        distances1[k] = en.getValue();
//                        String temp=en.getKey();
//                        char te=temp.charAt(temp.length()-1);
//                        positions1[k][0]=positions[Character.getNumericValue(te)-1][0];
//                        positions1[k++][1]=positions[Character.getNumericValue(te)-1][1];
//                    }
//                }
//                NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions1, distances1), new LevenbergMarquardtOptimizer());
//                LeastSquaresOptimizer.Optimum optimum = solver.solve();
//                double[] centroid = optimum.getPoint().toArray();

//                t=0;
//                plott((float) centroid[0], (float) centroid[1]);
//            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plotter);
        beacons.put("55:46:4F:D2:6A:CF","Beacon1");
        beacons.put("55:46:4F:D2:6A:DD","Beacon2");
        beacons.put("55:46:4F:11:87:50","Beacon3");
        beacons.put("55:46:4F:11:88:22","Beacon4");
        beacons.put("55:46:4F:11:87:13","Beacon5");
        beacons.put("55:46:4F:11:88:59","Beacon6");
        beacons.put("55:46:4F:11:88:1B","Beacon7");
        checkLocationPermission();
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(Locale.ENGLISH);
            }
        });
        map = findViewById(R.id.map);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        textexplore = findViewById(R.id.textexplore);
        startexplore = findViewById(R.id.startexplore);
        stopexplore = findViewById(R.id.stopexplore);
        read=findViewById(R.id.read);
        inspect = findViewById(R.id.inspect);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        display = findViewById(R.id.display);
        inspect.setVisibility(View.VISIBLE);
        map.setVisibility(View.INVISIBLE);
        categories = getIntent().getStringArrayListExtra("titu");
        value = categories.get(0);
        prevway = categories.get(1);
        nextway = categories.get(2);
        value = value.substring(0, value.length() - 1);
        prevway = prevway.substring(0, prevway.length() - 1);
        nextway = nextway.substring(0, nextway.length() - 1);
        val = value.split(",");
        previe = prevway.split(",");
        nextie = nextway.split(",");
        inspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspect.setVisibility(View.INVISIBLE);
                map.setVisibility(View.VISIBLE);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.setVisibility(View.INVISIBLE);
                inspect.setVisibility(View.VISIBLE);
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String toSpeak=inspect.getText().toString();
//                textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH, null);
                startScanning();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p1 > -1 && p1 < nextie.length) {
                    display.setText(previe[p1--]);
                    String toSpeak=display.getText().toString();
                    textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH, null);
                    }
                if (p1 < 0)
                    p1 = 0;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p1 < nextie.length && p1 > -1) {
                    display.setText(nextie[p1++]);
                    String toSpeak=display.getText().toString();
                    textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH, null);
                }
                if (p1 >= (nextie.length))
                    p1 = nextie.length - 1;
            }
        });
        startexplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explor();
            }
        });
        stopexplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textexplore.setText("");
            }
        });
        ufoBeaconManager = new UFOBeaconManager(plot.this);
        if (bluetoothAdapter.isEnabled()) {
//            Toast.makeText(plot.this, "Bluetooth On", Toast.LENGTH_SHORT).show();
        } else if (!bluetoothAdapter.isEnabled()) {
//            Toast.makeText(plot.this, "Bluetooth Off", Toast.LENGTH_SHORT).show();
        }
        description();
        plott();

    }

    public void alert2(String proximitybeacon)
    {
        ArrayList<String> tags = decision.hashBeacon.get(proximitybeacon).tags;
        String s="";
        for(String tag:tags)
            s+=tag+"\n";
        textToSpeech.speak("You have reached "+ s,TextToSpeech.QUEUE_FLUSH, null);

    }
    public void alert() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
//        try {
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//            r.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(plot.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(plot.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(plot.this)
                        .setTitle("hahsk")
                        .setMessage("asdas")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(plot.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(plot.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(plot.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                    }

                } else {
                }
                return;
            }
        }
    }

    public void enableBlu() {
        bluetoothAdapter.enable();
        Toast.makeText(plot.this, "Bluetooth ON", Toast.LENGTH_SHORT).show();
    }

    public void disableBlu() {
        bluetoothAdapter.disable();
        Toast.makeText(plot.this, "Bluetooth OFF", Toast.LENGTH_SHORT).show();
    }

    public void enableScan1() {
        ufoBeaconManager.startScan(new OnScanSuccessListener() {
            @Override
            public void onSuccess(final UFODevice ufoDevice) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BluetoothDevice bt = ufoDevice.getBtdevice();
                        String store1 = bt.getAddress();
                        double store3 = ufoDevice.getDistance();
                        if (store1.equals(beacon1)) {
                            d1 = store3;
                            if (d1 != 0) {
                                c1++;
                                d11 = d11 + d1;
                            }
                            if(c1>2 && d11/c1<1)
                            {
                                kuku="Beacon1";
                                alert2("Beacon1");
                                c1=0;
                                d11=0;
                            }
                        }
                        else if (store1.equals(beacon2)) {
                            d2 = store3;
                            if (d2 != 0) {
                                c2++;
                                d12 = d12 + d2;
                                c1=0;
                                d11=0;
                            }
                            if(c2>2 && d12/c2<1)
                            {
                                kuku="Beacon2";
                                alert2("Beacon2");
                                c2=0;
                                d12=0;

                            }
                        }
                        else if (store1.equals(beacon3)) {
                            d3 = store3;
                            if (d3 != 0) {
                                c3++;
                                d13 = d13 + d3;
                            }
                            if(c3>2 && d13/c3<1)
                            {
                                kuku="Beacon3";
                                alert2("Beacon3");
                                c3=0;
                                d13=0;

                            }
                        }
                        else if (store1.equals(beacon4)) {
                            d4 = store3;
                            if (d4 != 0) {
                                c4++;
                                d14 = d14 + d4;
                            }
                            if(c4>2 && d14/c4<1)
                            {
                                kuku="Beacon4";
                                alert2("Beacon4");
                                c4=0;
                                d14=0;

                            }
                        }
                        else if (store1.equals(beacon5)) {
                            d5 = store3;
                            if (d5 != 0) {
                                c5++;
                                d15 = d15 + d5;
                            }
                            if(c5>2 && d15/c5<1)
                            {
                                kuku="Beacon5";
                                alert2("Beacon5");
                                c5=0;
                                d15=0;

                            }
                        }
                        else if (store1.equals(beacon6)) {
                            d6 = store3;
                            if (d6 != 0) {
                                c6++;
                                d16 = d16 + d6;
                            }
                            if(c6>2 && d16/c6<1)
                            {
                                kuku="Beacon6";
                                alert2("Beacon6");
                                c6=0;
                                d16=0;

                            }
                        }
                        else if (store1.equals(beacon7)) {
                            d7 = store3;
                            if (d7 != 0) {
                                c7++;
                                d17 = d17 + d7;
                            }
                            if(c7>2 && d17/c7<1)
                            {
                                kuku="Beacon7";
                                alert2("Beacon7");
                                c7=0;
                                d17=0;
                            }
                        }
                    }
                });
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(int i, String s) {
            }
        });
    }

    public void disableScan() {
        ufoBeaconManager.stopScan(new OnSuccessListener() {
            @Override
            public void onSuccess(boolean b) {
                if (b) {
                }
                //Toast.makeText(MainActivity.this, "Scan Stopped!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(plot.this, "false", Toast.LENGTH_SHORT).show();
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(plot.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void plott()
    {
        int i, f, n, ff;
        int row1, row2;
        int col1, col2;
        float r1 = 5f;
        myBitmap = Bitmap.createBitmap(360, 360, Bitmap.Config.ARGB_8888);
        myPaint1 = new Paint();
        myPaint2 = new Paint();
        myPaint3 = new Paint();
        myPaint1.setStyle(Paint.Style.FILL);
        myPaint1.setColor(Color.GREEN);
        myPaint2.setStyle(Paint.Style.FILL);
        myPaint2.setColor(Color.BLUE);
        myPaint3.setStyle(Paint.Style.FILL);
        myPaint3.setColor(Color.RED);
        tempCanvas = new Canvas(myBitmap);
        tempCanvas.drawBitmap(myBitmap, 0, 0, null);
        for (i = 0; i < val.length - 1; i++) {
            f = Integer.parseInt(val[i]);
            n = Integer.parseInt(val[i + 1]);
            row1 = f / 100;
            row2 = n / 100;
            if (row1 == row2) {
                col1 = f % 100;
                col2 = n % 100;
                tempCanvas.drawRect((float) (col1 * 6), (float) ((row1 + 1) * 6), (float) (col2 * 6), (float) ((row1 + 2) * 6), myPaint1);
            } else {
                col1 = f % 100;
                tempCanvas.drawRect((float) (col1 * 6), (float) ((row2 + 1) * 6), (float) ((col1 + 1) * 6), (float) ((row1 + 2) * 6), myPaint1);
            }
        }
        for (i = 0; i < val.length; i++) {
            f = Integer.parseInt(val[i]);
            row1 = f / 100;
            col1 = f % 100;
            if (i == 0)
                tempCanvas.drawRect((float) (col1 * 6), (float) ((row1 + 1) * 6), (float) ((col1 + 1) * 6), (float) ((row1 + 2) * 6), myPaint3);
            else
                tempCanvas.drawRect((float) (col1 * 6), (float) ((row1 + 1) * 6), (float) ((col1 + 1) * 6), (float) ((row1 + 2) * 6), myPaint2);
        }
        String data=decision.getData();
        vale=data.split(",");
        map.setImageDrawable(new BitmapDrawable(getResources(), myBitmap));
        map.setBackgroundResource(getResources().getIdentifier(vale[1],"drawable",this.getPackageName()));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String str = "";
        int row1,col1,row2,col2;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientationData[] = new float[3];
                SensorManager.getOrientation(R, orientationData);
                double data = orientationData[0];
                data = Math.round(Math.toDegrees(data));
                data = data - 339; // change here
                if (data < 0) {
                    data += 360;
                }
                proximitybeacon="Beacon7";
                int cordinate = decision.hashBeacon.get(proximitybeacon).intValue;
                row1 = cordinate / 100;
                col1 = cordinate % 100;
                List<String> cat1=decision.getCategory();
                List<Integer> cat2=decision.getValue();
                for(int i=0;i<cat1.size();i++)
                {
                    int titu= cat2.get(i);
                    if(titu!=cordinate){
                        row2 = titu / 100;
                        col2 = titu % 100;
                        float angle=(float)Math.toDegrees((Math.atan2((row2-row1)*0.392,(col2-col1)*0.46)));
                        if(angle<0)
                            angle = angle + 360;
                        if ((angle > data - 30 && angle < data + 30) || (angle > data + 360 - 30 && angle < data + 360 + 30)) {
                            str = str + cat1.get(i)+"\n";
                            if(proximitybeacon.equals("Beacon7")){
                                if(data> 321 && data< 361)
                                    str=str+"Women's Washroom";
                                if(data> 306 && data< 316)
                                    str=str+"Drinking Water";
                                if(data> 291 && data< 301)
                                    str=str+"Men's Washroom";
                            }
                        }
                    }
                    }
                textexplore.setText(str);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(plot.this);
    }

    private void description() {
        String str = "";
        for (int tt = 0; tt < nextie.length; tt++)
            str = str + nextie[tt] + "\n";
        inspect.setText(str);
        String toSpeak=inspect.getText().toString();
        textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH, null);
    }

    public static HashMap<String,Double> sortByValue(HashMap<String, Double> hm)
    {
        List<Map.Entry<String, Double> > list = new LinkedList<Map.Entry<String, Double> >(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public void startScanning(){
        ufoBeaconManager.startScan(new OnScanSuccessListener() {
            @Override
            public void onSuccess(final UFODevice ufoDevice) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(plot.this, beacons.get(ufoDevice.getBtdevice().getAddress()), Toast.LENGTH_SHORT).show();
                        if(decision.hashBeacon.containsKey(beacons.get(ufoDevice.getBtdevice().getAddress()))){
                            Beacon beacon =decision.hashBeacon.get(beacons.get(ufoDevice.getBtdevice().getAddress()));
//                            Toast.makeText(plot.this, beacon.beaconName, Toast.LENGTH_SHORT).show();
                            if(ufoDevice.getDistance()!=0) {
                                beacon.count++;
                                beacon.distance += ufoDevice.getDistance();
                                if (beacon.count > 5) {
                                    if (beacon.distance/beacon.count<1 && !lastBeacon.equals(beacon.beaconName)){
                                        alert2(beacon.beaconName);
                                        Toast.makeText(plot.this, beacon.beaconName, Toast.LENGTH_SHORT).show();
                                        lastBeacon = beacon.beaconName;
                                    }
                                    beacon.count = 0;
                                    beacon.distance = 0;
                                }
                            }
                        }
                    }
                });
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(int i, String s) {

            }
        });
    }
//    public void stopScanning(){
//        ufoBeaconManager.s
//    }



}


