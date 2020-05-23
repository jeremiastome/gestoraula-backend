package com.tip.alumnos;

import static org.assertj.core.api.Assertions.*;

import com.tip.alumnos.model.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class DateUtilsTest {

    @Test
    public void getClassDays() {
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        Integer diasDeClase = DateUtils.getClassDays(fechaInicio, fechaFin);

        assertThat(diasDeClase).isEqualTo(0);
    }

}
