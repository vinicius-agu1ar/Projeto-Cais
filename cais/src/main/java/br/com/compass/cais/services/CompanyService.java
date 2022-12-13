package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.exceptions.CompanyNotFoundException;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

//    private final CompanyRepository repository;

    private final CompanyDTOAssembler assembler;

    public Company buscarOuFalhar(Long companyId){
//        return repository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        return null;
    }

    @Transactional
    public Company adicionar(Company company){
//        return  repository.save(company);
        return company;
    }

    @Transactional
    public void excluir(Long companyId){
        try{
//            repository.deleteBy(companyId);
//            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new CompanyNotFoundException();
        }catch (DataIntegrityViolationException e) { //erro se tentar excluir uma company que está em uso
//            throw new EntidadeEmUsoException(); //criar uma classe de erro para entidade em uso
        }
    }

}
