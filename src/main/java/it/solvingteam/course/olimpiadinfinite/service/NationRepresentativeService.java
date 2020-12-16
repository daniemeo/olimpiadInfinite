package it.solvingteam.course.olimpiadinfinite.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.course.olimpiadinfinite.dto.messages.DeleteNationRepresentantMessageDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.InsertNationRepresentativeMessageDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeSearchFilterDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeShowDto;
import it.solvingteam.course.olimpiadinfinite.mapper.NationRepresentativeMapper;
import it.solvingteam.course.olimpiadinfinite.model.nation.Nation;
import it.solvingteam.course.olimpiadinfinite.model.nationRepresentative.NationRepresentative;
import it.solvingteam.course.olimpiadinfinite.model.user.User;
import it.solvingteam.course.olimpiadinfinite.repository.NationRepresentativeRepository;






@Service
public class NationRepresentativeService {
	 
	 @Autowired
	 NationRepresentativeRepository nationRepresentativeRepository;
	 
	 @Autowired
	 NationRepresentativeMapper nationRepresentativeMapper;
	 
	 @Autowired 
	 NationService nationService;
	 
	 @Autowired
	 UserService userService;
	 
	 @Autowired
	 private EntityManager entityManager;
	 
//	 public List<NationRepresentativeDto> findAll() {
//			List<NationRepresentative> allNationRepresentative = this.nationRepresentativeRepository.findAll();
//			return nationRepresentativeMapper.convertEntityToDto(allNationRepresentative);
//		}
	 public List<NationRepresentative> findAll() {
//			List<NationRepresentative> allNationRepresentative = this.nationRepresentativeRepository.findAll();
			return this.nationRepresentativeRepository.findAll();
		}
	 
	 public Optional<NationRepresentative> findById(Long id) {
			return this.nationRepresentativeRepository.findById(id);
		}
	 
	 public Optional<NationRepresentative> findByFiscalCode(String fiscalCode){
		  return this.nationRepresentativeRepository.findByFiscalCode(fiscalCode);
	  }

	 public void insertNationRepresentative(InsertNationRepresentativeMessageDto insertNationRepresentativeMessageDto)  {
			NationRepresentative nationRepresentative =nationRepresentativeMapper.convertDtoToEntity(insertNationRepresentativeMessageDto);
			Nation nation = nationService.findById(nationRepresentative.getNation().getId()).orElse(null);
			nationRepresentative.setNation(nation);
			User user = userService.findUserByUSername(nationRepresentative.getUser().getUsername()).orElse(null);
			nationRepresentative.setUser(user);
			this.nationRepresentativeRepository.save(nationRepresentative);
			
		}
	 
		public List<NationRepresentativeDto> findBySearchParameter(NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto) {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<NationRepresentative> cq = cb.createQuery(NationRepresentative.class);

			Root<NationRepresentative> representative = cq.from(NationRepresentative.class);
			List<Predicate> predicates = new ArrayList<>();
			
			
			if (nationRepresentativeSearchFilterDto.getName() != null && !nationRepresentativeSearchFilterDto.getName().equals("")) {
				predicates.add(cb.like(representative.get("name"), "%" + nationRepresentativeSearchFilterDto.getName() + "%"));
			}
			if (nationRepresentativeSearchFilterDto.getSurname()!= null && !nationRepresentativeSearchFilterDto.getSurname().equals("")) {
				predicates.add(cb.like(representative.get("surname"), "%" + nationRepresentativeSearchFilterDto.getSurname() + "%"));
			}
			if (nationRepresentativeSearchFilterDto.getFiscalCode()!= null && !nationRepresentativeSearchFilterDto.getFiscalCode().equals("")) {
				predicates.add(cb.like(representative.get("fiscalCode"), "%" + nationRepresentativeSearchFilterDto.getFiscalCode() + "%"));
			}
			if (nationRepresentativeSearchFilterDto.getUserSearchDto() != null  && !nationRepresentativeSearchFilterDto.getUserSearchDto().getUsername().equals("")) {
				User user =userService.findUserByUSername(nationRepresentativeSearchFilterDto.getUserSearchDto().getUsername()).orElse(null);
				
				predicates.add(cb.equal(representative.get("user"), user));
			}
			if (nationRepresentativeSearchFilterDto.getNation()!= null && !nationRepresentativeSearchFilterDto.getNation().equals("")) {
				Nation nation = new Nation();
				nation.setId(Long.parseLong(nationRepresentativeSearchFilterDto.getNation()));
				predicates.add(cb.equal(representative.get("nation"), nation));
			}

			cq.where(predicates.toArray(new Predicate[0]));
			return nationRepresentativeMapper.convertEntityToDto(entityManager.createQuery(cq).getResultList());
		}
		
		public NationRepresentativeShowDto convertRepresentative(Long id) {
			NationRepresentative nationRepresentative = this.findById(id).orElse(null);
			return nationRepresentativeMapper.convertEntityToDtoShow(nationRepresentative);
		}
		
		public NationRepresentative getRepresentantFromDeleteDto(DeleteNationRepresentantMessageDto deleteNationRepresentantMessageDto) {
			NationRepresentative nationRepresentant = this.findById(Long.parseLong(deleteNationRepresentantMessageDto.getIdDelete())).orElse(null);
			return nationRepresentant;
		} 
		
		public void delete(Long id) {
			NationRepresentative nationRepresentative = nationRepresentativeRepository.findById(id).orElse(null);
			this.nationRepresentativeRepository.delete(nationRepresentative);
		}
}
