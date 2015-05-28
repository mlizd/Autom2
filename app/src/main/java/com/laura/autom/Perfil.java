package com.laura.autom;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Clase que junta las tareas (acciones a ejecutar) y las condiciones (estados o acciones que se deben dar
 * para que una tarea se pueda llevar a cabo)
 * Created by Laura on 25/05/2015.
 */
public class Perfil {

    /* Creacion de atributos */
    private String nombre;
    private LinkedList<Tarea> tareas;               //Lista de tareas de las que consta el perfil
    private LinkedList<Condicion> condiciones;      //Lista de las condiciones que se tienen que cumplir
    private boolean enable = true;

    /**
     *  Constructor vacío
     */
    Perfil(){
        tareas = new LinkedList<Tarea>();
        condiciones = new LinkedList<Condicion>();
    }


    /**
     * Constructor que inicializa el nombre
     * @param nombre
     */
    Perfil(String nombre){
        this();
        this.nombre = nombre;
    }

    /**
     * Constructor que inicializa las listas de la clase
     * @param tareas Lista de tareas que ejecutará el perfil
     * @param condiciones Lista de condiciones a cumplir
     */
    Perfil(LinkedList<Tarea> tareas, LinkedList<Condicion> condiciones){
        this.tareas = tareas;
        this.condiciones = condiciones;
    }

    /**
     * Constructor que inicializa todos los atributos de la clase
     * @param nombre El nombre del perfil
     * @param tareas Lista de tareas que ejecutará el perfil
     * @param condiciones Lista de condiciones a cumplir
     */
    Perfil(String nombre, LinkedList<Tarea> tareas, LinkedList<Condicion> condiciones){
        this(tareas, condiciones);
        this.nombre = nombre;
    }

    /**
     * Añade una condición al perfil
     * @param cond Condición a añadir
     */
    public void addCondicion(Condicion cond){
        condiciones.add(cond);
    }

    /**
     * Añade una tarea al perfil
     * @param tarea Tarea a añadir
     */
    public void addTarea(Tarea tarea){
        tareas.add(tarea);
    }

    /**
     * Marca si este perfil está activado o no
     * @param enable
     */
    public void cambiarEnable(boolean enable){
        this.enable = enable;
    }

    /**
     * Comprueba si las condiciones del perfil se cumplen
     * @return true - si se cumplen todas las condiciones, false - en otro caso
     */
    public boolean seCumple(){

        //Solo se cumplen las condiciones si el perfil esta activo
        if(!enable)
            return false;

        boolean correcto = true;
        Iterator<Condicion> it = condiciones.iterator();
        while(it.hasNext()){
            correcto = correcto && it.next().seCumple();
        }
        return correcto;
    }

    /**
     * Ejecuta todas las tareas del perfil
     */
    public void ejecutar(){
        Iterator<Tarea> it = tareas.iterator();
        while(it.hasNext()){
            it.next().ejecutar();
        }
    }

    public String getNombre(){
        return nombre;
    }
}
