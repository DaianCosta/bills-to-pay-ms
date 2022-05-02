package br.com.dcsolution.billstopay.modules.group.rest;

import br.com.dcsolution.billstopay.modules.group.dto.GroupDto;
import br.com.dcsolution.billstopay.modules.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(final GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<Page<GroupDto>> findAll(@RequestParam final Integer page,
                                                  @RequestParam final Integer size,
                                                  @RequestParam final String searchTerm) {
        return new ResponseEntity<>(groupService.findAll(page, size, searchTerm),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> findById(@PathVariable final Integer id) {
        return new ResponseEntity<>(groupService.findById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody @Valid GroupDto groupDto) {
        groupService.create(groupDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> put(@RequestBody @Valid GroupDto groupDto) {
        groupService.update(groupDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        groupService.delete(id);
        return ResponseEntity.ok().build();
    }
}
