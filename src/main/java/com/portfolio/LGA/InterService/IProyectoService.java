package com.portfolio.LGA.InterService;

import com.portfolio.LGA.dto.ProyectoDto;
import com.portfolio.LGA.model.Proyecto;

import java.util.List;

public interface IProyectoService {
    public List<ProyectoDto> verProyecto();

    public void crearProyecto(ProyectoDto proyectoDto);

    public void borrarProyecto(Long id);

    public Proyecto buscarProyecto(Long id);

    public Proyecto editarProyecto(ProyectoDto proyectoDto);
}
