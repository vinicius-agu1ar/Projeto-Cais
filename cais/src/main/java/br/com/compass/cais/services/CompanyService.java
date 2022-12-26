package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.enums.Origin;
import br.com.compass.cais.exceptions.CompanyNotFoundException;
import br.com.compass.cais.exceptions.CompanyAlreadySelectedException;
import br.com.compass.cais.exceptions.EntityInUseException;
import br.com.compass.cais.repository.CompanyRepository;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import br.com.compass.cais.services.assembler.CompanyInputDisassembler;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.ship.ShipResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    private final ShipRepository repositoryShip;

    private final ShipService shipService;

    private final CompanyDTOAssembler assembler;

    private final ShipDTOAssembler shipAssembler;

    private final CompanyInputDisassembler disassembler;

    public List<CompanyResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service Company");
        Page<Company> pageCompanies = repository.findAll(pageable);
        return assembler.toCollectionModel(pageCompanies.getContent());
    }

    public List<ShipResumeResponseDTO> findAll(Long companyId) {
        log.info("Chamando método findAll, listando todos ships de uma determinada company - Service Company");
        Company company = fetchOrFail(companyId);
        List<Ship> companyShips = repositoryShip.findByCompanyId(company.getId());
        return shipAssembler.toCollectionModelResume(companyShips);
    }

    public CompanyResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Company");
        Company company = fetchOrFail(id);
        return assembler.toModel(company);
    }

    public CompanyResponseDTO findByName(String name) {
        log.info("Chamando método findByName - Service Company");
        Company company = fetchOrFail(name);
        return assembler.toModel(company);
    }

    public CompanyResponseDTO update(Long id, CompanyRequestDTO request) {
        log.info("Chamando método update - Service Company");
        Company company = fetchOrFail(id);
        disassembler.copyToDomainObject(request,company);
        company = create(company);
        return assembler.toModel(company);
    }
    @Transactional
    public CompanyResponseDTO create(CompanyRequestDTO request) {
        log.info("Chamando método create - Service Company");
        Company company = disassembler.toDomainObject(request);
        company = create(company);
        return assembler.toModel(company);
    }

    @Transactional
    public Company create(Company company){
        log.info("Chamando método create (salvando no repository) - Service Company");
        try {
            return repository.save(company);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException();
        }

    }

    @Transactional
    public void bind(Long id, Long shipId) {
        log.info("Chamando método bind - Service Company");
        Company company = fetchOrFail(id);
        Ship ship = shipService.fetchOrFail(shipId);
        if(ship.getCompany() != null){
            throw new CompanyAlreadySelectedException();
        }
        ship.setCompany(company);
    }

    @Transactional
    public void unlink(Long id) {
        log.info("Chamando método unlink - Service Company");
        Ship ship = shipService.fetchOrFail(id);
        ship.setCompany(null);
    }

    @Transactional
    public void delete(Long companyId){
        log.info("Chamando método delete (excluindo no repository) - Service Company");
        try{
            repository.deleteById(companyId);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new CompanyNotFoundException();
        }catch (DataIntegrityViolationException e) { //erro se tentar excluir uma company que está em uso
            throw new EntityInUseException();
        }
    }

    private Company fetchOrFail(Long companyId){
        log.info("Chamando método fetchOrFail - Service Company");
        return repository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
    }

    private Company fetchOrFail(String name){
        log.info("Chamando método fetchOrFail - Service Company");
        return Optional.ofNullable(repository.findByName(name)).orElseThrow(CompanyNotFoundException::new);
    }

    public List<CompanyResponseDTO> verifyCompanyResponseDTO(Origin origin, Pageable pageable, String name) {
        log.info("Chamando método verifyCompanyResponseDTO - Service Company");
        if(name != null){
            List<CompanyResponseDTO> list = new ArrayList<>();
            CompanyResponseDTO byName = findByName(name);
            list.add(byName);
            return list;
        }else if(origin == null){
            return findAll(pageable);
        }else{
            List<Company> companies = repository.findByOrigin(origin, pageable).getContent();
            return assembler.toCollectionModel(companies);
        }
    }

}
