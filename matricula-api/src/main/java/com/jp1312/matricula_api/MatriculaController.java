package com.jp1312.matricula_api;
import com.jp1312.matricula_api.MatriculaRequest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @PostMapping
    public String cadastrarMatricula(@RequestBody MatriculaRequest request) {
        System.out.println("📥 Nome: " + request.getNome());
        System.out.println("📧 Email: " + request.getEmail());
        System.out.println("📚 Curso: " + request.getCurso());

        return "✅ Matrícula recebida com sucesso!";
    }
}
