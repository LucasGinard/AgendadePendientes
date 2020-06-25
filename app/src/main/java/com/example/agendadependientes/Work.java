package com.example.agendadependientes;

public class Work {

    public int id_work;
    public String nombre;
    public String fecha;
    public String desc;

    public Work(){

    }

    public Work(int id_work,String nombre, String fecha, String desc){
        this.nombre = nombre;
        this.fecha = fecha;
        this.desc = desc;
        this.id_work = id_work;
    }

    public int getId_work() {
        return id_work;
    }

    public void setId_work(int id_work) {
        this.id_work = id_work;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
