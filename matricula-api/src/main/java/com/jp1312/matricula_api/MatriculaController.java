package com.jp1312.matricula_api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
