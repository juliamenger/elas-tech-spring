package com.example.aulas.aula5.atividade.controller;

import com.example.aulas.aula4.controller.Mensagem;
import com.example.aulas.aula5.atividade.model.Pet;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    // GET /pets
    @GetMapping("/pets")
    public String pets() {
        return "Bem-vinda ao Pet Shop Elas Tech!";
    }

    //GET /pets/horario
    @GetMapping("/horario")
    public String horario() {
        return "Abrimos de segunda a sábado, das 8h às 18h.";
    }

    //GET /pets/destaque, que responde com o JSON: { "nome": "Thor", "especie": "Cachorro", "idade": 3 }
    @GetMapping("/destaque")
    public Pet pet () {
        return new Pet("Thor", "Cachorro", 3);
    }

    // GET /pets/adotar/{nome}, que usa o nome que vem no caminho:
    @GetMapping("/adotar/{nome}")
    public String adotar(@PathVariable String nome) {
        return "Parabéns! Você adotou " + nome + "!";
    }

    //pets/banho?pet=Mel → Mel está tomando banho!
    //pets/banho → Seu pet está tomando banho!
    @GetMapping("/banho")
    public String banho(@RequestParam(defaultValue = "Seu pet") String pet) {
        return pet + " está tomando banho!";
    }

    //pets/novo/{nome}, que devolve um JSON de um gato filhote com o nome que veio no caminho:
    @GetMapping("/novo/{nome}")
    public Pet novoPet (@PathVariable String nome) {
        return new Pet(nome, "Gato", 1);
    }

    //GET /pets/idade-humana, que recebe a idade do pet depois do ? e calcula a idade humana (multiplica por 7):
    @GetMapping("/idade-humana")
    public String idadeHumana(@RequestParam int anos) {
        return anos + " anos de pet equivalem a " + (anos * 7) + " anos humanos!";
    }
    //Depois teste /pets/idade-humana sem o ?anos=. O que acontece? Por quê?
    //Add missing query parameter. Porque não foi passado defaultValue

}
