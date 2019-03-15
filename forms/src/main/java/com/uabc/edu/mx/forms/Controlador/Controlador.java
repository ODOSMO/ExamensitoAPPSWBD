package com.uabc.edu.mx.forms.Controlador;

import com.uabc.edu.mx.forms.Modelo.Citas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Controlador {
    private List<String> valores = new ArrayList<>();
    private List<String> citasFiltradas = new ArrayList<>();
    private List<Citas> citasList = new ArrayList<>();

    @RequestMapping(path = "/lista")
    public String lista(Model model){
        model.addAttribute("lista", valores);
        model.addAttribute("consulta", citasFiltradas);
        return "lista";
    }

    @PostMapping("/filtrar")
    public String filtrar(HttpServletRequest request, @RequestParam(value="calendar", required=false) String fechaC){
        citasFiltradas.clear();
           for(Citas cita : citasList){
                if (cita.getFecha().equals(fechaC)){
                    citasFiltradas.add("Consulta sobre "+cita.getAsunto() +" programada para las "+ cita.getHora() +" Hrs, el dia "+ cita.getFecha() +" con el DR. "+ cita.getDoctor());
                }
           }
           return "redirect:/lista";
    }

    @PostMapping("/add")
    public String add(HttpServletRequest request,
                      @RequestParam(value="asunto", required=false) String asunto,
                      @RequestParam(value="hora", required=false) String hora,
                      @RequestParam(value="fecha", required=false) String fecha,
                      @RequestParam(value="doctor", required=false) String doctor){
        Citas cita = new Citas();
        cita.setAsunto(asunto);
        cita.setHora(hora);
        cita.setFecha(fecha);
        cita.setDoctor(doctor);
        citasList.add(cita);

        valores.add("Consulta sobre "+asunto +" programada para las "+ hora +" Hrs, el dia "+ fecha +" con el DR. "+ doctor);
        return "redirect:/lista";
    }
}
