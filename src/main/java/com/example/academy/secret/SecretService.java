package com.example.academy.secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretService {

    @Autowired
    SecretRepository secretRepository;

    public Secret save(Secret secret){
        return secretRepository.save(secret);
    }

    public List<Secret> getAll(){
        return secretRepository.findAll();
    }

    public Secret updateById(long id , Secret secret){
        Secret secret1 = secretRepository.findById(id).get();
        secret1.setCodice(secret.getCodice());
        return secretRepository.save(secret1);
    }

    public boolean deleteById(long id){
        try {
            secretRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
