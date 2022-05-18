package br.com.dcsolution.billstopay.modules.tag.rest;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.tag.dto.TagDto;
import br.com.dcsolution.billstopay.modules.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService groupService;

    @Autowired
    public TagController(final TagService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<PaginationDto<TagDto>> findAll(@RequestParam final Integer page,
                                                         @RequestParam final Integer size,
                                                         @RequestParam final String searchTerm) {
        return new ResponseEntity<>(groupService.findAll(page, size, searchTerm),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findById(@PathVariable final Integer id) {
        return new ResponseEntity<>(groupService.findById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody @Valid final TagDto groupDto) {
        groupService.create(groupDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> put(@RequestBody @Valid final TagDto groupDto) {
        groupService.update(groupDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        groupService.delete(id);
        return ResponseEntity.ok().build();
    }
}
