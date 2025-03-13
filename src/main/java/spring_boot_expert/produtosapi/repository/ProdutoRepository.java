package spring_boot_expert.produtosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_expert.produtosapi.model.Produto;

import java.util.List;

/* classe scaneada pelo springboot primeiro repositorio entidade e a segunda a classe que representa o id**/
public interface ProdutoRepository extends JpaRepository<Produto, String> {

    List<Produto> findByNome(String nome);
}
