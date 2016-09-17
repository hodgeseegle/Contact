package com.example.myadaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.PhoneTypeEntity;
import com.example.lzw.myproject.R;

import java.util.ArrayList;

/**
 * Created by lzw on 2016/8/24.
 */
public class PhoneTypeAdapter extends BaseAdapter {
    private ArrayList<PhoneTypeEntity> list;
    Context context;

    public PhoneTypeAdapter(Context contex, ArrayList list){
        this.context = contex;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明ViewHolder
        ViewHolder holder = null;
        //若为第一次显示当前item
        if (convertView == null){
            //获取布局加载器
            LayoutInflater inflater = LayoutInflater.from(context);
            //使用布局加载器加载item布局
            convertView = inflater.inflate(R.layout.homepage_model,null);
            //保存ViewHolder的状态，存在当前converView中
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            holder.textView = (TextView)convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }
        //否则
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        //从数据源中装载数据
        holder.tv_name.setText(list.get(position).getTypeName());
        return convertView;
    }

    /**
     * 用于优化性能的，保存Item布局中的控件
     */
    public  class ViewHolder{
        public TextView tv_name;//电话类型名称
        public ImageView imageView;//右箭头
        public  TextView textView;//横线
    }
}
