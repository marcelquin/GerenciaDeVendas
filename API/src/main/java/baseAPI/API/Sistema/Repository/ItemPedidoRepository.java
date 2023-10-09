package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.LongStream;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    Collection<Object> findAllById(Long[] idItem);

    boolean existsById(LongStream idItem);

}
