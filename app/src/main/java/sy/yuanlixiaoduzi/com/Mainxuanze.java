package sy.yuanlixiaoduzi.com;


import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;



/**
 * Created by Administrator on 2018/11/1.
 */

public class Mainxuanze extends AppCompatActivity {
    ImageView Card1_bg;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode());//EXplode 爆炸  Slide 上移动
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.item_jihua);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.activity_item_tool);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
         Card1_bg = findViewById(R.id.im_Card_bg1);
        ImageView Card2_bg = findViewById(R.id.im_Card_bg2);
        ImageView Card3_bg = findViewById(R.id.im_Card_bg3);
        Glide.with(this).load(R.drawable.card_bg2).into(Card2_bg);
        Glide.with(this).load(R.drawable.card_bg3).into(Card3_bg);
        Glide.with(this).load(R.drawable.card_bg1).into(Card1_bg);
//        Card1_bg.post(new Runnable() {
//            @Override
//            public void run() {
//                Animator anim = ViewAnimationUtils.createCircularReveal(Card1_bg, Card1_bg.getWidth() / 2, Card1_bg.getHeight() / 2, 0, Card1_bg.getWidth());
//                anim.setDuration(2000);
//                anim.start();
//            }
//        });
        }
}