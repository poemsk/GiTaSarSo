package com.poepoemyintswe.gitasarso;

import android.content.Context;
import android.media.MediaPlayer;

public class Sounds {
	
	public MediaPlayer mp = null;
	boolean isPlaying = false;
	
	Context context;
	
	public Sounds(Context context){
		this.context = context;
	}
	
	public void sounds(int id) {
		if (mp != null) {
            mp.reset();
            mp.release();
        }
        mp = MediaPlayer.create(context, id);   
        
	}
	
	public void play(){
		mp.start();
		isPlaying = true;
	}
	
	public void pause(){
		mp.pause();
		isPlaying = false;
	}
	
}
