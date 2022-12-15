package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.exceptions.CompanyNotFoundException;
import br.com.compass.cais.exceptions.EntityInUseException;
import br.com.compass.cais.repository.CompanyRepository;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import br.com.compass.cais.services.assembler.CompanyInputDisassembler;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.response.CompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    private final CompanyDTOAssembler assembler;

    private final CompanyInputDisassembler disassembler;

    public Page<CompanyResponseDTO> findAll(Pageable pageable) {
        Page<Company> pageCompanies = repository.findAll(pageable);
        List<CompanyResponseDTO> companyResponseDTOS = assembler.toCollectionModel(pageCompanies.getContent());
        return new PageImpl<>(companyResponseDTOS, pageable, pageCompanies.getTotalElements());
    }

    public CompanyResponseDTO findBy(Long id) {
        Company company = fetchOrFail(id);
        return assembler.toModel(company);
    }

    public CompanyResponseDTO update(Long id, CompanyRequestDTO request) {
        Company company = fetchOrFail(id);
        disassembler.copyToDomainObject(request,company);
        company = create(company);
        return assembler.toModel(company);
    }

    public CompanyResponseDTO create(CompanyRequestDTO request) {
        Company company = disassembler.toDomainObject(request);
        company = create(company);
        return assembler.toModel(company);
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
            throw new EntityInUseException();
        }
    }

    public Company fetchOrFail(Long companyId){
        return repository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
    }

}
