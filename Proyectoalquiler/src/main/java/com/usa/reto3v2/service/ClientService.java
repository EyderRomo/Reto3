package com.usa.reto3v2.service;

import com.usa.reto3v2.entities.Client;
import com.usa.reto3v2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public void save(Client firstClient){
        if(firstClient.getIdClient()==null){
              clientRepository.save(firstClient);
        }
        else {
            Optional<Client> a =clientRepository.getClient(firstClient.getIdClient());
            if(a.isPresent()){
                  a.get();
            }
            else{
                   clientRepository.save(firstClient);
            }
        }
    }
    public Client Update(Client firstClient){
        if(firstClient.getIdClient() != null){
            Optional<Client> c =clientRepository.getClient(firstClient.getIdClient());
            if(c.isPresent()){
                if(firstClient.getName()!=null){
                    c.get().setName(firstClient.getName());
                }
                if(firstClient.getEmail()!=null){
                    c.get().setEmail(firstClient.getEmail());
                }
                if(firstClient.getPassword()!=null){
                    c.get().setPassword(firstClient.getPassword());
                }
                if(firstClient.getAge()!=null){
                    c.get().setAge(firstClient.getAge());
                }
                clientRepository.save(c.get());
                    return c.get();

            }
            else{
                return firstClient;
            }
        }
        else{
            return firstClient;
        }
    }
    public boolean delete(int id){
        boolean marca=false;
        Optional<Client> c =clientRepository.getClient(id);
        if(c.isPresent()){
            clientRepository.delete(c.get());
            marca=true;
        }
        return marca;

    }
}