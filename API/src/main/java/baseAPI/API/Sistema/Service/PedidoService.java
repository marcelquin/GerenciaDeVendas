package baseAPI.API.Sistema.Service;


import baseAPI.API.Sistema.DTO.PedidoDTO;

import baseAPI.API.Sistema.Enum.Status;
import baseAPI.API.Sistema.Model.*;

import baseAPI.API.Sistema.Repository.*;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static baseAPI.API.Sistema.Enum.Status.*;


@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private CLienteRepository cRepository;
    @Autowired
    private CarrinhoRepository CrRepository;

    @Autowired
    private ProdutoRepository PRepository;

    @Autowired
    private ItemPedidoService ITService;

    public Pedido listar()
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

    public Pedido buscaPorId(Long id)
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

    public PedidoDTO salvar(Long idcar, Long idCli)
    {
        try{
            Pedido pedido = new Pedido();
            Cliente cliente = new Cliente();
            if(CrRepository.existsById(idcar))
            {
                Carrinho carrinho = CrRepository.findById(idcar).get();
                pedido.setProdutos(carrinho);
            }
            if(cRepository.existsById(idCli))
            {
                cliente = cRepository.findById(idCli).get();
                pedido.setCliente(cliente);
            }
            pedido.setValorTotal(pedido.CalValorTotal());
            pedido.setStatus(AGUARDANDOPAGAMENTO);
            pedido.setDataPedido(LocalDate.now());
            repository.save(pedido);

            List<Pedido> clientePedidos = new ArrayList<>();
            Pedido idpedido = repository.getReferenceById(pedido.getId());
            clientePedidos.add(idpedido);
            cliente.setPedidos(clientePedidos);
            cRepository.save(cliente);
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao buscar");
        }
        return null;
    }

    public PedidoDTO editar(Long idPed, Long idcar, Long idCli)
    {
        try {
            if(repository.existsById(idPed))
            {
               Pedido pedido = repository.findById(idPed).get();
                Cliente cliente = new Cliente();
                if(CrRepository.existsById(idcar))
                {
                    Carrinho carrinho = CrRepository.findById(idcar).get();
                    pedido.setProdutos(carrinho);
                }
                if(cRepository.existsById(idCli))
                {
                    cliente = cRepository.findById(idCli).get();
                    pedido.setCliente(cliente);
                }
                pedido.setValorTotal(pedido.CalValorTotal());
                pedido.setStatus(AGUARDANDOPAGAMENTO);
                pedido.setDataPedido(LocalDate.now());
                repository.save(pedido);

                List<Pedido> clientePedidos = new ArrayList<>();
                Pedido idpedido = repository.getReferenceById(pedido.getId());
                clientePedidos.add(idpedido);
                cliente.setPedidos(clientePedidos);
                cRepository.save(cliente);

            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao buscar");
        }
        return null;
    }


    public void alterarStatusPagamento(Long id, Status status)
    {
        try{
            if(repository.existsById(id)) {
                Pedido pedido = repository.findById(id).get();
                if (status == PAGO) {
                    pedido.setStatus(PAGO);
                    pedido.setDatapagamento(LocalDate.now());
                }
                if (status == CANCELADO) {
                    pedido.setStatus(CANCELADO);
                    pedido.setDataCancelamento(LocalDate.now());
                }
                repository.save(pedido);
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao buscar");
        }
    }

    public void deletar(Long id)
    {
        try {
           if(repository.existsById(id))
           {
               repository.deleteById(id);
           }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao buscar");
        }
    }
}
