package db1.meritmoney.resource;

import db1.meritmoney.domain.dto.GrupoDTO;
import db1.meritmoney.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/grupo")
public class GrupoResource {

    @Autowired
    private GrupoService grupoService;

    @PostMapping
    public GrupoDTO post(@RequestBody GrupoDTO body) {
        return grupoService.save(body);
    }

//    @GetMapping
//    public List<GrupoDTO> getByNome(@RequestParam("nome") String nome) {
//        return grupoService.getByNome(nome);
//    }

    @GetMapping
    public List<GrupoDTO> getByIntervaloDeTempo(@RequestParam("data_inicio") LocalDate dataInicio, @RequestParam("data_encerramento") LocalDate dataEncerramento) {
        return grupoService.getByData(dataInicio, dataEncerramento);
    }

    @PutMapping(value = "/{id}")
    public GrupoDTO put(@PathVariable("id") Long id, @RequestBody GrupoDTO body) {
        return grupoService.put(id, body);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        grupoService.delete(id);
    }

}
