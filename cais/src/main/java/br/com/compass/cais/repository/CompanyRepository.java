
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company,long>{
    Page<Company> findByName(String name, Pageable pagination);

}