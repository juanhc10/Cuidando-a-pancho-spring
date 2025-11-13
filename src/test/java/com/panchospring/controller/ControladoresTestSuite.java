package com.panchospring.controller;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Esta anotación indica que es una suite de tests
@Suite
@SelectClasses({
    CuidadorControllerTest.class,
    DuenioControllerTest.class,
    MascotaControllerTest.class,
    PremioControllerTest.class,
    UsuarioControllerTest.class,
    CuidadoControllerTest.class
})
public class ControladoresTestSuite {
    // No es necesario implementar métodos aquí.
}
