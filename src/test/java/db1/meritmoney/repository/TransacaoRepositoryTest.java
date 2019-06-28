package db1.meritmoney.repository;

import db1.meritmoney.domain.entity.Colaborador;
import db1.meritmoney.domain.entity.Grupo;
import db1.meritmoney.domain.entity.Transacao;
import db1.meritmoney.enums.TipoTransacao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransacaoRepositoryTest {

    private final static LocalDate dataAtual = LocalDate.now();
    private final static LocalDate dataDiferenteDaAtual = LocalDate.parse("2000-01-01");

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    GrupoRepository grupoRepository;

    private Colaborador colaboradorGestor = new Colaborador("Colaborador Gestor", "colaborador.gestor", true);
    private Colaborador colaboradorComum = new Colaborador("Colaborador Comum", "colaborador.comum", false);
    private Grupo grupoOrigemComTransacoes = new Grupo("Grupo Origem Existente", LocalDate.parse("2000-01-01"), LocalDate.parse("3000-01-01"));
    private Grupo grupoOrigemSemTransacoes = new Grupo("Grupo Origem Inexistente", LocalDate.parse("2500-01-01"), LocalDate.parse("3000-01-01"));
    private Transacao transacaoEntrada1 = new Transacao(colaboradorGestor, 5.5, TipoTransacao.E, colaboradorComum, grupoOrigemComTransacoes);
    private Transacao transacaoEntrada2 = new Transacao(colaboradorGestor, 10.0, TipoTransacao.E, colaboradorComum, grupoOrigemComTransacoes);
    private Transacao transacaoEntrada3 = new Transacao(colaboradorGestor, 4.5, TipoTransacao.E, colaboradorComum, grupoOrigemComTransacoes);

    @Before
    public void before() {
        colaboradorRepository.save(colaboradorGestor);
        colaboradorRepository.save(colaboradorComum);
        grupoRepository.save(grupoOrigemComTransacoes);
        grupoRepository.save(grupoOrigemSemTransacoes);
        transacaoRepository.save(transacaoEntrada1);
        transacaoRepository.save(transacaoEntrada2);
        transacaoRepository.save(transacaoEntrada3);
    }

    @Test
    public void deveRetornarUmaListaDeTransacoesDeTipoEntrada() {
        List<Transacao> transacoes = transacaoRepository.findByTipoTransacao(TipoTransacao.E);
        assertTrue(transacoes.stream()
                .allMatch(transacao -> transacao.getTipoTransacao()
                        .equals(TipoTransacao.E)));
        assertEquals(3, transacoes.size());
    }

    @Test
    public void deveRetornarUmaListaDeTransacoesVaziaPoisNaoExistemTransacoesDoTipoSaida() {
        List<Transacao> transacoes = transacaoRepository.findByTipoTransacao(TipoTransacao.S);
        assertTrue(transacoes.isEmpty());
    }

    @Test
    public void deveRetornarUmaListaDeTransacoesRealizadasNaDataAtual() {
        List<Transacao> transacoes = transacaoRepository.findByDataTransacao(dataAtual);
        assertTrue(transacoes.stream()
                .allMatch(transacao -> transacao.getDataTransacao()
                        .equals(dataAtual)));
        assertEquals(3, transacoes.size());
    }

    @Test
    public void deveRetornarUmaListaDeTransacoesVaziaPoisNaoExistemTransacoesRealizadasEmDatasDiferentesDaAtual() {
        List<Transacao> transacoes = transacaoRepository.findByDataTransacao(dataDiferenteDaAtual);
        assertTrue(transacoes.isEmpty());
    }

    @Test
    public void deveRetornarUmaListaDeTransacoesQueOcorreramNoGrupoOrigemComTransacoes() {
        List<Transacao> transacoes = transacaoRepository.findByGrupoOrigem(grupoOrigemComTransacoes);
        assertTrue(transacoes.stream()
                .allMatch(transacao -> transacao.getGrupoOrigem().getNome()
                        .equals(grupoOrigemComTransacoes.getNome())));
        assertEquals(3, transacoes.size());
    }

    @Test
    public void deveRetornarUmaListaDeTransacoesVaziaPoisNenhumaTransacaoOcorreuNoGrupoOrigemSemTransacoes() {
        List<Transacao> transacoes = transacaoRepository.findByGrupoOrigem(grupoOrigemSemTransacoes);
        assertTrue(transacoes.isEmpty());
    }

    @After
    public void after() {
        transacaoRepository.deleteAll();
        colaboradorRepository.deleteAll();
        grupoRepository.deleteAll();
    }

}
