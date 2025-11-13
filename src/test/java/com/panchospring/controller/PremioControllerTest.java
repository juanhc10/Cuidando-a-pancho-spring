package com.panchospring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panchospring.model.dto.premio.ProductoDto;
import com.panchospring.model.dto.premio.PromocionDto;
import com.panchospring.model.dto.premio.PremioDto;
import com.panchospring.service.PremioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PremioController.class)
class PremioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PremioService premioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe devolver la lista de premios")
    void getPremios() throws Exception {
        PremioDto premioDto = new PremioDto(1, "producto", 100);
        Mockito.when(premioService.getPremios())
                .thenReturn(List.of(premioDto));

        mockMvc.perform(get("/api/v1/premios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("Debe crear un producto")
    void crearProducto() throws Exception {
        ProductoDto productoDto = new ProductoDto("Juguete", "Juguete para perro");
        Mockito.when(premioService.crearProducto(any(ProductoDto.class)))
                .thenReturn(productoDto);

        mockMvc.perform(post("/api/v1/premios/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juguete"));
    }

    @Test
    @DisplayName("Debe crear una promoción")
    void crearPromocion() throws Exception {
        PromocionDto promocionDto = new PromocionDto();
        Mockito.when(premioService.crearPromocion(any(PromocionDto.class)))
                .thenReturn(promocionDto);

        mockMvc.perform(post("/api/v1/premios/promocion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(promocionDto)))
                .andExpect(status().isCreated());
    }
}
