package db1.meritmoney.service;

import db1.meritmoney.domain.dto.ColaboradorDTO;
import db1.meritmoney.domain.entity.Colaborador;
import db1.meritmoney.repository.ColaboradorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColaboradorServiceTest {

    private static final String usuarioExistente = "usuario.existente";
    private static final String usuarioInexistente = "usuario.inexistente";
    private static final String nomePassado = "Nome";
    private static final Double saldoPassado = 10.5;
    private static final Double novoSaldo = 1.5;

    @Autowired
    ColaboradorService colaboradorService;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    private ColaboradorDTO colaboradorParaSalvar = new ColaboradorDTO(1L, "Nome Um", usuarioExistente, 5.0, false);
    private ColaboradorDTO colaboradorComUsuarioRepetido = new ColaboradorDTO(2L, "Nome Dois", usuarioExistente, 15.0, true);
    private ColaboradorDTO colaborador2 = new ColaboradorDTO(3L, "Nome Três", "usuario.tres", 55.0, false);
    private ColaboradorDTO colaborador3 = new ColaboradorDTO(4L, "Nome Quatro", "usuario.quatro", 125.0, false);
    private ColaboradorDTO colaboradorSalvo;

    @Before
    public void before() {
        colaboradorSalvo = colaboradorService.save(colaboradorParaSalvar);
        colaboradorService.save(colaborador2);
    }

    // CREATE

    @Test
    public void deveSalvarUmColaborador() {
        colaboradorService.save(colaborador3);
        assertEquals(3, colaboradorRepository.findAll().size());
    }

    @Test
    public void deveRetornarRuntimeExceptionUsuarioDoColaboradorJaEstaEmUso() {
        try {
            colaboradorService.save(colaboradorComUsuarioRepetido);
        } catch (RuntimeException e) {
            assertEquals("O usuário do colaborador já está em uso!", e.getMessage());
        }
    }

    // READ

    @Test
    public void deveRetornarColaboradorRelacionadoAoUsuarioPassado() {
        colaboradorSalvo = colaboradorService.getByUsuario(usuarioExistente);
        assertEquals(colaboradorParaSalvar.getUsuario(), colaboradorSalvo.getUsuario());
        assertEquals(colaboradorParaSalvar.getNome(), colaboradorSalvo.getNome());
    }

    @Test
    public void deveRetornarNullPointerExceptionVazio() {
        try {
            colaboradorService.getByUsuario(usuarioInexistente);
        } catch (NullPointerException e) { }
    }

    @Test
    public void deveRetornarListaDeColaboradoresComStringPassada() {
        List<ColaboradorDTO> colaboradores = colaboradorService.getByNome(nomePassado);
        assertTrue(colaboradores.stream()
                .allMatch(colaborador -> colaborador.getNome()
                        .contains(nomePassado)));
        assertEquals(2, colaboradores.size());
    }

    @Test
    public void deveRetornarListaVaziaGetByNome() {
        List<ColaboradorDTO> colaboradores = colaboradorService.getByNome("Nome Inexistente");
        assertTrue(colaboradores.isEmpty());
    }

    @Test
    public void deveRetornarListaGetBySaldo() {
        List<ColaboradorDTO> colaboradores = colaboradorService.getBySaldo(saldoPassado);
        assertTrue(colaboradores.stream()
                .allMatch(colaborador -> colaborador.getSaldo() > saldoPassado));
        assertEquals(1, colaboradores.size());
    }

    @Test
    public void deveRetornarListaVaziaGetBySaldo() {
        List<ColaboradorDTO> colaboradores = colaboradorService.getBySaldo(saldoPassado + 1000);
        assertTrue(colaboradores.isEmpty());
    }

    // UPDATE

//    @Test
//    public void deveModificarColaborador() {
//        colaboradorParaSalvar.setSaldo(novoSaldo);
//        colaboradorService.put(colaboradorParaSalvar.getId(), colaboradorParaSalvar);
//        colaboradorSalvo = colaboradorService.getByUsuario(colaboradorParaSalvar.getUsuario());
//        assertEquals(novoSaldo, colaboradorSalvo.getSaldo());
//    }
//
//    @Test
//    public void deveFalharAoModificarColaborador() {
//        try {
//            colaboradorParaSalvar.setSaldo(novoSaldo + 100);
//            colaboradorService.put(50L, colaboradorParaSalvar);
//        } catch (JpaObjectRetrievalFailureException e) { }
//    }

    // DELETE

    @Test
    public void deletarColaboradorSalvo() {
        Colaborador colaboradorParaDeletar = colaboradorRepository.findByUsuario(colaboradorParaSalvar.getUsuario());
        colaboradorService.delete(colaboradorParaDeletar.getId());
        assertEquals(1, colaboradorRepository.findAll().size());
    }

    @Test
    public void deletarColaboradorInexistente() {
        try {
            colaboradorService.delete(50L);
        } catch (JpaObjectRetrievalFailureException e) { }
    }

    @After
    public void after() {
        colaboradorRepository.deleteAll();
    }

    private ColaboradorDTO colaboradorToDTO(Colaborador colaborador) {
        return new ColaboradorDTO(colaborador.getId(), colaborador.getNome(), colaborador.getUsuario(), colaborador.getSaldo(), colaborador.getGestor());
    }

}
