package com.mafvazquez.noticiero.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mafvazquez.noticiero.model.Noticia;
import com.mafvazquez.noticiero.model.Usuario;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {

    @PostMapping(value = "/login")
    public ModelAndView login(Model model, Usuario usuario, HttpSession session){

        session.setAttribute("usuario", usuario);

        ModelAndView modelAndView = new ModelAndView("noticias/list");
        modelAndView.addObject("noticias", getNoticias());

        usuario.getUsuario();

        return modelAndView;
    }

    @GetMapping(value = {"/logout"})
    public ModelAndView logout(Model model, HttpSession session){

        session.invalidate();

        ModelAndView modelAndView = new ModelAndView("noticias/list");
        modelAndView.addObject("noticias", getNoticias());

        return modelAndView;
    }
    
    @GetMapping(value = "/list")
    public ModelAndView list(Model model){

        ModelAndView modelAndView = new ModelAndView("noticias/list");
        modelAndView.addObject("noticias", getNoticias());

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Noticia noticia) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticia", new Noticia());
        modelAndView.setViewName("noticias/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Noticia noticia){

        List<Noticia> noticias = getNoticias();
        noticias.add(noticia);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticia", getNoticia(codigo));
        modelAndView.setViewName("noticias/edit");

        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Noticia noticia) {

        List<Noticia> noticias = getNoticias();

        int indexOf = noticias.indexOf(noticia);

        noticias.set(indexOf, noticia);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");
        
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        List<Noticia> noticias = getNoticias();
        noticias.remove(getNoticia(codigo));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");

        return modelAndView;
    }

    private Noticia getNoticia(int codigo){

        List<Noticia> noticias = getNoticias();
        int indexOf = noticias.indexOf(new Noticia(codigo));

        return noticias.get(indexOf);
    }

    private List<Noticia> getNoticias() {
        
        ArrayList<Noticia> noticias = new ArrayList<Noticia>();

        noticias.add(new Noticia(1, "Pelea de ratas", "Dos ratas peleando por un roto con música de Linkin Park de fondo"));
        noticias.add(new Noticia(2, "Elon Musk destruye Twitter", "El CEO de Twitter, Elon Musk, ha llevado la red social a la bancarrota en menos de 2 meses"));

        return noticias;
    }
}
