package com.example.hamiltontevince04;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {
    // BASE ID
    private static final int BASE_ID = 0X1011;
    // Reference to our owning screen (context)
    private Context mContext = null;
    // Reference to our collection
    private ArrayList<Person> mPerson = null;


    public PersonAdapter(Context context,ArrayList person){
        mContext = context;
        mPerson = person;
    }

    @Override
    public int getCount() {
        if(mPerson != null);
        return mPerson.size();
    }

    @Override
    public Object getItem(int pos) {
        if(mPerson != null && pos >= 0 & pos < mPerson.size()){
            return mPerson.get(pos);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        Person p = (Person)getItem(pos);
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_baseadapter,parent,false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }

        if(p != null){
            vh.tv_name.setText(p.getFirstName() +" "+ p.getLastName());
            vh.tv_birthDate.setText(p.getBirthday());
            vh.iv_userImage.setImageResource(p.getUserImage());

        }

        return convertView;
    }

    static class ViewHolder{
        TextView tv_name;
        TextView tv_birthDate;
        ImageView iv_userImage;

        public ViewHolder(View layout){
         tv_name = layout.findViewById(R.id.baseName_textView);
         tv_birthDate = layout.findViewById(R.id.baseBirthday_textView);

         iv_userImage = layout.findViewById(R.id.baseImage_imageView);
        }
    }
}
