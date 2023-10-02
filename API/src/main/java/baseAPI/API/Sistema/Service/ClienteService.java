package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.PedidoDTO;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.Pedido;
import baseAPI.API.Sistema.Repository.CLienteRepository;
import baseAPI.API.Sistema.Repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private CLienteRepository repository;
    @Autowired
    private  PedidoRepository pedidoRepository;

    public Cliente listar()
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

    public Cliente buscaPorId(Long id)
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

    public ClienteDTO salvar(ClienteDTO clienteDTO)
    {
        try{
            if(clienteDTO != null)
            {
                Cliente cliente = new Cliente();
                BeanUtils.copyProperties(clienteDTO, cliente);
                repository.save(cliente);
                System.out.println("Salvo com sucesso");
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao salvar");
        }
        return null;
    }

    public void adicionarPedido(Long idcliente, Long idPedido)
    {
        if(idcliente != null && idPedido != null)
        {
            if(repository.existsById(idcliente))
            {
                Cliente cliente = repository.findById(idcliente).get();
                if(pedidoRepository.existsById(idPedido))
                {
                    Pedido pedido = pedidoRepository.findById(idPedido).get();
                    List<Pedido> pedidos = new ArrayList<>();
                    pedidos.add(pedido);
                    cliente.setPedidos(pedidos);
                }
            }
        }
    }


    public ClienteDTO editar(Long id,ClienteDTO clienteDTO)
    {
        try{
            if(repository.existsById(id))
            {
                if(clienteDTO != null)
                {
                    Cliente cliente = repository.findById(id).get();
                    BeanUtils.copyProperties(clienteDTO, cliente);
                    repository.save(cliente);
                    System.out.println("Salvo com sucesso");
                }
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao editar");
        }
        return null;
    }

    public void deletar(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                repository.deleteById(id);
            }
        }catch (Exception e)
    {
        e.getMessage();
        System.out.println("erro ao deletar");
    }
    }

}
