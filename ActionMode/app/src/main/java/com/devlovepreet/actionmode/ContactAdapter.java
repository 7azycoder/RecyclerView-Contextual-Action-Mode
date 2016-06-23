package com.devlovepreet.actionmode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devlovepreet on 23/6/16.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    ArrayList<Contact> adapter_list = new ArrayList<>();
    MainActivity mainActivity;
    Context ctx;

    public ContactAdapter(ArrayList<Contact> adapter_list,Context ctx)
    {
        this.adapter_list = adapter_list;
        this.ctx = ctx;
        mainActivity =(MainActivity) ctx;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view,mainActivity);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.img.setImageResource(adapter_list.get(position).getImg_id());
        holder.Name.setText(adapter_list.get(position).getName());
        holder.Email.setText(adapter_list.get(position).getEmail());
        if(!mainActivity.is_in_action_mode)
        {
            holder.checkBox.setVisibility(View.GONE);
        }
        else
        {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return adapter_list.size();
    }

    public  static  class  ContactViewHolder extends  RecyclerView.ViewHolder
    {
        ImageView img;
        TextView Name,Email;
        CheckBox checkBox;
        MainActivity mainActivity;
        public  ContactViewHolder(View itemView,MainActivity mainActivity)
        {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_id);
            Name = (TextView) itemView.findViewById(R.id.name);
            Email = (TextView) itemView.findViewById(R.id.email);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_list_item);
            this.mainActivity = mainActivity;
        }

    }
}
