package com.onektower.indexlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by zhwilson on 2016/12/1.
 * 模仿手机联系人界面的listview
 */
public class IndexListView extends ListView {
    //左侧分组栏默认宽高
    private static final float GROUP_VIEW_WIDTH = 50;
    private static final float GROUP_VIEW_HEIGHT = 50;
    //索引栏默认宽高
    private static final float INDEX_VIEW_WIDTH = 40;
    private static final float INDEX_VIEW_HEIGHT = 400;
    //索引栏与屏幕的右边距
    private static final float INDEX_VIEW_RIGHT_MARGIN = 10;
    private Context mContext;
    //需要的几个View
    private View mGroupView;//左侧分组View
    private View mIndexView;//索引栏View
    //左侧分组栏宽高
    private int mGroupViewWidth;
    private int mGroupViewHeight;
    //索引栏宽高
    private int mIndexViewWidth;
    private int mIndexViewHeight;
    //索引栏与屏幕右边距
    private int mIndexViewMargin;
    //控件的宽高
    private int width;
    private int height;

    private boolean mGroupViewVisiblity = true;
    private boolean mIndexViewVisiblity = false;

    private IndexAdapter indexAdapter;//适配器

    public IndexListView(Context context) {
        this(context, null);
    }

    public IndexListView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.IndexListViewDefStyle);
    }

    public IndexListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndexListView, defStyleAttr, 0);
        mGroupViewWidth = typedArray.getInt(R.styleable.IndexListView_groupViewWidth, dp2px(GROUP_VIEW_WIDTH));
        mGroupViewHeight = typedArray.getInt(R.styleable.IndexListView_groupViewHeight, dp2px(GROUP_VIEW_HEIGHT));
        mIndexViewWidth = typedArray.getInt(R.styleable.IndexListView_indexViewWidth, dp2px(INDEX_VIEW_WIDTH));
        mIndexViewHeight = typedArray.getInt(R.styleable.IndexListView_indexViewHeight, dp2px(INDEX_VIEW_HEIGHT));
        mIndexViewMargin = typedArray.getInt(R.styleable.IndexListView_indexViewRightMargin, dp2px(INDEX_VIEW_RIGHT_MARGIN));

        typedArray.recycle();
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        indexAdapter = (IndexAdapter) adapter;
        super.setAdapter(adapter);
    }

    public void setmGroupView(View mGroupView) {//设置左侧分组栏
        this.mGroupView = mGroupView;
    }

    public void setmIndexView(View mIndexView) {//设置右侧索引栏
        this.mIndexView = mIndexView;
    }

    public void setmGroupViewVisiblity(boolean mGroupViewVisiblity) {//设置分组栏可见性
        this.mGroupViewVisiblity = mGroupViewVisiblity;
    }

    public void setmIndexViewVisiblity(boolean mIndexViewVisiblity) {//设置索引栏可见性
        this.mIndexViewVisiblity = mIndexViewVisiblity;
    }

    private int dp2px(float dp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mGroupView != null && mGroupViewVisiblity) {
            measureChild(mGroupView, widthMeasureSpec, heightMeasureSpec);
            mGroupViewWidth = mGroupView.getMeasuredWidth();
            mGroupViewHeight = mGroupView.getMeasuredHeight();
        }

        if (mIndexView != null && mIndexViewVisiblity) {
            measureChild(mIndexView, widthMeasureSpec, heightMeasureSpec);
            mIndexViewWidth = mIndexView.getMeasuredWidth();
            mIndexViewHeight = mIndexView.getMeasuredHeight();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mGroupView != null && mGroupViewVisiblity) {
            mGroupView.layout(0, 0, mGroupViewWidth, mGroupViewHeight);
            //TODO 计算分组栏的位置
            configGroupView(getFirstVisiblePosition());
        }
        if (mIndexView != null && mIndexViewVisiblity) {
            mIndexView.layout(width - mIndexViewWidth - mIndexViewMargin, mIndexViewMargin, width - mIndexViewMargin, height - mIndexViewMargin);
        }
    }

    private void configGroupView(int position) {
        if (mGroupView == null)
            return;
        int state = indexAdapter.getGroupState(position);
        switch (state) {
            case IGroup.GROUP_GONE:
                mGroupViewVisiblity = false;
                break;
            case IGroup.GROUP_VISIBLE:
                if (mGroupView.getTop() != 0) {
                    mGroupView.layout(0, 0, mGroupViewWidth, mGroupViewHeight);
                }
                indexAdapter.configGroupView(mGroupView, position);
                mGroupViewVisiblity = true;
                break;
            case IGroup.GROUP_UP:
//                View firstView = getChildAt()
                break;
        }
    }
}
