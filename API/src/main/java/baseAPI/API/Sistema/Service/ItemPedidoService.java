package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.ItemPedidoDTO;
import baseAPI.API.Sistema.Model.ItemPedido;
import baseAPI.API.Sistema.Model.Produto;
import baseAPI.API.Sistema.Repository.ItemPedidoRepository;
import baseAPI.API.Sistema.Repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;
    @Autowired
    private ProdutoRepository PRepository;


    public ItemPedido listar()
    {
        return ((ItemPedido) repository.findAll().stream());
    }

    public  ItemPedido buscarPorId(Long id)
    {
        return (ItemPedido) repository.findById(id).stream();
    }

    public ItemPedidoDTO salvar(ItemPedidoDTO itemPedidoDTO)
    {
        try{
            if(itemPedidoDTO != null)
            {
                ItemPedido itemPedido = new ItemPedido();
                BeanUtils.copyProperties(itemPedidoDTO, itemPedido);
                if(PRepository.existsById(itemPedidoDTO.getProduto()))
                {
                    Produto produto = PRepository.findById(itemPedidoDTO.getProduto()).get();
                    itemPedido.setProduto(produto);
                }
                itemPedido.setValorItem(itemPedido.CalValorItem());
                repository.save(itemPedido);
            }
        }catch (Exception e){
            e.getMessage();
            e.getStackTrace();
            System.out.println("ops algo deu errado");
        }
        return null;
    }

    public Double calculaValor(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                ItemPedido itemPedido = repository.findById(id).get();
                itemPedido.setValorItem(itemPedido.CalValorItem());
            }
        }catch (Exception e){
            e.getMessage();
            e.getStackTrace();
            System.out.println("ops algo deu errado");
        }
        return null;
    }
    public void adicionaItem(Long idItem, Long idProd)
    {
        try{
            if(repository.existsById(idItem))
            {
                ItemPedido itemPedido = repository.findById(idItem).get();

                if(PRepository.existsById(idProd))
                {
                    Produto produto = PRepository.findById(idProd).get();
                    itemPedido.setProduto(produto);
                }
                repository.save(itemPedido);
            }
        }catch (Exception e){
            e.getMessage();
            e.getStackTrace();
            System.out.println("ops algo deu errado");
        }
    }

    public void Editar(Long idItem, Double quantidade, Long idProd)
    {
        try{
            if(repository.existsById(idItem))
            {
                ItemPedido itemPedido = repository.findById(idItem).get();
                boolean b = PRepository.existsById(idProd);
                if(b);
                {
                    Produto produto = PRepository.findById(idProd).get();
                    itemPedido.setProduto(produto);
                    itemPedido.setValorItem(itemPedido.CalValorItem());
                }
                itemPedido.setQuantidade(quantidade);
                repository.save(itemPedido);
            }
        }catch (Exception e){
            e.getMessage();
            System.out.println("ops algo deu errado");
        }
    }

    public void baixaEstoque(Long idItem)
    {
        try{
            if(repository.existsById(idItem))
            {
                ItemPedido itemPedido = repository.findById(idItem).get();
                if(PRepository.existsById(itemPedido.getProduto().getId()))
                {
                    Produto produto = PRepository.findById(itemPedido.getProduto().getId()).get();
                    produto.setEstoque(produto.getEstoque() - itemPedido.getQuantidade());
                    PRepository.save(produto);
                }
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("ops algo deu errado");
        }
    }

    public void  deletar(Long id)
    {
        if(repository.existsById(id))
        {
            repository.deleteById(id);
        }
    }


}
