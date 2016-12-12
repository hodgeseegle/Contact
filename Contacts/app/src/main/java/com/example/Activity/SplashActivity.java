package com.example.Activity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;

import com.example.lzw.myproject.R;

/**
 * 开屏页
 */
public class SplashActivity extends BaseActivity {
    private ImageView iv_splash_title1;
    private ImageView iv_splash_title2;
    private ObjectAnimator alphaAniam;
    @Override
    protected int setContent() {
       return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        iv_splash_title1=(ImageView)findViewById(R.id.iv_splash_title1);
        iv_splash_title2=(ImageView)findViewById(R.id.iv_splash_title2);
    }

    @Override
    protected void setListener() {

    }
    /**
     * @description 加载动画
     */
    private void initAnimation() {
        alphaAniam=ObjectAnimator.ofFloat(iv_splash_title2,"alpha",0.0f,1.0f);
        alphaAniam.setDuration(3000);   //设置动画时间
        alphaAniam.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //跳转到主页
                Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
                startActivity(intent);
                //关闭开屏页
                finish();
            }
        });
        alphaAniam.start();

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
        alphaAniam.pause();
    }
}
