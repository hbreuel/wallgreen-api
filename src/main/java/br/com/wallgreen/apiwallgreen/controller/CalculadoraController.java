package br.com.wallgreen.apiwallgreen.controller;

import br.com.wallgreen.apiwallgreen.models.CalculadoraWallGreen;
import br.com.wallgreen.apiwallgreen.models.RequestDadosDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wallgreen-api")
public class CalculadoraController {

    @PostMapping
    public ResponseEntity<CalculadoraWallGreen> calculaWallGreen(@RequestBody RequestDadosDTO dadosDTO) {
        try {
            var calculadora = new CalculadoraWallGreen(dadosDTO);
            calculadora.calculaDimensoes();
            calculadora.calculaQuantidades();
            return ResponseEntity.ok(calculadora);
        } catch (Exception e) {
            // Se ocorrer algum erro, você pode retornar uma resposta de erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
