package com.lihongxin.animationdemo.BySurfaceView;

import android.app.Activity;
import android.os.Bundle;

import com.lihongxin.animationdemo.R;

public class AnimationActivity5 extends Activity {
    //初始化
    private GifSurfaceView gsv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation5);
        gsv = (GifSurfaceView) findViewById(R.id.gsv);
//        playNetGif();//目前播放网络gif不可行
        playLocalGif();
    }

    private void playLocalGif() {
//        // 设置路径，这个路径实际上在library中是课更改的，我们在assets目录下放置一张gif图片
        gsv.setPath("animation5.gif");
// 设置缩放大小
        gsv.setZoom(2f);
    }

    private void playNetGif() {
        // 如果是网络，记得添加权限
        gsv.setNet(true);
        gsv.setPath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589368609851&di=da580f5041a1955bd3fbff4726f1f241&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201806%2F12%2F20180612133448_SXT3V.gif");
        gsv.setZoom(2f);
    }
}
