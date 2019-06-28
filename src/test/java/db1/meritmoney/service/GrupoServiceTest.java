package db1.meritmoney.service;

import db1.meritmoney.domain.entity.Grupo;
import db1.meritmoney.repository.GrupoRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrupoServiceTest {

    @Autowired
    GrupoService grupoService;

    @Autowired
    GrupoRepository grupoRepository;

    Grupo grupo = new Grupo("Grupo Existente", LocalDate.parse("2000-01-01"), LocalDate.parse("3000-01-01"));


}
