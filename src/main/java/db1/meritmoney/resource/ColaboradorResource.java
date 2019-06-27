package db1.meritmoney.resource;

import db1.meritmoney.domain.dto.ColaboradorDTO;
import db1.meritmoney.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorResource {

    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping
    public ColaboradorDTO post(@RequestBody ColaboradorDTO body) {
        return colaboradorService.save(body);
    }

//    @GetMapping
//    public ColaboradorDTO getByUsuario(@RequestParam("usuario") String usuario) {
//        return colaboradorService.getByUsuario(usuario);
//    }
//
    @GetMapping
    public List<ColaboradorDTO> getByNome(@RequestParam("nome") String nome) {
        return colaboradorService.getByNome(nome);
    }
//
//    @GetMapping
//    public List<ColaboradorDTO> getBySaldo(@RequestParam("saldo") Double saldo) {
//        return colaboradorService.getBySaldo(saldo);
//    }

    @PutMapping(value = "/{id}")
    public ColaboradorDTO put(@PathVariable("id") Long id, @RequestBody ColaboradorDTO body) {
        return colaboradorService.put(id, body);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        colaboradorService.delete(id);
    }

}
