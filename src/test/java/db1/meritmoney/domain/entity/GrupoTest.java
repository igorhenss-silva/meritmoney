package db1.meritmoney.domain.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrupoTest {

    private static final String nomeValido = "Grupo de Teste";
    private static final String nomeInvalido = "AB";
    private static final LocalDate dataInicioValida = LocalDate.parse("2000-01-01");
    private static final LocalDate dataEncerramentoValida = LocalDate.parse("2019-07-01");
    private static final LocalDate dataInicioInvalida = LocalDate.parse("2019-07-05");
    private static final LocalDate dataEncerramentoInvalida = LocalDate.parse("2019-07-01");

    private Grupo grupo = new Grupo(nomeValido, dataInicioValida, dataEncerramentoValida);

    @Test
    public void deveRetornarRuntimeExceptionDeNomeMuitoCurto() {
        try {
            Grupo grupoNomeInvalido = new Grupo(nomeInvalido, dataInicioValida, dataEncerramentoValida);
        } catch (RuntimeException e) {
            assertEquals("O nome do grupo deve conter três ou mais caracteres.", e.getMessage());
        }
    }

    @Test
    public void deveRetornarRuntimeExceptionDataEncerramentoDeveSerMaiorQueDataInicio() {
        try {
            Grupo grupoDatasInvalidas = new Grupo(nomeValido, dataInicioInvalida, dataEncerramentoInvalida);
        } catch (RuntimeException e) {
            assertEquals("A data de encerramento deve ser maior que a data de início.", e.getMessage());
        }
    }

    @Test
    public void deveEncerrarGrupo() {
        grupo.encerrar();
        assertEquals(LocalDate.now(), grupo.getDataEncerramento());
    }

}
