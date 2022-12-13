package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.exceptions.CompanyNotFoundException;
import br.com.compass.cais.exceptions.EntityInUseException;
import br.com.compass.cais.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    private static final String MSG_COMPANY_IS_IN_USE = "Company code %d cannot be removed as it is in use";

    public Company fetchOrFail(Long companyId){
        return repository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
    }

    @Transactional
    public Company create(Company company){
        return repository.save(company);
    }

    @Transactional
    public void delete(Long companyId){
        try{
            repository.deleteById(companyId);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new CompanyNotFoundException();
        }catch (DataIntegrityViolationException e) { //erro se tentar excluir uma company que está em uso
            throw new EntityInUseException(String.format(MSG_COMPANY_IS_IN_USE, companyId));
        }
    }

}
