package db1.meritmoney.resource;

import db1.meritmoney.domain.dto.TransacaoDTO;
import db1.meritmoney.enums.TipoTransacao;
import db1.meritmoney.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoResource {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public TransacaoDTO post(@RequestBody TransacaoDTO body) {
        return transacaoService.save(body);
    }

//    @GetMapping
//    public List<TransacaoDTO> getByColaborador(@RequestParam("id") Long id) {
//        return transacaoService.getByColaborador(id);
//    }
//
//    @GetMapping
//    public List<TransacaoDTO> getByQuantiaTransferida(@RequestParam("min") Double min, @RequestParam("max") Double max) {
//        return transacaoService.getByQuantiaTransferida(min, max);
//    }
//
    @GetMapping
    public List<TransacaoDTO> getByTipoTransacao(@RequestParam("tipo_transacao") TipoTransacao tipoTransacao) {
        return transacaoService.getByTipoTransacao(tipoTransacao);
    }
//
//    @GetMapping
//    public List<TransacaoDTO> getByDataTransacao(@RequestParam("data_transacao") LocalDate dataTransacao) {
//        return transacaoService.getByDataTransacao(dataTransacao);
//    }
//
//    @GetMapping
//    public List<TransacaoDTO> getByGrupoOrigem(@RequestParam("grupo_origem") Long grupoOrigem) {
//        return transacaoService.getByGrupoOrigem(grupoOrigem);
//    }

    @PutMapping(value = "/{id}")
    public TransacaoDTO put(@PathVariable("id") Long id, @RequestBody TransacaoDTO body) {
        return transacaoService.put(id, body);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        transacaoService.delete(id);
    }

}
