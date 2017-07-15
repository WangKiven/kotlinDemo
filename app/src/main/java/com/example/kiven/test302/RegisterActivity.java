package com.example.kiven.test302;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiven.test302.entity.Shop;
import com.example.kiven.test302.entity.User;
import com.kiven.kutils.logHelper.KLog;
import com.kiven.kutils.tools.KAlertDialogHelper;
import com.kiven.kutils.tools.KUtil;

import org.xutils.ex.DbException;

import java.io.IOException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loadBg();

        findViewById(R.id.ll_color).setOnTouchListener(new View.OnTouchListener() {
            float oldX, oldY;
            int oldX1, oldY1;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                FrameLayout.LayoutParams wmParams = (FrameLayout.LayoutParams) v.getLayoutParams();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = event.getRawX();
                        oldY = event.getRawY();

                        oldX1 = wmParams.leftMargin;
                        oldY1 = wmParams.topMargin;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int b = KUtil.dip2px(getBaseContext(), 1) / 2;
                        float x = event.getRawX() - oldX;
                        float y = event.getRawY() - oldY;
                        if (Math.abs(x) > 1 && Math.abs(y) > 1) {
                            wmParams.leftMargin = (int) (oldX1 + x);
                            wmParams.topMargin = (int) (oldY1 + y);
                        }
                        break;
                    default:
                        int a = KUtil.dip2px(getBaseContext(), 2);
                        if (Math.abs(wmParams.leftMargin - oldX1) <= a && Math.abs(wmParams.topMargin - oldY1) <= a) {// 认定单点击
                            Toast.makeText(getBaseContext(), "onClickss", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

                //刷新
                v.setLayoutParams(wmParams);

                return true;
            }
        });
        findViewById(R.id.iv_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBg();
            }
        });

    }

    private String wpTag = "wallpaper";
    private void loadBg() {
        int num = KUtil.getSharedPreferencesIntValue(wpTag, 0);
        KUtil.putSharedPreferencesIntValue(wpTag, num + 1);

        try {
            String[] locales = getAssets().list(wpTag);
            if (locales == null || locales.length == 0) {
                return;
            }

            String path = wpTag + "/" + locales[num % locales.length];

            InputStream inputStream = getAssets().open(path);
            Bitmap bgBitmap = BitmapFactory.decodeStream(inputStream);

            ImageView imageView = (ImageView) findViewById(R.id.iv_bg);
            imageView.setImageBitmap(bgBitmap);

            Palette.from(bgBitmap).resizeBitmapArea(8).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    setColor(palette.getDominantSwatch(), R.id.item_1, "getDominantSwatch");
                    setColor(palette.getMutedSwatch(), R.id.item_2, "getMutedSwatch");
                    setColor(palette.getVibrantSwatch(), R.id.item_3, "getVibrantSwatch");
                    setColor(palette.getDarkMutedSwatch(), R.id.item_4, "getDarkMutedSwatch");
                    setColor(palette.getDarkVibrantSwatch(), R.id.item_5, "getDarkVibrantSwatch");
                    setColor(palette.getLightMutedSwatch(), R.id.item_6, "getLightMutedSwatch");
                    setColor(palette.getLightVibrantSwatch(), R.id.item_7, "getLightVibrantSwatch");
                }
            });

        } catch (IOException e) {
            KLog.e(e);
        }
    }

    public void setColor(Palette.Swatch swatch, int rId, String mode) {
        View view = findViewById(rId);

        TextView tv_mode = (TextView) view.findViewById(R.id.tv_mode);
        TextView tv_rgb = (TextView) view.findViewById(R.id.tv_rgb);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        if (swatch == null) {
            tv_mode.setText(mode + " 没获取到");

            tv_rgb.setTextColor(0);
            tv_title.setTextColor(0);
            tv_message.setTextColor(0);
        } else {
            tv_mode.setText(mode);

            tv_rgb.setTextColor(swatch.getRgb());
            tv_title.setTextColor(swatch.getTitleTextColor());
            tv_message.setTextColor(swatch.getBodyTextColor());
        }
    }

    public void onClick(View view) {
        // java调用Kotlin
        User user = new User("kiven", "pw");

        boolean b = true;
        try {
            // 静态变量调用
            AppContext.Companion.getDbManager().save(user);

            Shop shop = new Shop("SN9008909", "食品");
            KLog.i(shop.getMacNum());
        } catch (DbException e) {
            e.printStackTrace();
            b = false;
        }

        KAlertDialogHelper.Show1BDialog(this, "注册" + (b? "成功":"失败"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
