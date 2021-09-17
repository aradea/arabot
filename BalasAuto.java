package com.asep.jurigwa;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.RemoteInput;
import android.util.Log;
import android.widget.Toast;

import com.asep.jurigwa.models.Action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;

@SuppressLint("OverrideAbstract")
public class BalasAuto extends NotificationListenerService {
    private BufferedWriter bw;
    public static final String TAG = "jurig";

    private SimpleDateFormat sdf;
    private MyHandler handler = new MyHandler();
    private String nMessage;
    private String data;
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            String msgString = (String) msg.obj;
            Toast.makeText(getApplicationContext(), msgString, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ASEP", "Service is started" + "-----");
        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.i(TAG, "onNotificationRemoved");

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        //        super.onNotificationPosted(sbn);


        Log.i(TAG, "Here");

        BalasAuto.this.cancelNotification(sbn.getKey());

        Action action = NotificationUtils.getQuickReplyAction(sbn.getNotification(), getPackageName());

        if (action != null) {
            Log.i(TAG, "success");
            try {
				Calendar c = Calendar.getInstance();
				int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

				if(timeOfDay >= 4 && timeOfDay < 9){
					String[] jawab1 = new String[] {"jika dalam 3 menit sy gk balas brrti sy gak on", "gak bisa balas wa... lagi gk bawa hp", "silakan tinggalkan pesan...sy lgi gk bawa hp","wilujeng enjing..selamat pagi🌭","otomatis","sae namah enjing keneh kieumah sarapan🌮 ","quota lgi cekak gk bisa balas wa"
						,"nya seh... saenamaah enjing kieumah ngopi hela🍟","ada yg bisa sy bantu?","ma 'af kalau gak nyambung karna sy otomatis","persiapan brangkat kerja"};
					String ACAK1 = jawab1[(int) (Math.random() * jawab1.length)];
					action.sendReply(getApplicationContext(), "🥱"+ACAK1);

				}else if(timeOfDay >= 9 && timeOfDay < 16){
					String[] jawab2 = new String[] {"jika dalam 4 menit sy gk balas brrti sy lgi gak pegang hp", "asa paranas kieu nya poe teh🥺", "Tos siang gening teu karaos nya.","haredang bro... hp na beak batre","lagi riweuh... hampura teu nyambung","ngojay na balong panas kieumah madep","slamt siang wijeng wayah kieu"
						,"kela nuju sibuk...ieu teh","hp lagi di cas...ngopi hela","jigana tos siang kieumah.... cendol rada madep","lagi kerja..."};
					String ACAK2 = jawab2[(int) (Math.random() * jawab2.length)];
					action.sendReply(getApplicationContext(), "🤗"+ACAK2);

				}else if(timeOfDay >= 16 && timeOfDay < 18){
					String[] jawab3 = new String[] {"jika dalam 5 menit sy gk balas brrti sy tidur", "wasalam..", "teu karaos tos sonten deui 😌","rada sae jigana sonten2 kieu mah kopi luak 😇","robot..auto","kela kagok... ker ngusep","ges lila teu teu tuang pais simeut","met.. sore bro bra 😇"
						,"kitu nya.... ok","waktuna jalan jalan sore","ges sore bade mandi cantik hela","teu nyambung nya?... hehe hampura ieu otomatis","oh....."};
					String ACAK3 = jawab3[(int) (Math.random() * jawab3.length)];
					action.sendReply(getApplicationContext(), "🤔"+ACAK3);

				}else if(timeOfDay >= 18 && timeOfDay < 24){
					String[] jawab4 = new String[] {"jika dalam 5 menit sy gk balas brrti sy sibuk","teu bisa blas wa brrti sare","jam sabaraha ieu","malam....","waktuna tidur ges malam","ya bagus namah wiridan ngadoa gs malam mah","emang gak ngntuk gitu?"
						,"malam yg dingin...euy","robot otomasi..balas wa","loba nyamuk bro?","mau tidur persiapan... sugan bae ngimpi","ngantuk brooò","dikopian hela supadis cenghar geura..🙌","asa ngaley kieu soca...duh"};
					String ACAK4 = jawab4[(int) (Math.random() * jawab4.length)];
					action.sendReply(getApplicationContext(), "😴"+ACAK4);

				}
				//
				/*String[] jawab1 = new String[] {"hp lagi rusak", "*Asallamualaikum*","Agi "};
				 String acak1 = jawab1[(int) (Math.random() * jawab1.length)];
				 action.sendReply(getApplicationContext(), "😊"+acak1);
				 */
            } catch (PendingIntent.CanceledException e) {
                Log.i(TAG, "CRAP " + e.toString());
            }
        } else {
            Log.i(TAG, "not success");
        }



    }

    private void writeData(String str) {
        try {
//            bw.newLine();
//            bw.write("NOTE");
            bw.newLine();
            bw.write(str);
            bw.newLine();
//            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            FileOutputStream fos = new FileOutputStream(newFile(), true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
        } catch (IOException e) {
            Log.d("ASEP", "BufferedWriter Initialization error");
        }
        Log.d("ASEP", "Initialization Successful");
    }

    private File newFile() {
        File fileDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "ANotification");
        fileDir.mkdir();
        String basePath = Environment.getExternalStorageDirectory() + File.separator + "ANotification" + File.separator + "record.txt";
        return new File(basePath);

    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

			switch (msg.what) {
                case 1:
//                    Toast.makeText(MyService.this,"Bingo",Toast.LENGTH_SHORT).show();




            }
        }
    }
}
