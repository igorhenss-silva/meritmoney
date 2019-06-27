package db1.meritmoney.resource;

import db1.meritmoney.domain.dto.ColaboradoresGruposDTO;
import db1.meritmoney.service.ColaboradoresGruposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "colaboradores_grupos")
public class ColaboradoresGruposResource {

    @Autowired
    ColaboradoresGruposService colaboradoresGruposService;

    @PostMapping
    public ColaboradoresGruposDTO post(@RequestBody ColaboradoresGruposDTO body) {
        return colaboradoresGruposService.save(body);
    }

}
