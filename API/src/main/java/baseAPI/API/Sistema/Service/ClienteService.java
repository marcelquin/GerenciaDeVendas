package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.Pedido;
import baseAPI.API.Sistema.Repository.CLienteRepository;
import baseAPI.API.Sistema.Repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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

    public ResponseEntity<byte[]> verImagemclientePorId(long id) throws IOException, SQLException {
        Cliente entidade = repository.findById(id).get();
        byte[] imageBytes = null;
        imageBytes = entidade.getImagem().getBytes(1, (int) entidade.getImagem().length());
        return ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
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

    public void adicionarImagemCliente(Long id, MultipartFile file)
    {
        try {
            if(repository.existsById(id))
            {
                Cliente cliente = repository.findById(id).get();
                if(!file.isEmpty()){
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    cliente.setImagem(blob);
                }
                repository.save(cliente);
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao salvar");
        }
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
