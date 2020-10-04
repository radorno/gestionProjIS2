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

import com.is2.web.app.models.dao.IUsuarioDao;
import com.is2.web.app.models.entity.Usuario;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class IndexControllers {

    @Autowired
    private IUsuarioDao usuarioDao;

    @GetMapping({"/index", "/"})
    public String index(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            Usuario usuario = new Usuario();

            model.put("usuario", usuario);
            model.put("error", "");
            return "index";
        } else {
            return "administrador/home";
        }
    }
    
    @GetMapping({"/logout"})
    public String logout(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            Usuario usuario = new Usuario();

            model.put("usuario", usuario);
            model.put("error", "");
            return "administrador/home";
        } else {
            Usuario usuarioSesion = null;
            session.setAttribute("user",usuarioSesion);
            Usuario usuario = new Usuario();

            model.put("usuario", usuario);
            model.put("error", "");
            return "index";
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String validarUsuario(@Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {

        if (usuarioDao.validarUser(usuario)) {
            session.setAttribute("user", usuarioDao.findUser(usuario.getUserCode()));
            return "administrador/home";

        } else {
            model.addAttribute("error", "Usuario invalido");
            return "index";

        }

    }

}
