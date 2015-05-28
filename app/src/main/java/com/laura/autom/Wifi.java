package com.laura.autom;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Laura on 25/05/2015.
 */
public class Wifi implements Condicion {

    String ssid, bssid, mac, ip;
    Context context;
    WifiManager wifiMgr;

    Wifi(Context context){
        super();
        this.context = context;
        wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    Wifi(Context context, String ssid, String bssid, String mac, String ip){
        this(context);
        if(ssid.equals("")) this.ssid = null;
        else this.ssid = ssid;

        if(bssid.equals("")) this.bssid = null;
        else this.ssid = ssid;

        if(mac.equals("")) this.mac = null;
        else this.ssid = ssid;

        if(ip.equals("")) this.ip  = null;
        else this.ssid = ssid;
        this.bssid = bssid;
        this.mac = mac;
        this.ip = ip;
    }

    private WifiInfo getInfo(){
        return wifiMgr.getConnectionInfo();
    }

    /**
     * Obtiene la IP del dispositivo
     * @return
     */
    private String getIp(){
        try {
            //Obtiene las interfaces del dispositivo
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()) {
                //Obtiene las IPs de cada interfaz
                Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inet = addresses.nextElement();
                    Toast.makeText(context,"Ip: "+inet.getHostAddress(),Toast.LENGTH_SHORT).show();
                    //Si alguna es válida, la devuelve
                    if (!inet.isLoopbackAddress() && inet instanceof Inet4Address)
                        return inet.getHostAddress();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean seCumple() {
        WifiInfo info = wifiMgr.getConnectionInfo();

        //Comprobamos que los campos a los que les hemos asignado un valor sean iguales
        //a los valores actuales de la red
        if(ip != null && !ip.equals(getIp())){
            return false;
        }
        if(ssid != null && !ssid.equals(info.getSSID())){
            return false;
        }
        if(bssid != null && !bssid.equals(info.getBSSID())) {
            return false;
        }
        if(mac != null && !mac.equals(info.getMacAddress())){
            return false;
        }

        //Si llegamos hasta aquí, es que se cumple la condición
        return true;
    }
}
