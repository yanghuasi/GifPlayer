package com.lihongxin.animationdemo.ByGlide;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lihongxin.animationdemo.R;

/**
 * 通过glide播放一个GIF文件
 */
public class AnimationActivity4 extends Activity {
    private ImageView gif;//gif;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation4);
        context = this;
        //关键代码
        gif = findViewById(R.id.gif);
        playAnimation();


        RelativeLayout main = findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAnimation2();
            }
        });
    }

    private void playAnimation() {
        Glide.with(this).load(R.drawable.test_gif).listener(new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof GifDrawable) {
                    //加载一次
                    ((GifDrawable) resource).setLoopCount(2);
                }
                return false;
            }
        }).into(gif);
    }
    private void playAnimation2() {
        Glide.with(this).load(R.drawable.test_gif2).listener(new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof GifDrawable) {
                    //加载一次
                    ((GifDrawable) resource).setLoopCount(2);
                }
                return false;
            }
        }).into(gif);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

}

