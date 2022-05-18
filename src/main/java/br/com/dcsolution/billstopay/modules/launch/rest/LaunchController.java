package br.com.dcsolution.billstopay.modules.launch.rest;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDetailDto;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import br.com.dcsolution.billstopay.modules.launch.service.LaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/launch")
public class LaunchController {

    private final LaunchService launchService;

    @Autowired
    public LaunchController(final LaunchService launchService) {
        this.launchService = launchService;
    }

    @GetMapping
    public ResponseEntity<PaginationDto<LaunchDetailDto>> findAll(@RequestParam final Integer page,
                                                                  @RequestParam final Integer size,
                                                                  @RequestParam final String searchTerm) {
        return new ResponseEntity<>(launchService.findAll(page, size, searchTerm),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaunchDetailDto> findById(@PathVariable final Integer id) {
        return new ResponseEntity<>(launchService.findById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody @Valid final LaunchDto launchDto) {
        launchService.create(launchDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> put(@RequestBody @Valid final LaunchDto launchDto) {
        launchService.update(launchDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        launchService.delete(id);
        return ResponseEntity.ok().build();
    }
}
