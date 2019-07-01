//package db1.meritmoney.service;
//
//import db1.meritmoney.domain.dto.ColaboradorDTO;
//import db1.meritmoney.domain.dto.GrupoDTO;
//import db1.meritmoney.domain.dto.TransacaoDTO;
//import db1.meritmoney.domain.entity.Colaborador;
//import db1.meritmoney.domain.entity.Grupo;
//import db1.meritmoney.enums.TipoTransacao;
//import db1.meritmoney.repository.ColaboradorRepository;
//import db1.meritmoney.repository.GrupoRepository;
//import db1.meritmoney.repository.TransacaoRepository;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TransacaoServiceTest {
//
//    @Autowired
//    TransacaoService transacaoService;
//
//    @Autowired
//    TransacaoRepository transacaoRepository;
//
//    @Autowired
//    ColaboradorService colaboradorService;
//
//    @Autowired
//    ColaboradorRepository colaboradorRepository;
//
//    @Autowired
//    GrupoService grupoService;
//
//    @Autowired
//    GrupoRepository grupoRepository;
//
////    55 22 99790 9669
//
//    private ColaboradorDTO colaboradorGestor = new ColaboradorDTO(1L, "Colaborador Gestor", "colaborador.gestor", 2.5, true);
//    private ColaboradorDTO colaboradorComum = new ColaboradorDTO(2L, "Colaborador Comum", "colaborador.comum", 5.0,false);
//    private GrupoDTO grupoOrigem = new GrupoDTO(1L, "Grupo Origem", LocalDate.parse("2000-01-01"), LocalDate.parse("3000-01-01"));
//
//    @Before
//    public void before() {
//        colaboradorService.save(colaboradorGestor);
//        colaboradorService.save(colaboradorComum);
//        grupoService.save(grupoOrigem);
//    }
//
//    // CREATE
//
//    @Test
//    public void deveRetornarTransacao() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        assertEquals(2, colaboradorRepository.findAll().size());
//        assertEquals(1, grupoRepository.findAll().size());
////        assertEquals(1, transacaoRepository.findAll().size());
//    }
//
//    // READ
//
//    @Test
//    public void deveRetornarListaDeTransacoesRealizadasPorOuParaOColaboradorPassado() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByColaborador(colaboradorComum.getId());
//        assertEquals(1, transacoes.size());
//    }
//
//    @Test
//    public void deveRetornarListaVaziaPoisNaoHaColaboradorPassado() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByColaborador(9999999999L);
//        assertTrue(transacoes.isEmpty());
//    }
//
//    @Test
//    public void deveRetornarListaDeTransacoesComUmaQuantiaTransferidaEntreAsQuantiasPassadas() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByQuantiaTransferida(5.5, 5.5125);
//        assertEquals(1, transacoes.size());
//    }
//
//    @Test
//    public void deveRetornarListaVaziaPoisNaoHaRegistroComUmaQuantiaTransferidaEntreAsQuantiasPassadas() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByQuantiaTransferida(0.0, 2.5);
//        assertTrue(transacoes.isEmpty());
//    }
//
//    @Test
//    public void deveRetornarListaDeTransacoesDoTipoTransacaoPassado() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByTipoTransacao(TipoTransacao.E);
//        assertEquals(1, transacoes.size());
//    }
//
//    @Test
//    public void deveRetornarListaVaziaPoisNaoHaTransacoesDoTipoPassado() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByTipoTransacao(TipoTransacao.S);
//        assertTrue(transacoes.isEmpty());
//    }
//
//    @Test
//    public void deveRetornarListaDeTransacoesDaDataTransacaoPassada() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByDataTransacao(LocalDate.now());
//        assertEquals(1, transacoes.size());
//    }
//
//    @Test
//    public void deveRetornarListaDeTransacoesRealizadasNoGrupoOrigemPassado() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByGrupoOrigem(grupoOrigem.getId());
//        assertEquals(1, transacoes.size());
//    }
//
//    @Test
//    public void deveRetornarListaVaziaPoisNaoHaTransacoesRealizadasNoGrupoOrigem() {
//        TransacaoDTO transacao = new TransacaoDTO(1L, colaboradorGestor.getId(), 5.5, TipoTransacao.E, colaboradorComum.getId(), LocalDate.now(), grupoOrigem.getId());
//        transacaoService.save(transacao);
//        List<TransacaoDTO> transacoes = transacaoService.getByGrupoOrigem(9999999999L);
//        assertTrue(transacoes.isEmpty());
//    }
//
//    @After
//    public void after() {
//        transacaoRepository.deleteAll();
//        colaboradorRepository.deleteAll();
//        grupoRepository.deleteAll();
//    }
//
//}