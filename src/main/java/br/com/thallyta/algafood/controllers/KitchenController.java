package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.openapi.KitchenControllerOpenApi;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.assembler.request.KitchenRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.KitchenResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.KitchenRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.KitchenResponseDTO;
import br.com.thallyta.algafood.repositories.KitchenRepository;
import br.com.thallyta.algafood.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController implements KitchenControllerOpenApi {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenRequestDTODisassembler kitchenDisassembler;

    @Autowired
    private KitchenResponseDTOAssembler kitchenAssembler;

    @GetMapping
    public Page<KitchenResponseDTO> getAll(Pageable pageable){
        Page<Kitchen> kitchens = kitchenService.getAll(pageable);
        List<KitchenResponseDTO> kitchenResponseDTOList = kitchenAssembler.toCollectionModel(kitchens.getContent());
        return new PageImpl<>(kitchenResponseDTOList, pageable, kitchens.getTotalElements());
    }

    @GetMapping("/{id}")
    public KitchenResponseDTO getById(@PathVariable Long id){
        Kitchen kitchen = kitchenService.findOrFail(id);
        return kitchenAssembler.toKitchenResponse(kitchen);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public KitchenResponseDTO create(@RequestBody @Valid KitchenRequestDTO kitchenRequestDTO) {
        Kitchen kitchen = kitchenDisassembler.toDomainObject(kitchenRequestDTO);
        return kitchenAssembler.toKitchenResponse(kitchenService.save(kitchen));
    }

    @PutMapping("/{id}")
    public KitchenResponseDTO update(@PathVariable Long id, @RequestBody @Valid KitchenRequestDTO kitchenRequestDTO) {
        Kitchen kitchenFound = kitchenService.findOrFail(id);
        kitchenDisassembler.copyToDomainObject(kitchenRequestDTO, kitchenFound);
        return kitchenAssembler.toKitchenResponse(kitchenService.save(kitchenFound));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
            kitchenService.delete(id);
    }

}
