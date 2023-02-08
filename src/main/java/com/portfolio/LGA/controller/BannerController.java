package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IBannerService;
import com.portfolio.LGA.dto.BannerDto;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.model.Banner;
import com.portfolio.LGA.model.Persona;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banner")
@CrossOrigin(origins = {"https://lga-portfolio.web.app", "http://localhost:4200"})
@OpenAPIDefinition(info = @Info(title = "API CRUD", description = "Operaciones CRUD para banner"))
public class BannerController {
    private final IBannerService bannerService;
    private final ModelMapper modelMapper;

    @Autowired
    public BannerController(IBannerService bannerService, ModelMapper modelMapper) {
        this.bannerService = bannerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/lista")
    @Operation(
            summary = "Obtener todas las imagenes de banner",
            description = "Este método devuelve una lista de todas los banner registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista  devuelta con éxito"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<List<BannerDto>> verBanner() {
        List<BannerDto> bannersDtos = bannerService.verBanner();
        return new ResponseEntity(bannersDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear banner",
            description = "Crea un banner con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "Banner se ha creado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos no son válidos")
    public ResponseEntity<BannerDto> agregarBanner(@RequestBody BannerDto bannerDto) {
        if (StringUtils.isBlank(bannerDto.getNombreUrl())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        bannerService.crearBanner(bannerDto);
        return new ResponseEntity(new Mensaje("Banner creado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    @Operation(
            summary = "Editar banner",
            description = "Edita bannera con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La banner se ha editado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos del banneer no son válidos")
    public ResponseEntity<BannerDto> editarBanner(@RequestBody BannerDto bannerDto) {
        bannerService.editarBanner(bannerDto);
        return new ResponseEntity(new Mensaje("Banner editado"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    @Operation(
            summary = "Buscar por su ID",
            description = "Devuelve la información específica basada en su ID")
    @ApiResponse(responseCode = "200", description = "Banner fue encontrado y es devuelta en el cuerpo de la respuesta", content = @Content(schema = @Schema(implementation = Banner.class)))
    @ApiResponse(responseCode = "400", description = "El ID es inválido o no existe con ese ID")
    public ResponseEntity<Banner> buscarBanner(@PathVariable Long id) {
        if (bannerService.buscarBanner(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(bannerService.buscarBanner(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar banner",
            description = "Elimina  según el ID especificado en la URL")
    public ResponseEntity<Void> borrarBanner(@PathVariable Long id) {
        bannerService.borrarBanner(id);
        return new ResponseEntity(new Mensaje("Banner borrado"), HttpStatus.OK);
    }
}
