package com.is2.web.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.is2.web.app.models.dao.IProyectoDao;
import com.is2.web.app.models.dao.IRolDao;
import com.is2.web.app.models.dao.IUsuarioDao;
import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/app")
public class AdminController {

    @Autowired
    private IRolDao rolDao;
    @Autowired
    private IUsuarioDao usuarioDao;

    @GetMapping({"/home"})
    public String home(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("user") != null) {
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/home";
            } else {
                return "administrador/homeDesa";
            }
        } else {
            Usuario usuario = new Usuario();

            model.put("usuario", usuario);
            model.put("error", "");
            return "index";
        }

    }

    @GetMapping({"/administrativo"})
    public String moduloAdministrativo(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("user") != null) {
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/administrativo";
            } else {
                return null;
            }

        } else {
            Usuario usuario = new Usuario();

            model.put("usuario", usuario);
            model.put("error", "");
            return "index";
        }

    }

    @GetMapping({"/administrativo/crearUsuarios"})
    public String crearUsuario(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("user") != null) {
            Usuario usuario = new Usuario();

            model.put("roles", rolDao.findAll());
            model.put("usuario", usuario);
            model.put("error", "");
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/crearUsuarios";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @RequestMapping(value = "/administrativo/crearUsuarios", method = RequestMethod.POST)
    public String guardarUsuario(@Valid Usuario usuario, BindingResult result, Model model, Map<String, Object> models) {

        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            models.put("roles", rolDao.findAll());
            return "administrador/crearUsuarios";
        }

        if (usuarioDao.findUser(usuario.getUserCode()) != null) {

            if (usuario.getId() == 0) {
                model.addAttribute("error", "error usuario ya existe dentro de la base de datos");
                models.put("roles", rolDao.findAll());
                return "administrador/crearUsuarios";
            } else {
                usuarioDao.save(usuario);
                return "administrador/gestionRoles";
            }
        } else {

            usuarioDao.save(usuario);
            return "administrador/gestionUsuario";
        }
    }

    @GetMapping({"/administrativo/modificarUsuario/{userCode}"})
    public String modificarUsuario(HttpSession session, @PathVariable("userCode") String userCode, Map<String, Object> model) {
        if (session.getAttribute("user") != null) {

            Usuario usuario = null;
            usuario = usuarioDao.findUser(userCode);
            model.put("roles", rolDao.findAll());
            model.put("usuario", usuario);
            model.put("error", "");
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/modificarUsuario";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @RequestMapping(value = "/administrativo/modificarUsuario", method = RequestMethod.POST)
    public String modificarUsuario(@Valid Usuario usuario, BindingResult result, Model model, Map<String, Object> models) {

        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            models.put("roles", rolDao.findAll());
            return "administrador/modificarUsuario";
        }

        if (usuarioDao.findUser(usuario.getUserCode()) != null) {

            if (usuario.getId() == 0) {
                model.addAttribute("error", "error usuario ya existe dentro de la base de datos");
                models.put("roles", rolDao.findAll());
                return "administrador/modificarUsuario";
            } else {
                usuarioDao.save(usuario);
                model.addAttribute("usuarios", usuarioDao.findAll());
                return "administrador/verUsuarios";
            }
        } else {

            usuarioDao.save(usuario);
            model.addAttribute("usuarios", usuarioDao.findAll());
            return "administrador/verUsuarios";
        }
    }

    @GetMapping({"/administrativo/modificarUsuarios"})
    public String modificarUsuario(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {

            Usuario usuario = new Usuario();

            model.put("roles", rolDao.findAll());
            model.put("usuario", usuario);
            model.put("error", "");
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/modificarUsuarios";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @GetMapping({"/administrativo/eliminarUsuario/{userCode}"})
    public String eliminarUsuario(HttpSession session, @PathVariable("userCode") String userCode, Map<String, Object> model, Model models) {
        if (session.getAttribute("user") != null) {
            Usuario usuario = usuarioDao.findUser(userCode);
            usuarioDao.removeUsuario(usuario);
            models.addAttribute("usuarios", usuarioDao.findAll());
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/verUsuarios";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @GetMapping({"/administrativo/verUsuarios"})
    public String verUsuarios(Model model, HttpSession session, Map<String, Object> models) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("usuarios", usuarioDao.findAll());
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/verUsuarios";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            models.put("usuario", usuarioSession);
            models.put("error", "");
            return "index";

        }
    }

    @GetMapping({"/administrativo/gestionUsuario"})
    public String gestionUsuario(HttpSession session, Model model, Map<String, Object> models) {
        if (session.getAttribute("user") != null) {
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/gestionUsuario";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            models.put("usuario", usuarioSession);
            models.put("error", "");
            return "index";

        }
    }

    @GetMapping({"/administrativo/gestionRoles"})
    public String gestionRoles(HttpSession session, Model model, Map<String, Object> models) {
        if (session.getAttribute("user") != null) {
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/gestionRoles";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            models.put("usuario", usuarioSession);
            models.put("error", "");
            return "index";

        }

    }

    @GetMapping({"/administrativo/crearRoles"})
    public String crearRoles(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("user") != null) {
            Rol rol = new Rol();
            model.put("rol", rol);
            model.put("error", "");
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/crearRoles";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }

    }

    @RequestMapping(value = "/administrativo/crearRoles", method = RequestMethod.POST)
    public String guardarRoles(@Valid Rol rol, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            return "administrador/crearRoles";
        }

        if (rolDao.findRol(rol.getNombreRol()) != null) {
            if (rol.getId() == 0) {
                model.addAttribute("error", "error Rol ya existe dentro de la base de datos");
                return "administrador/crearRoles";
            } else {
                rolDao.save(rol);
                return "administrador/gestionRoles";
            }
        } else {

            rolDao.save(rol);
            return "administrador/gestionRoles";
        }

    }

    @GetMapping({"/administrativo/verRoles"})
    public String verRoles(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.put("roles", rolDao.findAll());
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/verRoles";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @GetMapping({"/administrativo/eliminarRol/{nombreRol}"})
    public String eliminarRol(@PathVariable("nombreRol") String nombreRol, HttpSession session, Map<String, Object> model, Model models) {
        if (session.getAttribute("user") != null) {
            Rol rol = rolDao.findRol(nombreRol);
            rolDao.removeRol(rol);
            model.put("roles", rolDao.findAll());
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/verRoles";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }
    }

    @GetMapping({"/administrativo/modificarRol/{nombreRol}"})
    public String modificarRol(@PathVariable("nombreRol") String nombreRol, HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("user") != null) {
            Rol rol = null;
            rol = rolDao.findRol(nombreRol);
            model.put("rol", rol);
            model.put("error", "");
            model.put("roles", rolDao.findAll());
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/modificarRol";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }

    }

    @RequestMapping(value = "/administrativo/modificarRol", method = RequestMethod.POST)
    public String modificarRol(@Valid Rol rol, BindingResult result, Model model, Map<String, Object> models) {

        if (result.hasErrors()) {
            model.addAttribute("error", "error volver a cargar campos");
            return "administrador/modificarRol";
        }

        if (rolDao.findRol(rol.getNombreRol()) != null) {
            if (rol.getId() == 0) {
                model.addAttribute("error", "error Rol ya existe dentro de la base de datos");
                return "administrador/modificarRol";
            } else {
                rolDao.save(rol);
                models.put("roles", rolDao.findAll());
                return "administrador/verRoles";
            }
        } else {

            rolDao.save(rol);
            models.put("roles", rolDao.findAll());
            return "administrador/verRoles";
        }

    }

    @GetMapping({"/administrativo/asignarRol/{userCode}"})
    public String asignarRol(@PathVariable("userCode") String userCode, Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            Usuario usuario = usuarioDao.findUser(userCode);

            model.put("roles", rolDao.findAll());
            model.put("usuario", usuario);
            model.put("error", "");
            model.put("title", "Asignar Rol a Usuario : " + usuario.getUserCode());
            Usuario user = (Usuario) session.getAttribute("user");
            if (user.getNombreRol().equals("Administrador") || user.getNombreRol().equals("Admin. y Desarrollador")) {
                return "administrador/asignarRol";
            } else {
                return null;
            }

        } else {
            Usuario usuarioSession = new Usuario();

            model.put("usuario", usuarioSession);
            model.put("error", "");
            return "index";

        }

    }

    @RequestMapping(value = "/administrativo/asignarRol", method = RequestMethod.POST)
    public String asignarRol(Usuario usuarioNuevo, Map<String, Object> model, Model models) {

        Usuario usuario = null;
        Rol rol = null;
        usuario = usuarioDao.findUser(usuarioNuevo.getUserCode());
        rol = rolDao.findRol(usuarioNuevo.getNombreRol());
        if (usuario == null) {
            models.addAttribute("error", "error Usuario no existe");
            return "administrador/asignarRol";
        } else if (rol == null) {
            models.addAttribute("error", "error Rol no existe");
            return "administrador/asignarRol";
        } else {
            usuario.setNombreRol(usuarioNuevo.getNombreRol());
            model.put("error", "Rol asignado con exito");
            usuarioDao.save(usuario);
            models.addAttribute("usuarios", usuarioDao.findAll());
            return "administrador/verUsuarios";
        }
    }

}
