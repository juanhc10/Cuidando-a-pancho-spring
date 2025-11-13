package com.panchospring.config;

import com.panchospring.model.entity.Cuidador;
import com.panchospring.model.entity.Duenio;
import com.panchospring.model.entity.Mascota;
import com.panchospring.model.entity.MascotaExotica;
import com.panchospring.repository.MascotaRepository;
import com.panchospring.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.panchospring.model.entity.Idioma.*;

@Configuration
@RequiredArgsConstructor
public class DataInitializationConfig {

    private final BCryptPasswordEncoder encoder;

    @Transactional
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, MascotaRepository mascotaRepository) {
        return args -> {
            Duenio juan = Duenio.builder().nombre("Juan").contrasenia(encoder.encode("123")).idioma(ESPANIOL).build();
            Cuidador maria = Cuidador.builder().nombre("Maria").contrasenia(encoder.encode("123")).idioma(INGLES).panchoPuntos(600).puedeCuidarExotica(true).build();
            Duenio carlos = Duenio.builder().nombre("Carlos").contrasenia(encoder.encode("password3")).idioma(EUSKERA).build();
            Cuidador laura = Cuidador.builder().nombre("Laura").contrasenia(encoder.encode("password4")).idioma(GALLEGO).panchoPuntos(600).build();
            Duenio ana = Duenio.builder().nombre("Ana").contrasenia(encoder.encode("password5")).idioma(ESPANIOL).build();
            usuarioRepository.saveAll(Arrays.asList(juan, maria, carlos, laura, ana));
            Mascota pedro = Mascota.builder().nombre("Pedro").localidad("Madrid").descripcion("Perro pequeño y juguetón").horaDisponible(LocalDateTime.now()).codRiac("1234").numPoliza(5678).duenio(juan).build();
            Mascota luna = Mascota.builder().nombre("Luna").localidad("Barcelona").descripcion("Gata tranquila y cariñosa").horaDisponible(LocalDateTime.now()).codRiac("2345").numPoliza(6789).duenio(carlos).build();
            MascotaExotica rocky = MascotaExotica.builder().nombre("Rocky").localidad("Valencia").descripcion("Perro guardián").horaDisponible(LocalDateTime.now()).codRiac("3456").numPoliza(7890).duenio(ana).certificadoLegal("es legal").certificadoSalud("y tiene salud").certificadoProcedencia("procede").build();
            Mascota lola = Mascota.builder().nombre("Lola").localidad("Sevilla").descripcion("Coneja blanca").horaDisponible(LocalDateTime.now()).codRiac("4567").numPoliza(8901).duenio(juan).build();
            Mascota milo = Mascota.builder().nombre("Milo").localidad("Bilbao").descripcion("Hámster dorado").horaDisponible(LocalDateTime.now()).codRiac("5678").numPoliza(9012).duenio(ana).build();
            mascotaRepository.saveAll(Arrays.asList(pedro, luna, rocky, lola, milo));
        };
    }
}
