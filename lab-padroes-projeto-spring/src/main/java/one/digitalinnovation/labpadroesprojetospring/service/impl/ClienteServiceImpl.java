package one.digitalinnovation.labpadroesprojetospring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.labpadroesprojetospring.model.Cliente;
import one.digitalinnovation.labpadroesprojetospring.model.ClienteRepository;
import one.digitalinnovation.labpadroesprojetospring.model.Endereco;
import one.digitalinnovation.labpadroesprojetospring.model.EnderecoRepository;
import one.digitalinnovation.labpadroesprojetospring.service.ClienteService;
import one.digitalinnovation.labpadroesprojetospring.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {


    // Singleton : ImplementaR os Componentes do Spring com @Autowired
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    //  Strategy: Implementar os metodos definidos na interface
    //  Facade: Abstrair integrações com subsitemas, promovendo uma interface simples 

    
    public Iterable<Cliente> buscarTodos(){
        // Buscar todos os clientes
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarNome(String nome){
        Cliente cliente = clienteRepository.findByNome(nome);
        return cliente;
    }

    

    @Override
    public Cliente buscarPorId(Long id){
        // Buscar cliente por ID
        @SuppressWarnings("null")
        Optional <Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente){
       
        salvarClienteComCep(cliente);
    }


    @SuppressWarnings("null")
    @Override
    public void atualizar(Long id, Cliente cliente) {

     Optional <Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarClienteComCep(cliente);
        }
        
    }

    @SuppressWarnings("null")
    @Override
    public void deletar(Long id) {
       clienteRepository.deleteById(id);

    }

    
    private void salvarClienteComCep(Cliente cliente) {
        //Verificar se o Endereco do Cliente já existe (pelo cep)
       
        String cep = cliente.getEndereco().getCep();
       Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
            
        // Caso não exista, integrar com o  ViaCep e persistir o Retorno
        Endereco novoEndereco = viaCepService.consultarCep(cep);
           enderecoRepository.save(novoEndereco);   
           return novoEndereco;
        });

        cliente.setEndereco(endereco);

        // Inserir cliente

        clienteRepository.save(cliente);
    }

}
