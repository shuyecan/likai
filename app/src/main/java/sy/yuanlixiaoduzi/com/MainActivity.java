package sy.yuanlixiaoduzi.com;

import android.animation.Animator;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.Been.Mainbeen;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

import apther.Main_apther;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mdrawerlayout;
    private NavigationView nav_view;
    private RecyclerView rc_main_day;
    private List<Mainbeen> list = new ArrayList<>();
    private Main_apther apther;
    private long mExitTime;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                mdrawerlayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if(Build.VERSION.SDK_INT <=19){
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("警告")
                    .setMessage("本应用不支持安卓4.4及以下版本，对您带来不便，敬请谅解。")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(0);
                        }
                    })
                    .create();
            dialog.setCancelable(false);
            dialog.show();
        }
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);
        initView();
        initdata();
    }

    private void initdata() {
        list = LitePal.findAll(Mainbeen.class);
        if(list.size()==0||list==null) {
            for (int x = 1; x < 31; x++) {
                list.add(new Mainbeen(x, 0, false));
            }
            LitePal.saveAll(list);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
       //rc_main_day.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
       rc_main_day.setLayoutManager(layoutManager);
        apther = new Main_apther(list);
        rc_main_day.setAdapter(apther);
        rc_main_day.post(new Runnable() {
            @Override
            public void run() {
                Animator anim = ViewAnimationUtils.createCircularReveal(rc_main_day, rc_main_day.getWidth() / 2, 0, 0, rc_main_day.getHeight());
                anim.setDuration(1500);
                anim.start();
            }
        });
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar_companyranks);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_view_home_36dp);
        }
        mdrawerlayout = findViewById(R.id.drawer_main);
        nav_view = findViewById(R.id.nav_view);
        final View draw = nav_view.inflateHeaderView(R.layout.nav_header);
         rc_main_day = findViewById(R.id.rc_main_day);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.toString()){
                    case "训练计划":
                        mdrawerlayout.closeDrawers();
                        break;
                    case "选择训练计划":
                        Intent intent = new Intent(MainActivity.this,Mainxuanze.class);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                        break;
                    case "统计":
                        Intent intent2 = new Intent(MainActivity.this,ItemTongji.class);
                        startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                        break;
                    case  "设置":
                        Intent intent3 = new Intent(MainActivity.this,SettingActivity.class);
                        startActivity(intent3,ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mdrawerlayout.closeDrawers();
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("退出")
                .setMessage("就要走了？一起来锻炼吧")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("离开", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create();
        dialog.show();

        return super.onKeyDown(keyCode, event);
    }
}
