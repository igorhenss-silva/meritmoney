package db1.meritmoney.service;

import db1.meritmoney.domain.entity.Transacao;
import db1.meritmoney.repository.TransacaoRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransacaoServiceTest {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    TransacaoRepository transacaoRepository;

//    Transacao transacao = new Transacao();

}
