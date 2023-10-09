package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.CarrinhoDTO;
import baseAPI.API.Sistema.Model.Carrinho;
import baseAPI.API.Sistema.Model.ItemPedido;
import baseAPI.API.Sistema.Repository.CarrinhoRepository;
import baseAPI.API.Sistema.Repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository repository;
    @Autowired
    private ItemPedidoRepository IRepository;



    public Carrinho listar()
    {
        try{
            repository.findAll();
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao listar");
        }
        return null;
    }

    public Carrinho buscaPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                repository.findById(id);
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao buscar");
        }
        return null;
    }

    public CarrinhoDTO AdicionarItem(List<Long> idItem)
    {
        try{
            Carrinho carrinho = new Carrinho();;
            List<ItemPedido> item = new ArrayList<>();
            for(Long i = 0L ; i < idItem.size(); i++)
            {
                Long id = idItem.stream().toList().get(Math.toIntExact(i)).longValue();
                ItemPedido itemPedido = IRepository.findById(id).get();
                item.add(itemPedido);
                System.out.println(itemPedido);
            }
            carrinho.setItemPedidos(item);
            carrinho.setValorTotal(carrinho.calValorTotal());
            repository.save(carrinho);
        }catch (Exception e){
            e.getMessage();
            e.getStackTrace();
            System.out.println("ops algo deu errado");
        }
        return null;
    }

    public CarrinhoDTO editar(Long id,List<Long> idItem)
    {
        try{
            if(repository.existsById(id))
            {
                AdicionarItem(idItem);
            }
        }catch (Exception e){
            e.getMessage();
            e.getStackTrace();
            System.out.println("ops algo deu errado");
        }
        return null;
    }

    public void deletar(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                repository.findById(id);
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao buscar");
        }
    }

}
