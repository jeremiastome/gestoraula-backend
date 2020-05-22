package com.tip.alumnos;

import com.tip.alumnos.controller.AlumnoController;
import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.repository.IAlumnoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AlumnoController.class)
@ContextConfiguration(initializers = {MyApplicationContextInitializer.class})
class AlumnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public IAlumnoRepository alumnoRepository;

    @Test
    public void retrieveDetailsForCourse() throws Exception {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Ricardo", "Fort"));

        Mockito.when(alumnoRepository.findAll()).thenReturn(alumnos);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/alumnos/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

    }
}
