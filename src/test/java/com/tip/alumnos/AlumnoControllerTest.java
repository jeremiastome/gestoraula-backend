package com.tip.alumnos;

import com.tip.alumnos.model.Alumno;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

public class AlumnoControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAlumnos() throws Exception {
        String uri = "/alumnos";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Alumno[] alumnos = super.mapFromJson(content, Alumno[].class);
        assertTrue(alumnos.length > 0);
    }

    @Test
    public void createAlumno() throws Exception {
        String uri = "/alumnos";
        Alumno alumno = new Alumno();
        alumno.setNombre("Esteban");
        alumno.setApellido("Matas");
        alumno.setDni(36630465);
        alumno.setEmailContacto("esteban@gmail.comm");

        String inputJson = super.mapToJson(alumno);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @Test
    public void createInvalidAlumno() throws Exception {
        String uri = "/alumnos";
        Alumno alumno = new Alumno();
        alumno.setNombre("Esteban");
        alumno.setApellido("Matas");
        alumno.setDni(36630464);
        alumno.setEmailContacto("esteban@gmail.comm");

        Alumno alumno2 = new Alumno();
        alumno.setNombre("Esteban");
        alumno.setApellido("Matas");
        alumno.setDni(36630464);
        alumno.setEmailContacto("esteban@gmail.comm");

        String inputJson = super.mapToJson(alumno);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        String inputJson2 = super.mapToJson(alumno2);
        MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status2 = mvcResult2.getResponse().getStatus();
        assertEquals(409, status2);
    }

    @Test
    public void getAlumnosByEmail() throws Exception {
        String uri = "/alumnosRegistrados/jeremias.tome1@gmail.com";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Alumno[] alumnos = super.mapFromJson(content, Alumno[].class);
        assertEquals(1, alumnos.length);
        assertEquals("Ricardo", alumnos[0].getNombre());
    }


}
