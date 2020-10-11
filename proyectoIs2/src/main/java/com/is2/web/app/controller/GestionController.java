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
import com.is2.web.app.models.dao.ILineaBaseDao;
import com.is2.web.app.models.dao.ITareaDao;
import com.is2.web.app.models.entity.LineaBase;
import com.is2.web.app.models.entity.Tarea;
import com.is2.web.app.models.entity.Tareas;
import com.is2.web.app.models.entity.Usuario;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app")
public class GestionController {

    @Autowired
    private ITareaDao tareaDao;
    @Autowired
    private ILineaBaseDao lineaBaseDao;
    private Long codeLineaBase;
    
    @GetMapping({"/gestion"})
    public String menuGestion(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("user") != null) {
            Usuario user = (Usuario) session.getAttribute("user");
            if(user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")){
                return "gestion/moduloGestion";
            }else{
                return null; 
            }
            
        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }

    }

    @GetMapping({"/gestion/crearLineaBase"})
    public String crearLineaBase(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            LineaBase lineaBase = new LineaBase();
            
            model.put("error", "");
            model.put("lineaBase", lineaBase);
            Usuario user = (Usuario) session.getAttribute("user");
            if(user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")){
                return "gestion/crearLineaBase";
            }else{
                return null; 
            }
            
        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }

    }

    @RequestMapping(value = "/gestion/crearLineaBase", method = RequestMethod.POST)
    public String crearLineaBase(@Valid LineaBase lineaBase, BindingResult result, Model model,Map<String, Object> models) {

        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            
            return "gestion/crearLineaBase";
        }

        if (lineaBaseDao.findLineaBase(lineaBase.getCodigo()) != null) {

            if (lineaBase.getId() == 0) {
                model.addAttribute("error", "error LineaBase ya existe dentro de la base de datos");
                return "gestion/crearLineaBase";
            } else {
              
                lineaBaseDao.save(lineaBase);
                codeLineaBase = lineaBaseDao.findLineaBase(lineaBase.getCodigo()).getId();
                models.put("tareas",tareaDao.findNoBloqueadas());
                model.addAttribute("error", "LineaBase creada con exito");
                return "gestion/seleccionar_tareas";
            }
        } else {

            
            lineaBaseDao.save(lineaBase);
            codeLineaBase = lineaBaseDao.findLineaBase(lineaBase.getCodigo()).getId();
            models.put("tareas",tareaDao.findNoBloqueadas());
            model.addAttribute("error", "LineaBase creado con exito");
            return "gestion/seleccionar_tareas";
        }

    }
    
    @RequestMapping(value = "/gestion/seleccionar_tareas", method = RequestMethod.POST)
    public String crearLineaBaseOk(@RequestParam(value = "selectedTareas")  String[] tareas, Model model,Map<String, Object> models) {

        Tarea newTarea = new Tarea();
        LineaBase newLineaBase = lineaBaseDao.findOne(codeLineaBase);
        for(int i = 0; i < tareas.length ; i++ ){
        
            newTarea = tareaDao.findTarea(tareas[i]);
            newTarea.setCodLineaBase(newLineaBase.getCodigo());
            tareaDao.save(newTarea);
        }
        
        
        model.addAttribute("error", "Linea Base creada con exito");
        models.put("lineaBase", new LineaBase());    
        return "gestion/crearLineaBase";
    }
    
    
    
    @GetMapping({"/gestion/listarlb"})
    public String menuLineaBase(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            Usuario user = (Usuario) session.getAttribute("user");
            if(user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")){
                return "gestion/listarlb";
            }else{
                return null; 
            }
            
        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @GetMapping({"/gestion/listar_tareas"})
    public String verLineaBaseTareas(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.put("tareas", tareaDao.findAll());
            Usuario user = (Usuario) session.getAttribute("user");
            if(user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")){
                return "gestion/listar_tareas";
            }else{
                return null; 
            }
            
        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    
    @GetMapping({"/gestion/verLineaBase"})
    public String verLineaBase(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.put("lineaBases", lineaBaseDao.findAll());
            
            return "gestion/verLineaBase";
            
            
        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }
//    @GetMapping({"/gestion/buscar_linea_base"})
//    public String buscarLineaBase(Map<String, Object> model, HttpSession session) {
//        if (session.getAttribute("user") != null) {
//            LineaBase lineaBaseNuevo = new LineaBase();
//            model.put("lineaBase", lineaBaseNuevo);
//            Usuario user = (Usuario) session.getAttribute("user");
//            if(user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")){
//                return "/gestion/buscar_linea_base";
//            }else{
//                return null; 
//            }
//            
//        } else {
//            Usuario usuarioSession = new Usuario();
//
//            model.put("usuario", usuarioSession);
//            model.put("error", "");
//            return "index";
//
//        }
//
//    }
//
//    @RequestMapping(value = "/gestion/buscar_linea_base", method = RequestMethod.POST)
//    public String modificarUsuario(LineaBase lineaBaseNuevo, Map<String, Object> model, Model models) {
//
//        LineaBase lineaBase = null;
//        lineaBase = lineaBaseDao.findLineaBase(lineaBaseNuevo.getCodigo());
//        if (lineaBase == null) {
//            models.addAttribute("error", "error Linea Base no existe");
//            return "gestion/buscar_linea_base";
//        } else {
//
//            model.put("tareas", tareaDao.findLineaBase(lineaBaseNuevo.getCodigo()));
//            model.put("error", "");
//
//            return "gestion/ver_linea_base";
//        }
//
//    }

    @GetMapping({"/gestion/eliminarlb"})
    public String eliminarLineaBase(Map<String, Object> model, Model models, HttpSession session) {
        if (session.getAttribute("user") != null) {
            LineaBase lineaBaseAeliminar = new LineaBase();
            model.put("lineaBase", lineaBaseAeliminar);
            models.addAttribute("error", "");
            Usuario user = (Usuario) session.getAttribute("user");
            if(user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")){
                return "/gestion/eliminarlb";
            }else{
                return null; 
            }
            
        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @RequestMapping(value = "/gestion/eliminarlb", method = RequestMethod.POST)
    public String modificarRoles(LineaBase lineaBaseAeliminar, Map<String, Object> model, Model models) {
        LineaBase lineaBase = null;
        lineaBase = lineaBaseDao.findLineaBase(lineaBaseAeliminar.getCodigo());

        if (lineaBase == null) {
            models.addAttribute("error", "error Linea base a Eliminar no existe");
            return "/gestion/eliminarlb";
        } else {
            models.addAttribute("error", " Linea base eliminada con exito");
            lineaBaseDao.removeLineaBase(lineaBase);
            tareaDao.desbloquearTarea(lineaBase.getCodigo());

            return "/gestion/eliminarlb";
        }

    }

    @GetMapping({"/gestion/agregar_tarea_lb"})
    public String conectarTarea(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            Tarea tarea = new Tarea();

            model.put("tarea", tarea);
            model.put("error", "");
            Usuario user = (Usuario) session.getAttribute("user");
            if(user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")){
                return "gestion/agregar_tarea_lb";
            }else{
                return null; 
            }
            
        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @RequestMapping(value = "gestion/agregar_tarea_lb", method = RequestMethod.POST)
    public String conectarTarea(Tarea tareaNuevo, Map<String, Object> model, Model models) {

        Tarea tarea = null;
        tarea = tareaDao.findTarea(tareaNuevo.getCodigoTarea());

        if (tarea == null) {
            models.addAttribute("error", "error Tarea no existe");
            
            return "gestion/agregar_tarea_lb";
        } else {
            tarea.setCodLineaBase(tareaNuevo.getCodLineaBase());
            tarea.setEstado("BLOQUEADO");
            tareaDao.save(tarea);
            model.put("error", "Tarea Bloqueada en Linea Base con exito");

            return "gestion/agregar_tarea_lb";
        }
    }

}
