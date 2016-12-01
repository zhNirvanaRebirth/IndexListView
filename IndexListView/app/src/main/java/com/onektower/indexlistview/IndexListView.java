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
    //左侧分组栏宽高
    private static final float GROUP_VIEW_WIDTH = 50;
    private static final float GROUP_VIEW_HEIGHT = 50;
    //索引栏宽高
    private static final float INDEX_VIEW_WIDTH = 10;
    private static final float INDEX_VIEW_HEIGHT = 400;
    private Context mContext;
    //需要的几个View
    private View mGroupView;//左侧分组View
    private View mIndexView;//索引栏View
    //左侧分组栏默认宽高
    private int mGroupViewWidthDef;
    private int mGroupViewHeightDef;
    //索引栏默认宽高
    private int mIndexViewWidthDef;
    private int mIndexViewHeightDef;

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
        mGroupViewWidthDef = typedArray.getInt(R.styleable.IndexListView_groupViewWidth, dp2px(GROUP_VIEW_WIDTH));
        mGroupViewHeightDef = typedArray.getInt(R.styleable.IndexListView_groupViewHeight, dp2px(GROUP_VIEW_HEIGHT));
        mIndexViewWidthDef = typedArray.getInt(R.styleable.IndexListView_indexViewWidth, dp2px(INDEX_VIEW_WIDTH));
        mIndexViewHeightDef = typedArray.getInt(R.styleable.IndexListView_indexViewHeight, dp2px(INDEX_VIEW_HEIGHT));

        typedArray.recycle();
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        indexAdapter = (IndexAdapter) adapter;
        super.setAdapter(adapter);
    }

    private int dp2px(float dp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }
}
