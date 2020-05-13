package com.lihongxin.animationdemo.BySurfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 自定义Gif动画引擎 SurfaceView的实现主要是实现高速预览 我们将GIF图片绘制在SurfaceView上
 *
 * @author LGL
 */
public class GifSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    // 监听
    private SurfaceHolder holder;
    // 影片类
    private Movie movie;
    // 输入流
    private InputStream is = null;
    // 缩放
    private float zoom = 1f;
    // 图片路径
    private String path;
    // 判断是否网络读取
    private boolean isNet = false;

    // 逐步播放
    private Handler handler = new Handler();
    private Runnable run = new Runnable() {

        @Override
        public void run() {
            // 不断绘制
            Canvas canvas = holder.lockCanvas();
            // 绘制的时候进行缩放比例,不影响下次绘图操作
            canvas.save();
            canvas.scale(zoom, zoom);
            if (movie != null) {
                movie.draw(canvas, 0, 0);
                // 开始绘制
                movie.setTime((int) (System.currentTimeMillis() % movie.duration()));
            }
            canvas.restore();
            holder.unlockCanvasAndPost(canvas);
            handler.removeCallbacks(run);
            // 下次还用这个线程
            handler.postDelayed(run, 30);
        }
    };

    // 构造方法
    public GifSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            // 判断读取方法
            if (isNet) {
                // Android 4.0 之后不能在主线程中请求HTTP请求
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //                            is = new URL(path).openConnection().getInputStream();
                        is = getImageStream(path);
                    }
                }).start();
            } else {
                // 本地读取文件
                is = getContext().getAssets().open(path);
            }
            // 读取流
            movie = Movie.decodeStream(is);
            // 设置SurfaceView的宽高
            if (movie != null) {
                int width = movie.width();
                int height = movie.height();
                setMeasuredDimension((int) (width * zoom), (int) (height * zoom));
            }
            // 播放gif的帧动画
            handler.post(run);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // 初始化完成
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 读取影片流

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // SurfaceView被销毁时结束线程
        handler.removeCallbacks(run);
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNet(boolean isNet) {
        this.isNet = isNet;
    }

    /**
     * 获取网络图片流
     *
     * @param url
     * @return
     */
    private InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }
}
