package com.laura.autom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.LinkedList;

/**
 * Created by Laura on 25/05/2015.
 */
public class AdapterPerfil extends BaseAdapter {

    Context context;
    LinkedList<Perfil> lista;

    AdapterPerfil(Context context, LinkedList<Perfil> lista){
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btn;
        if(convertView == null){
            btn = new Button(context);
            btn.setActivated(true);
            btn.setLayoutParams(new GridView.LayoutParams(100, 55));
            btn.setPadding(8, 8, 8, 8);
            btn.setBackground(context.getResources().getDrawable(R.drawable.perfil_button));
            btn.setLongClickable(true);
        }
        else {
            btn = (Button) convertView;
        }

        Perfil perfil = (Perfil)getItem(position);
        btn.setText(perfil.getNombre());
        btn.setId(position);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Button "+v.getId()+" activado",Toast.LENGTH_SHORT).show();
            }
        });

        return btn;
    }
}

