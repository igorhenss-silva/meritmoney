package db1.meritmoney.resource;

import db1.meritmoney.domain.dto.GrupoDTO;
import db1.meritmoney.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping
    public List<GrupoDTO> getByNome(@RequestParam("nome") String nome) {
        return grupoService.getByNome(nome);
    }

//    @GetMapping
//    public List<GrupoDTO> getByDataInicio(@RequestParam("data_inicio") LocalDateTime data) {
//        return grupoService.getByData(data);
//    }

//    @GetMapping
//    public List<GrupoDTO> getByDataFinalizacao(@RequestParam("data_finalizacao") LocalDateTime data) {
//        return grupoService.getByData(data);
//    }

    @PutMapping(value = "/{id}")
    public GrupoDTO put(@PathVariable("id") Long id, @RequestBody GrupoDTO body) {
        return grupoService.put(id, body);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        grupoService.delete(id);
    }

}
