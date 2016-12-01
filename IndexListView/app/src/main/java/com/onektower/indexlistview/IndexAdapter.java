package com.onektower.indexlistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by zhwilson on 2016/12/1.
 * 适配器
 */
public class IndexAdapter extends BaseAdapter implements IGroup {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getGroupState(int position) {
        return 0;
    }

    @Override
    public void configGroupView(View group, int position) {

    }
}
