import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compass.cais.*;

@RestController
@RequestMapping(/company)
public class CompanyController{

    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping
    @Cacheable(value = "listOfTheCompanies")
    public Page<CompanyRequestDTO> listCompanies(@RequestParam (require = false) String name,
                                                 @PageableDefault (sort="name" ,direction = Direction.ASC, page = 0, size = 10) Pageable pagination ){
        if(name == null){
            Page<Company> companies = companyRepository.findAll(pagination);
            return comapnies;
        }else{
            Page<Company> companies = companyRepository.findByName(name, pagination);
            return companies;
        }

    }

}