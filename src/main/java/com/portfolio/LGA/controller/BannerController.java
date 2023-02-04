package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IBannerService;
import com.portfolio.LGA.dto.BannerDto;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.model.Banner;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banner")
@CrossOrigin(origins = {"https://lga-portfolio.web.app", "http://localhost:4200"})
public class BannerController {
    private final IBannerService bannerService;
    private final ModelMapper modelMapper;

    @Autowired
    public BannerController(IBannerService bannerService, ModelMapper modelMapper) {
        this.bannerService = bannerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<BannerDto>> verBanner() {
        List<BannerDto> bannersDtos = bannerService.verBanner();
        return new ResponseEntity(bannersDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<BannerDto> agregarBanner(@RequestBody BannerDto bannerDto) {
        if (StringUtils.isBlank(bannerDto.getNombreUrl())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        bannerService.crearBanner(bannerDto);
        return new ResponseEntity(new Mensaje("Banner creado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<BannerDto> editarBanner(@RequestBody BannerDto bannerDto) {
        bannerService.editarBanner(bannerDto);
        return new ResponseEntity(new Mensaje("Banner editado"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Banner> buscarBanner(@PathVariable Long id) {
        if (bannerService.buscarBanner(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(bannerService.buscarBanner(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> borrarBanner(@PathVariable Long id) {
        bannerService.borrarBanner(id);
        return new ResponseEntity(new Mensaje("Banner borrado"), HttpStatus.OK);
    }
}
