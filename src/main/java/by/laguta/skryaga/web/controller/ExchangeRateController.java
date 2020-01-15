package by.laguta.skryaga.web.controller;

import by.laguta.skryaga.entity.User;
import by.laguta.skryaga.model.ExchangeRate;
import by.laguta.skryaga.service.ExchangeRateService;
import by.laguta.skryaga.service.exception.ExchangeRateUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/exchange")
@Slf4j
public class ExchangeRateController {

    @Resource
    private ExchangeRateService exchangeRateService;

    @GetMapping("")
    public ResponseEntity<ExchangeRate> getLowestRate() {
        try {
            Optional<ExchangeRate> lowestRate = exchangeRateService.getLowestRate();
            return lowestRate.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ExchangeRateUpdateException e) {
            return ResponseEntity.notFound().build();
        }
    }

   /* @PostMapping("")
    @ResponseStatus(CREATED)
    public User createUser(@RequestBody User user) {
        return null;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
    }*/

}
