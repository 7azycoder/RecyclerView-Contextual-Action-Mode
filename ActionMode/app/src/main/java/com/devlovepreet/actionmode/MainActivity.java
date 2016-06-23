package com.devlovepreet.actionmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{
    boolean is_in_action_mode =false;
    TextView counter_text_view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Toolbar toolbar;
    int[] img_id = {R.drawable.avengers,R.drawable.bajrangi,R.drawable.blackhat,R.drawable.dragons,R.drawable.frankstein,
            R.drawable.ninja,R.drawable.oculus,R.drawable.water,R.drawable.ongbak,R.drawable.oups,R.drawable.oculus,R.drawable.water,R.drawable.ongbak,R.drawable.oups};
    ArrayList<Contact> contacts = new ArrayList<>();
    ArrayList<Contact> selection_list = new ArrayList<>();
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        counter_text_view = (TextView) findViewById(R.id.counter_text);
        counter_text_view.setVisibility(View.GONE);

        String[] Name,Email;
        Name = getResources().getStringArray(R.array.name);
        Email = getResources().getStringArray(R.array.email);
        int i=0;
        for(String NAME : Name)
        {
            Contact contact = new Contact(img_id[i],NAME,Email[i]);
            contacts.add(contact);
            i++;
        }
        adapter = new ContactAdapter(contacts,MainActivity.this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        toolbar.getMenu().clear();//clear mainactivity menu
        toolbar.inflateMenu(R.menu.menu_action_mode);//inflate action mode menu
        counter_text_view.setVisibility(View.VISIBLE); //make textView visible on it
        is_in_action_mode = true;//make checkbox visible
        adapter.notifyDataSetChanged();//notify adapter about this  change
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void prepareSelection(View view,int position)
    {
        if(((CheckBox)view).isChecked())
        {
            selection_list.add(contacts.get(position));
            counter = counter + 1;
            updateCounter(counter);
        }
        else
        {
            selection_list.remove(contacts.get(position));
            counter = counter - 1;
            updateCounter(counter);
        }

    }

    public void updateCounter(int counter)
    {
        if(counter==0)
        {
            counter_text_view.setText("0 item selected");
        }
        else
        {
            counter_text_view.setText(counter+" items selected");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item_delete)
        {
            ContactAdapter contactAdapter = (ContactAdapter) adapter;
            contactAdapter.updateAdapter(selection_list);
            clearActionMode();
        }
        else if(item.getItemId()== android.R.id.home)
        {
            clearActionMode();
            adapter.notifyDataSetChanged();
        }
        return true;
    }
    public void clearActionMode()
    {
        is_in_action_mode = false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counter_text_view.setVisibility(View.GONE);
        counter_text_view.setText(" 0 item selected");
        counter = 0;
        selection_list.clear();
    }
}
