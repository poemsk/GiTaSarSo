package com.poepoemyintswe.gitasarso;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Detail  extends Activity implements OnClickListener{
	TextView title, singer;
	Button play, back, next, pause;
	Sounds s = new Sounds(this);
	RelativeLayout r, button;
	int i= 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		Intent intent = getIntent();
		i = intent.getIntExtra("pressed", 0);
		
		r = (RelativeLayout)findViewById(R.id.bg_color);
		button = (RelativeLayout)findViewById(R.id.button);
		RandomColor();
		title = (TextView)findViewById(R.id.title);
		singer = (TextView)findViewById(R.id.subtitle);
		play= (Button)findViewById(R.id.play);
		back = (Button)findViewById(R.id.back);
		next = (Button)findViewById(R.id.next);
		pause = (Button)findViewById(R.id.pause);
		
		play.setOnClickListener(this);
		back.setOnClickListener(this);
		next.setOnClickListener(this);
		pause.setOnClickListener(this);
		
		ChangeFont(title);ChangeFont(singer);
		title.setText(Main.title[i]);
		singer.setText(Main.singers[i]);
		
		
	}
	
	private void ChangeFont(TextView t) {
		if (Build.VERSION.SDK_INT > 14) {
			Typeface face = Typeface.createFromAsset(this.getAssets(), String.format("fonts/%s", "ZAWGYI1.TTF")); 
			t.setTypeface(face);
		}
		else {
			Typeface face = Typeface.createFromAsset(this.getAssets(), String.format("fonts/%s", "ZAWGYI.TTF")); 
			t.setTypeface(face);
		}
	}

	@Override
	public void onClick(View arg0) {
		if(play.getId() == arg0.getId()){
			FinishListener();
			pause.setVisibility(View.VISIBLE);
			play.setVisibility(View.GONE);
			RandomColor();

		}
		else if (pause.getId() == arg0.getId()) {
			s.pause();
			play.setVisibility(View.VISIBLE);
			pause.setVisibility(View.GONE);
			RandomColor();

		}
		else if(back.getId() == arg0.getId()) {
			if(i>0){
				
				i--;
				title.setText(Main.title[i]);
				singer.setText(Main.singers[i]);
				if (s.isPlaying) {
					FinishListener();					
				}
				RandomColor();
			}
		}
		else{
			if(i<Main.song.length-1){
				
				i++;
				title.setText(Main.title[i]);
				singer.setText(Main.singers[i]);
				if (s.isPlaying) {
					FinishListener();
				}
				RandomColor();
			}
			
			
		}
	}
	
	@Override
	public void onBackPressed() {
		
		if (s.isPlaying) {
			s.mp.reset();
            s.mp.release();
            
		}
		
		super.onBackPressed();
	}
	
	public void FinishListener(){
		s.sounds(Main.song[i]);
		s.play();
		s.mp.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mPlayer) {
				mPlayer.stop();
				s.isPlaying = false;
				play.setVisibility(View.VISIBLE);
				pause.setVisibility(View.GONE);
				
			}
		});
	}
	
	public void RandomColor(){
		r.setBackgroundColor(Color.rgb((int)(Math.random()*100), (int)(Math.random()*100), (int)(Math.random()*100)));
		button.setBackgroundColor(Color.rgb((int)(Math.random()*100)+151, (int)(Math.random()*100)+151, (int)(Math.random()*100)+151));

	}
}

