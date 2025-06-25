package com.jp1312.matricula_api;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public List<MatriculaRequest> listarMatriculas() {
        List<MatriculaRequest> lista = new ArrayList<>();

        MatriculaRequest m1 = new MatriculaRequest();
        m1.setNome("João Pedro");
        m1.setEmail("joao@example.com");
        m1.setCurso("Engenharia da Computação");

        MatriculaRequest m2 = new MatriculaRequest();
        m2.setNome("Maria Silva");
        m2.setEmail("maria@example.com");
        m2.setCurso("Ciência da Computação");

        lista.add(m1);
        lista.add(m2);

        return lista;
    }

}

