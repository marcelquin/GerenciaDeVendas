package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.ItemPedido;
import baseAPI.API.Sistema.Model.Produto;
import jakarta.persistence.metamodel.IdentifiableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsById(Long[] idProduto);


}
