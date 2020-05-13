package com.lihongxin.animationdemo.ByVideoView;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;

import com.lihongxin.animationdemo.R;

/**
 * 通过videoview播放一段mp4
 */

public class AnimationActivity3 extends Activity  {
    private GifVideoView gifVideoView;
    private MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation3);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int heigh=dm.heightPixels;
        gifVideoView=findViewById(R.id.gifVideoView);
        mc = new MediaController(this);
        mc.setVisibility(View.INVISIBLE);
        gifVideoView.setMetrics(width,heigh);
        gifVideoView.setBackgroundColor(0);
        gifVideoView.setMediaController(mc);
        Uri uri=Uri.parse( "android.resource://"+getPackageName()+"/"+R.raw.girl);
        gifVideoView.setVideoURI(uri);
        gifVideoView.start();
        gifVideoView.requestFocus();
        //视频编码格式不支持的情况
        gifVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                if(what==MediaPlayer.MEDIA_ERROR_SERVER_DIED){
                    Log.v("view exception","Media Error,Server Died"+extra);
                }else if(what==MediaPlayer.MEDIA_ERROR_UNKNOWN){
                    Log.v("video exception","Media Error,Error Unknown "+extra);
                }
                return true;
            }
        });

        Log.i("play",uri+"");



        LinearLayout main = findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一个动画播放完成后，播放第二个动画
                Uri uri=Uri.parse( "android.resource://"+getPackageName()+"/"+R.raw.test2);
                gifVideoView.setVideoURI(uri);
                gifVideoView.start();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


/*    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_SCROLL:
                return true;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                return true;
            case MotionEvent.ACTION_MASK:
                return true;
            case MotionEvent.ACTION_DOWN:
                return true;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }*/


}
