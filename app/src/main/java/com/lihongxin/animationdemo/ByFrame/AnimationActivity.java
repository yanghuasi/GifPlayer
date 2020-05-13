package com.lihongxin.animationdemo.ByFrame;

import android.app.Activity;
import android.os.Bundle;
import com.lihongxin.animationdemo.R;


/**
 * 通过逐帧动画drawable下的多张PNG图片
 */

public class AnimationActivity extends Activity {
    private RedPacketsAnimationByFrameView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        view = findViewById(R.id.animationView);
        view.play_animation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
