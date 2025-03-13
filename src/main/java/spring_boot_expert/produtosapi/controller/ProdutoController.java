package spring_boot_expert.produtosapi.controller;

import org.springframework.web.bind.annotation.*;
import spring_boot_expert.produtosapi.model.Produto;
import spring_boot_expert.produtosapi.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/*@restController em spring diz que vai receber requisicao Rest em um dos metodos que estao mapeados* mapping URL base do controle**/

@RestController
@RequestMapping("produtos")
public class ProdutoController {


    /*definir propriedade do produtoRepository que scaneado pelo spring que vai prover implementacao de produtoRepository*/
    private ProdutoRepository produtoRepository;

    /*injetar com construtor nao precisa instanciar para salvar o produto*/
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /*criar recurso enviar em ou receber server Rest, mapear para post retornado produto para quem chamou **/
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Produto recebido: " + produto);

        var id = UUID.randomUUID().toString();
        produto.setId(id);

        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id) {
//        Optional<Produto> produto = produtoRepository.findById(id);
//        return produto.isPresent() ? produto.get() : null;
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String id){
        produtoRepository.deleteById(id);
    }
//atualizar
    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id, @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
    }
//busca criada no repository findByNome
    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome){
         return produtoRepository.findByNome(nome);
    }


}
    /* metodo void sem retorno, executa acao mas nao retorna nada para quem chamou
    public void salvar(@RequestBody Produto produto){
        System.out.println("Produto recebido: " + produto);
    }**/

