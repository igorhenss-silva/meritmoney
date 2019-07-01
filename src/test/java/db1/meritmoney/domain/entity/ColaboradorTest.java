package db1.meritmoney.domain.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColaboradorTest {

    private static final String nomeValido = "Colaborador de Teste";
    private static final String nomeInvalido = "A";
    private static final String usuarioValido = "colaborador.teste";
    private static final String usuarioInvalido = "ac";

    private Colaborador colaborador = new Colaborador(nomeValido, usuarioValido, false);

    @Test
    public void deveRetornarRuntimeExceptionDeNomeInvalido() {
        try {
            new Colaborador(nomeInvalido, usuarioValido, true);
        } catch (RuntimeException e) {
            assertEquals("O nome do colaborador deve conter dois ou mais caracteres.", e.getMessage());
        }
    }

    @Test
    public void deveRetornarRuntimeExceptionDeUsuarioInvalido() {
        try {
            new Colaborador(nomeValido, usuarioInvalido, false);
        } catch (RuntimeException e) {
            assertEquals("Usuário inválido. Todo usuário precisa ter um ponto [.] entre o nome e o sobrenome.", e.getMessage());
        }
    }

    @Test
    public void deveTransferir() {
        try {
            colaborador.transferir(50.0);
        } catch (RuntimeException e) {
            assertEquals("Saldo insuficiente.", e.getMessage());
        }
    }

}
