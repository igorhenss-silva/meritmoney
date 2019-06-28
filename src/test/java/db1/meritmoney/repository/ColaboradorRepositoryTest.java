package db1.meritmoney.repository;

import db1.meritmoney.domain.entity.Colaborador;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColaboradorRepositoryTest {

    private final static String usuarioExistente = "igor.silva";
    private final static String usuarioInexistente = "user.notfound";

    @Autowired
    ColaboradorRepository colaboradorRepository;

    private Colaborador colaborador = new Colaborador("Igor Silva", usuarioExistente, false);

    @Before
    public void before() {
        colaboradorRepository.save(colaborador);
    }

    @Test
    public void deveRetornarOColaboradorCorrespondenteAoUsuarioPassado() {
        Colaborador colaboradorExistente = colaboradorRepository.findByUsuario(usuarioExistente);
        assertNotNull(colaboradorExistente);
        assertEquals(usuarioExistente, colaboradorExistente.getUsuario());
    }

    @Test
    public void deveRetornarUmColaboradorVazioPoisOUsuarioPassadoNaoExiste() {
        Colaborador colaboradorNaoExistente = colaboradorRepository.findByUsuario(usuarioInexistente);
        assertNull(colaboradorNaoExistente);
    }

    @After
    public void after() {
        colaboradorRepository.deleteAll();
    }

}
