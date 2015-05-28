package com.laura.autom;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.zip.Inflater;

/* Estaba intentando que cuando presione el botón, se abra la vista de Wifi (layout.wifi)
para poder seleccionar las condiciones que quieres (si no pones algo en un campo se interpreta
que no quieres poner condición en ese campo), guardar esa condición y ver si se cumple.
Si no entiendes algo avisa

IDEAS: HACER WIFI EXTENDS FRAGMENT (Hacer cada servicio aparte como fragmento??)
 */


public class MainActivity extends ActionBarActivity {

    LinkedList<Perfil> lista = new LinkedList<Perfil>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.addButton);

        Button guardarButton = (Button) findViewById(R.id.guardarButton);
        Log.d("MainActivity", "onCreate - Adapter");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick - addButton");
            }
        });
    }

    public void procesar(){
        GridView gridView = (GridView) findViewById(R.id.gridPerfiles);
        ListAdapter adapter = new AdapterPerfil(this, lista);
        gridView.setAdapter(adapter);

        if(!lista.isEmpty()){
            Perfil perf = lista.getFirst();
            Toast.makeText(this, "Se cumple? " + perf.seCumple(), Toast.LENGTH_SHORT).show();}
    }

    public void guardar(View v){

        EditText ssidEdit = (EditText) findViewById(R.id.ssidEdit);
        EditText bssidEdit = (EditText) findViewById(R.id.bssidEdit);
        EditText macEdit = (EditText) findViewById(R.id.macEdit);
        EditText ipEdit = (EditText) findViewById(R.id.ipEdit);

        String ssid = ssidEdit.getText().toString();
        String bssid = bssidEdit.getText().toString();
        String mac = macEdit.getText().toString();
        String ip = ipEdit.getText().toString();

        LinkedList<Condicion> cond = new LinkedList<Condicion>();
        cond.add(new Wifi(getApplicationContext(), ssid, bssid, mac, ip));
        lista.add(new Perfil(null, cond));
        getLayoutInflater().inflate(R.layout.activity_main, null);
        procesar();
    }
}
