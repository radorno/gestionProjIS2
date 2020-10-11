/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is2.web.app.models.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author justo
 */
public class Tareas {
    
    private List<Tarea> tareas = new ArrayList<>();

    public Tareas() {
    }
    
    public Tareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
    
    
    
}
