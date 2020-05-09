package com.tip.alumnos;

import com.tip.alumnos.model.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CursoControllerTest extends AlumnosApplicationTests {

    @Test
    public void crearUnCurso() throws Exception {
        String uri = "/cursos";
        Curso curso1 = new Curso("1° A");

        String inputJson = super.mapToJson(curso1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }
}
