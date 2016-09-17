package com.example.lzw.myproject;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ImageView;

/**
 * 开屏页
 */
public class SplashActivity extends BaseActivity {
    //开屏页图片
    ImageView iv_logo;
    //淡入动画
    ValueAnimator alphaAnim;
    ValueAnimator translate;

    @Override
    protected int setContent() {
       return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
    }

    @Override
    protected void setListener() {

    }
    /**
     * @description 加载动画
     */
    private void initAnimation() {
        translate =  ObjectAnimator.ofFloat(
                iv_logo, "translationX",0f,780f)
                .setDuration(4000);

        translate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //淡入动画
                alphaAnim = ObjectAnimator.ofFloat(
                        iv_logo, "alpha", 0.0f, 1.0f);
                //设置动画时间
                alphaAnim.setDuration(3000);
                //设置动画播放结束后的监听
                alphaAnim.start();
                alphaAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //跳转到主页
                        Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        //关闭开屏页
                        finish();
                    }
                });


            }
        });
        //开启动画
        translate.start();

    }

    /**
     * 当前页是否已获得焦点
     *
     * @param hasFocus true代表获得焦点，false相反
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            initAnimation();
        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        alphaAnim.pause();
    }
}
