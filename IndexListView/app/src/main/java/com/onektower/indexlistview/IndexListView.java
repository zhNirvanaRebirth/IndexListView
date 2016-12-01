package com.onektower.indexlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by zhwilson on 2016/12/1.
 * 模仿手机联系人界面的listview
 */
public class IndexListView extends ListView {
    //需要的几个View
    private View mGroupView;//左侧分组View
    private View mIndexView;//索引栏View

    public IndexListView(Context context) {
        this(context, null);
    }

    public IndexListView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.IndexListViewDefStyle);
    }

    public IndexListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
