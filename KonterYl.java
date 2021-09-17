package com.as.jurig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdActivity.*;
import com.google.android.gms.ads.AdRequest.*;
import com.google.android.gms.ads.MobileAds.*;
import com.google.android.gms.ads.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import android.content.*;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KonterYl extends AppCompatActivity
{
	private TextView konterylhasil;
	private EditText Eyl,Ejarum,Ekilo,Efeder,Ebng;
	private Button OK, RST;
	Double angka1,angka2,angka3,angka4,angka5,hasil_1,hasil_2,hasil_3,ylfull,ubenang;
	LinearLayout l1,l2;
	Animation uptodown,downtoup;



	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konteryl);
		
		l1 = (LinearLayout) findViewById(R.id.l1);
		uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
		downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
		l1.setAnimation(uptodown);

		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.jurig);
		getSupportActionBar().setDisplayUseLogoEnabled(true);

		konterylhasil =(TextView) findViewById(R.id.hasilkonter);
		Ejarum =(EditText) findViewById(R.id.jumlahjarum);
		Eyl =(EditText) findViewById(R.id.jumlahyl);
		Ekilo =(EditText) findViewById(R.id.kiloantarget);
		Efeder =(EditText) findViewById(R.id.jumlahfeder);
		Ebng =(EditText) findViewById(R.id.ukuranbenang);
		OK =(Button) findViewById(R.id.btok);
		RST =(Button) findViewById(R.id.btreset);



		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.jurig);
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		//loadBannerAds();

	}

	/*public void loadBannerAds() {
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		AdView adView = new AdView(this);
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId("ca-app-pub-1533557789138028/8605761799");

	}*/
	public void konver1(){
        //konversi inputan ke double
        angka1 = Double.parseDouble(Eyl.getText().toString());
        angka2 = Double.parseDouble(Efeder.getText().toString());
		angka3 = Double.parseDouble(Ejarum.getText().toString());
		angka4 = Double.parseDouble(Ebng.getText().toString());
		angka5 = Double.parseDouble(Ekilo.getText().toString());
    }
	public void HitungReset(View view) {
        konver1();
        //hasil = v1+v2;  //perhitungan
        konterylhasil.setText("");
		Ejarum.setText("0");
		Ebng.setText("0");
		Efeder.setText("0");
		Ekilo.setText("0");
		Eyl.setText("0");
		
    }
	public void HitungOk(View view) {
        konver1();
		ubenang = 1.693 * angka4;
		ylfull = angka1 /100 * angka3;
        hasil_1 =ylfull * angka2 / ubenang;
		hasil_2 = angka5 * 1000;
		hasil_3 = hasil_2 / hasil_1;
        konterylhasil.setText(Double.toString(hasil_3));  //output
    }
}
