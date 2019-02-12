package asus.com.bwie.work1;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 圆角
     */
    private Button mYuanjiaoButton;
    /**
     * 圆形
     */
    private Button mYuanxingButton;
    /**
     * 比例1：2
     */
    private Button mBiliButton;
    /**
     * 渐进式
     */
    private Button mJianjinButton;
    /**
     * 磁盘缓存
     */
    private Button mCipanButton;
    /**
     * 加载动图
     */
    private Button mDonghuaButton;
    /**
     * 加载监听
     */
    private Button mJiantingButton;
    /**
     * 配置OKHTTP
     */
    private Button mPeizhiButton;
    private SimpleDraweeView mYuanjiao;
    private SimpleDraweeView mYuanxing;
    private SimpleDraweeView mBili;
    private SimpleDraweeView mJianjin;
    private SimpleDraweeView mCipan;
    private SimpleDraweeView mDonghua;
    private SimpleDraweeView mJianting;
    private SimpleDraweeView mPeizhi;

    Uri uri = Uri.parse("http://img5.imgtn.bdimg.com/it/u=3365018759,2226705862&fm=26&gp=0.jpg");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mYuanjiaoButton = (Button) findViewById(R.id.yuanjiaoButton);
        mYuanjiaoButton.setOnClickListener(this);
        mYuanxingButton = (Button) findViewById(R.id.yuanxingButton);
        mYuanxingButton.setOnClickListener(this);
        mBiliButton = (Button) findViewById(R.id.biliButton);
        mBiliButton.setOnClickListener(this);
        mJianjinButton = (Button) findViewById(R.id.jianjinButton);
        mJianjinButton.setOnClickListener(this);
        mCipanButton = (Button) findViewById(R.id.cipanButton);
        mCipanButton.setOnClickListener(this);
        mDonghuaButton = (Button) findViewById(R.id.donghuaButton);
        mDonghuaButton.setOnClickListener(this);
        mJiantingButton = (Button) findViewById(R.id.jiantingButton);
        mJiantingButton.setOnClickListener(this);
        mPeizhiButton = (Button) findViewById(R.id.peizhiButton);
        mPeizhiButton.setOnClickListener(this);
        mYuanjiao = (SimpleDraweeView) findViewById(R.id.yuanjiao);
        mYuanxing = (SimpleDraweeView) findViewById(R.id.yuanxing);
        mBili = (SimpleDraweeView) findViewById(R.id.bili);
        mJianjin = (SimpleDraweeView) findViewById(R.id.jianjin);
        mCipan = (SimpleDraweeView) findViewById(R.id.cipan);
        mDonghua = (SimpleDraweeView) findViewById(R.id.donghua);
        mJianting = (SimpleDraweeView) findViewById(R.id.jianting);
        mPeizhi = (SimpleDraweeView) findViewById(R.id.peizhi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.yuanjiaoButton:
                mYuanjiao.setVisibility(View.VISIBLE);
                mYuanxing.setVisibility(View.GONE);
                mBili.setVisibility(View.GONE);
                mJianjin.setVisibility(View.GONE);
                mCipan.setVisibility(View.GONE);
                mDonghua.setVisibility(View.GONE);
                mJianting.setVisibility(View.GONE);
                mPeizhi.setVisibility(View.GONE);
                mYuanjiao.setImageURI(uri);
                break;
            case R.id.yuanxingButton:
                mYuanjiao.setVisibility(View.GONE);
                mYuanxing.setVisibility(View.VISIBLE);
                mBili.setVisibility(View.GONE);
                mJianjin.setVisibility(View.GONE);
                mCipan.setVisibility(View.GONE);
                mDonghua.setVisibility(View.GONE);
                mJianting.setVisibility(View.GONE);
                mPeizhi.setVisibility(View.GONE);
                mYuanxing.setImageURI(uri);
                break;
            case R.id.biliButton:
                mYuanjiao.setVisibility(View.GONE);
                mYuanxing.setVisibility(View.GONE);
                mBili.setVisibility(View.VISIBLE);
                mJianjin.setVisibility(View.GONE);
                mCipan.setVisibility(View.GONE);
                mDonghua.setVisibility(View.GONE);
                mJianting.setVisibility(View.GONE);
                mPeizhi.setVisibility(View.GONE);
                mBili.setImageURI(uri);
                break;
            case R.id.jianjinButton:
                mYuanjiao.setVisibility(View.GONE);
                mYuanxing.setVisibility(View.GONE);
                mBili.setVisibility(View.GONE);
                mJianjin.setVisibility(View.VISIBLE);
                mCipan.setVisibility(View.GONE);
                mDonghua.setVisibility(View.GONE);
                mJianting.setVisibility(View.GONE);
                mPeizhi.setVisibility(View.GONE);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(mJianjin.getController())
                        .build();
                mJianjin.setController(controller);
                break;
            case R.id.cipanButton:
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                boolean inBitmapMemoryCache = imagePipeline.isInBitmapMemoryCache(uri);
                if (inBitmapMemoryCache=true){
                    Toast.makeText(MainActivity.this,"缓存成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"缓存失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.donghuaButton:
                mYuanjiao.setVisibility(View.GONE);
                mYuanxing.setVisibility(View.GONE);
                mBili.setVisibility(View.GONE);
                mJianjin.setVisibility(View.GONE);
                mCipan.setVisibility(View.GONE);
                mDonghua.setVisibility(View.VISIBLE);
                mJianting.setVisibility(View.GONE);
                mPeizhi.setVisibility(View.GONE);
                Uri parse = Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549988414465&di=b4017419421da38b456c8664010fead7&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F201612_23_9%2Fa15tbh4708957136855.gif");
                ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(
                            String id,
                            @Nullable ImageInfo imageInfo,
                            @Nullable Animatable anim) {
                        if (anim != null) {
                            // 其他控制逻辑
                            anim.start();
                        }
                    }
                };

                DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                        .setUri(parse)
                        .setControllerListener(controllerListener)
                        .build();
                mDonghua.setController(controller1);

                break;
            case R.id.jiantingButton:
                mYuanjiao.setVisibility(View.GONE);
                mYuanxing.setVisibility(View.GONE);
                mBili.setVisibility(View.GONE);
                mJianjin.setVisibility(View.GONE);
                mCipan.setVisibility(View.GONE);
                mDonghua.setVisibility(View.GONE);
                mJianting.setVisibility(View.VISIBLE);
                mPeizhi.setVisibility(View.GONE);
                ControllerListener controllerListener1 = new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(
                            String id,
                            @Nullable ImageInfo imageInfo,
                            @Nullable Animatable anim) {
                        if (imageInfo == null) {
                            return;
                        }
                        QualityInfo qualityInfo = imageInfo.getQualityInfo();
                        FLog.d("Final image received! " +
                                        "Size %d x %d",
                                "Quality level %d, good enough: %s, full quality: %s",
                                imageInfo.getWidth(),
                                imageInfo.getHeight(),
                                qualityInfo.getQuality(),
                                qualityInfo.isOfGoodEnoughQuality(),
                                qualityInfo.isOfFullQuality());
                    }

                    @Override
                    public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                        Toast.makeText(MainActivity.this,"Intermediate image received",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        FLog.e(getClass(), throwable, "Error loading %s", id);
                    }
                };

                DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                        .setControllerListener(controllerListener1)
                        .setUri(uri)
                        .build();
                mJianjin.setController(controller2);
                break;
            case R.id.peizhiButton:
                break;
        }
    }
}
