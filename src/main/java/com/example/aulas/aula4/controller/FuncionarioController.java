package com.example.aulas.aula4.controller;
import com.example.aulas.aula4.Funcionario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aula4/funcionarios")
public class FuncionarioController {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    // GET /aula4/funcionarios
    @GetMapping
    public List<Funcionario> listar() {
        return funcionarios;
    }

    // GET /aula4/funcionarios/1
    @GetMapping("/{id}")
    public Funcionario buscar(@PathVariable int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Funcionário não encontrado");
    }

    // POST /aula4/funcionarios
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario cadastrar(@RequestBody Funcionario funcionario) {
        funcionario.setId(proximoId);
        proximoId++;
        funcionarios.add(funcionario);
        return funcionario;
    }

    // PUT /aula4/funcionarios/1
    @PutMapping("/{id}")
    public Funcionario atualizar(@PathVariable int id, @RequestBody Funcionario dados) {
        Funcionario existente = buscar(id); // reaproveita!
        existente.setNome(dados.getNome());
        existente.setCargo(dados.getCargo());
        existente.setSalario(dados.getSalario());
        return existente;
    }

    // DELETE /aula4/funcionarios/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable int id) {
        Funcionario existente = buscar(id);
        funcionarios.remove(existente);
    }

}
