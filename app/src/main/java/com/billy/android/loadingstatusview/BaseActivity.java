package com.billy.android.loadingstatusview;

import android.app.Activity;

import com.billy.android.loading.Gloading;
import com.billy.android.loadingstatusview.wrapactivity.SpecialActivity;

/**
 * base activity
 * @author billy.qi
 * @since 19/3/19 21:15
 */
public abstract class BaseActivity extends Activity {

    protected Gloading.Holder mHolder;

    /**
     * make a Gloading.Holder wrap with current activity by default
     * override this method in subclass to do special initialization
     * @see SpecialActivity
     */
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            ////在Activity中显示, 父容器为: android.R.id.content  withRetry 需要支持加载失败后点击重试
            mHolder = Gloading.getDefault().wrap(this).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    protected void onLoadRetry() {
        // override this method in subclass to do retry task
    }

    public void showLoading() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoading();
    }

    public void showLoadSuccess() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadSuccess();
    }

    public void showLoadFailed() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadFailed();
    }

    public void showEmpty() {
        initLoadingStatusViewIfNeed();
        mHolder.showEmpty();
    }

}
