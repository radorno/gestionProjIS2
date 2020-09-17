package com.is2.web.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.is2.web.app.models.dao.IProyectoDao;
import com.is2.web.app.models.dao.IRolDao;
import com.is2.web.app.models.dao.ITareaDao;
import com.is2.web.app.models.dao.IUsuarioDao;
import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Tarea;
import com.is2.web.app.models.entity.Usuario;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/app")
public class DesarrolladorController {

    @Autowired
    private IProyectoDao proyectoDao;
    @Autowired
    private ITareaDao tareaDao;
    @Autowired
    private IUsuarioDao usuarioDao;
    private String codeProyect;
    private String codeTarea;
    @GetMapping({"/desarrollo"})
    public String menuDesarrollo() {
        return "desarrollador/desarrollo";
    }

    @GetMapping({"/desarrollo/gestionProyecto"})
    public String gestionProyecto(Model model) {
        return "desarrollador/gestionProyecto";
    }

    @GetMapping({"/desarrollo/gestionTarea"})
    public String gestionTarea(Model model) {
        return "desarrollador/gestionTarea";
    }

    @GetMapping({"/desarrollo/crearProyecto"})
    public String crearProyecto(Map<String, Object> model) {
        Proyecto proyecto = new Proyecto();
        model.put("proyecto", proyecto);
        model.put("error", "");
        return "desarrollador/crearProyecto";

    }

    @RequestMapping(value = "/desarrollo/crearProyecto", method = RequestMethod.POST)
    public String guardarProyecto(@Valid Proyecto proyecto, BindingResult result, Model model) {

        Usuario usuario = new Usuario();
        usuario = usuarioDao.findUser(proyecto.getAdmin());
        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            return "desarrollador/crearProyecto";
        }

        if (usuario == null) {
            model.addAttribute("error", "error Admin no existe en base de datos");
            return "desarrollador/crearProyecto";
        }

        if (proyectoDao.findProyecto(proyecto.getCodigoProyecto()) != null) {

            if (proyecto.getId() == 0) {
                model.addAttribute("error", "error Proyecto ya existe dentro de la base de datos");
                return "desarrollador/crearProyecto";
            } else {
                proyectoDao.save(proyecto);
                model.addAttribute("error", "Proyecto creado con exito");
                return "desarrollador/crearProyecto";
            }
        } else {

            proyectoDao.save(proyecto);
            model.addAttribute("error", "Proyecto creado con exito");
            return "desarrollador/crearProyecto";
        }

    }

    @GetMapping({"/desarrollo/eliminarProyecto/{codigoProyecto}"})
    public String eliminarProyecto(@PathVariable("codigoProyecto") String codigoProyecto, Map<String, Object> model, Model models) {
        Proyecto proyecto = proyectoDao.findProyecto(codigoProyecto);
        proyectoDao.removeProyecto(proyecto);
        model.put("proyecto", proyectoDao.findAll());
        return "desarrollador/verProyectos";
    }

    @GetMapping({"/desarrollo/modificarProyecto/{codigoProyecto}"})
    public String modificarProyecto(@PathVariable("codigoProyecto") String codigoProyecto, Map<String, Object> model) {
        Proyecto proyecto = null;
        proyecto = proyectoDao.findProyecto(codigoProyecto);
        model.put("proyecto", proyecto);
        model.put("error", "");
        return "desarrollador/modificarProyecto";
    }

    @RequestMapping(value = "/desarrollo/modificarProyecto", method = RequestMethod.POST)
    public String modificarProyecto(@Valid Proyecto proyecto, BindingResult result, Model model, Map<String, Object> models) {

        Usuario usuario = new Usuario();
        usuario = usuarioDao.findUser(proyecto.getAdmin());
        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            return "desarrollador/modificarProyecto";
        }

        if (usuario == null) {
            model.addAttribute("error", "error Admin no existe en base de datos");
            return "desarrollador/modificarProyecto";
        }

        if (proyectoDao.findProyecto(proyecto.getCodigoProyecto()) != null) {

            if (proyecto.getId() == 0) {
                model.addAttribute("error", "error Proyecto ya existe dentro de la base de datos");
                return "desarrollador/modificarProyecto";
            } else {
                proyectoDao.save(proyecto);
                model.addAttribute("error", "Proyecto modificado con exito");
                models.put("proyectos", proyectoDao.findAll());
                return "desarrollador/verProyectos";
            }
        } else {

            proyectoDao.save(proyecto);
            model.addAttribute("error", "Proyecto modificado con exito");
            models.put("proyectos", proyectoDao.findAll());
            return "desarrollador/verProyectos";
        }

    }

    @GetMapping({"/desarrollo/verProyectos"})
    public String verProyectos(Map<String, Object> model) {

        model.put("proyectos", proyectoDao.findAll());
        return "desarrollador/verProyectos";
    }

    @GetMapping({"/desarrollo/eliminarTarea/{codigoTarea}"})
    public String eliminarTarea(@PathVariable("codigoTarea") String codigoTarea, Map<String, Object> model, Model models) {
        Tarea tarea = tareaDao.findTarea(codigoTarea);
        tareaDao.removeTarea(tarea);
        model.put("tareas", tareaDao.findAll());
        return "desarrollador/verTareas";
    }

    @GetMapping({"/desarrollo/modificarTarea/{codigoTarea}"})
    public String modificarTarea(@PathVariable("codigoTarea") String codigoTarea, Map<String, Object> model) {
        Tarea tarea = null;
        tarea = tareaDao.findTarea(codigoTarea);
        model.put("tarea", tarea);
        model.put("error", "");
        return "desarrollador/modificarTarea";
    }

  
//        if(!tarea.getCodLineaBase().isEmpty()){
//                    model.addAttribute("error", "error Tarea bloqueada no puede modificarse");
//                    models.put("tarea",tarea);
//                    return "desarrollador/modificarTarea";
//                }

    @RequestMapping(value = "/desarrollo/modificarTarea", method = RequestMethod.POST)
    public String modificarTarea(@Valid Tarea tarea, BindingResult result, Model model, Integer valid, Map<String, Object> models) {
 

        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            return "desarrollador/modificarTarea";
        }

        if (tareaDao.findTarea(tarea.getCodigoTarea()) != null) {
            if (tarea.getId() == 0) {
                model.addAttribute("error", "error Tarea ya existe dentro de la base de datos");
                return "desarrollador/modificarTarea";
            } else {
                Tarea tareaNueva = tareaDao.findTarea(tarea.getCodigoTarea());
                tareaNueva.setCodigoTarea(tarea.getCodigoTarea());
                tareaNueva.setPrioridad(tarea.getPrioridad());
                tareaNueva.setEstado(tarea.getEstado());
                tareaNueva.setVersion(tarea.getVersion());
                tareaNueva.setDescripcion(tarea.getDescripcion());
                tareaNueva.setObservacion(tarea.getObservacion());
                if(tareaNueva.getCodLineaBase() != null){
                    model.addAttribute("error", "error Tarea bloqueada no puede modificarse");
                    models.put("tarea",tarea);
                    return "desarrollador/modificarTarea";
                }
                
                tareaDao.save(tareaNueva);
                models.put("tareas", tareaDao.findAll());
                return "desarrollador/verTareas";
            }
        } else {
            Tarea tareaNueva = tareaDao.findTarea(tarea.getCodigoTarea());
            tareaNueva.setCodigoTarea(tarea.getCodigoTarea());
            tareaNueva.setPrioridad(tarea.getPrioridad());
            tareaNueva.setEstado(tarea.getEstado());
            tareaNueva.setVersion(tarea.getVersion());
            tareaNueva.setDescripcion(tarea.getDescripcion());
            tareaNueva.setObservacion(tarea.getObservacion());
                if(tareaNueva.getCodLineaBase() != null){
                    model.addAttribute("error", "error Tarea bloqueada no puede modificarse");
                    models.put("tarea",tarea);
                    return "desarrollador/modificarTarea";
                }
            
            tareaDao.save(tareaNueva);
            models.put("tareas", tareaDao.findAll());
            return "desarrollador/verTareas";
        }

    }

    @GetMapping({"/desarrollo/verTareas"})
    public String verTareas(Map<String, Object> model) {

        model.put("tareas", tareaDao.findAll());
        return "desarrollador/verTareas";
    }

    @GetMapping({"/desarrollo/verTareas/proyecto/{codigoProyecto}"})
    public String verTareas(@PathVariable("codigoProyecto") String codigoProyecto, Map<String, Object> model) {

        model.put("tareas", tareaDao.findProyecto(codigoProyecto));
        return "desarrollador/verTareasProyecto";
    }

    @GetMapping({"/desarrollo/crearTarea"})
    public String crearTarea(Map<String, Object> model) {
        Tarea tarea = new Tarea();

        model.put("tarea", tarea);
        model.put("error", "");
        return "desarrollador/crearTarea";
    }

    @RequestMapping(value = "/desarrollo/crearTarea", method = RequestMethod.POST)
    public String guardarTarea(@Valid Tarea tarea, BindingResult result, Model model, Integer valid) {

        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            return "desarrollador/crearTarea";
        }

        if (tareaDao.findTarea(tarea.getCodigoTarea()) != null) {
            if (tarea.getId() == 0) {
                model.addAttribute("error", "error Tarea ya existe dentro de la base de datos");
                return "desarrollador/crearTarea";
            } else {
                tareaDao.save(tarea);
                model.addAttribute("error", "Tarea creada con exito");
                return "desarrollador/crearTarea";
            }
        } else {

            tareaDao.save(tarea);
            model.addAttribute("error", "Tarea creada con exito");
            return "desarrollador/crearTarea";
        }

    }

    @GetMapping({"/desarrollo/asignarTarea/{codigoProyecto}"})
    public String asignarTarea(@PathVariable("codigoProyecto") String codigoProyecto, Map<String, Object> model) {

        codeProyect = codigoProyecto;
        Proyecto proyecto = proyectoDao.findProyecto(codigoProyecto);
        model.put("tareas", tareaDao.findAll());
        model.put("message", "Asinar tarea a proyecto : " + proyecto.getCodigoProyecto());
        return "desarrollador/asignarTarea";

    }

    @GetMapping({"/desarrollo/asignarTarea/tarea/{codigoTarea}"})
    public String asignarTareas(@PathVariable("codigoTarea") String codigoTarea, Map<String, Object> model) {

        Tarea tarea = tareaDao.findTarea(codigoTarea);
        tarea.setCodigoProyecto(codeProyect);
        tareaDao.save(tarea);
        model.put("message", "Tarea asignada con exito");
        model.put("proyectos", proyectoDao.findAll());
        return "desarrollador/verProyectos";

    }

    
    
    
    @GetMapping({"/desarrollo/conectarTarea/{codigoTarea}"})
    public String conectarTarea(@PathVariable("codigoTarea") String codigoTarea, Map<String, Object> model) {

        codeTarea = codigoTarea;
        Tarea tarea = tareaDao.findTarea(codigoTarea);
        model.put("tareas", tareaDao.findTareasPadre(codigoTarea));
        model.put("message", "Conectar tarea hija a tare Padre : " + tarea.getCodigoTarea());
        return "desarrollador/conectarTarea";

    }
    
    @GetMapping({"/desarrollo/tarea/conectarTarea/{codigoTarea}"})
    public String conectarTareas(@PathVariable("codigoTarea") String codigoTarea, Map<String, Object> model) {

        Tarea tarea = tareaDao.findTarea(codeTarea);
        tarea.setTareaPadre(codigoTarea);
        tareaDao.save(tarea);
        model.put("message", "Tarea conectada con exito");
        model.put("tareas", tareaDao.findAll());
        return "desarrollador/verTareas";

    }
    
    

}
