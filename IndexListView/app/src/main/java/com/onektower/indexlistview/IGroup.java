package com.onektower.indexlistview;

import android.view.View;

/**
 * Created by zhwilson on 2016/12/1.
 * 分组栏状态接口
 */
public interface IGroup {
    public static final int GROUP_GONE = 0;//不显示分组栏
    public static final int GROUP_VISIBLE = 1;//显示分组栏
    public static final int GROUP_UP = 2;//设置分组栏向上滑动

    public int getGroupState(int position);//获取分组栏状态

    public void configGroupView(View group, int position);
}
