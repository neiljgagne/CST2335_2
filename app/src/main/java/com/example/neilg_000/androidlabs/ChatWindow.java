package com.example.neilg_000.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ChatWindow extends Activity {
    protected static final String ACTIVITY_NAME = "ChatWindow";

    ListView lv;
    EditText et;
    Button b;
    ChatAdapter messageAdapter;

    ArrayList<String> messageArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        b = (Button) findViewById(R.id.b1);
        et = (EditText) findViewById(R.id.ed1);
        lv =  (ListView) findViewById(R.id.daList);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               messageArrayList.add(et.getText().toString());
               messageAdapter.notifyDataSetChanged();
               et.setText("");
            }
        });

        messageAdapter = new ChatAdapter( this );
        lv.setAdapter(messageAdapter);
    }

    protected class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context c){
           super(c, 0);
        }

        public int getCount(){
            return messageArrayList.size();
        }

        public String getItem(int position){
            return messageArrayList.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result;

            if(position % 2 == 0){
                result = inflater.inflate(R.layout.chat_row_incoming, null);
                //Toast.makeText(getApplicationContext(), "incoming", Toast.LENGTH_SHORT).show();
            }

            else{
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
                //Toast.makeText(getApplicationContext(), "outgoing", Toast.LENGTH_SHORT).show();
            }

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;
        }
    }
}