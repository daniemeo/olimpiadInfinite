package it.solvingteam.course.olimpiadinfinite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.course.olimpiadinfinite.dto.messages.NationDto;
import it.solvingteam.course.olimpiadinfinite.mapper.NationMapper;
import it.solvingteam.course.olimpiadinfinite.model.nation.Nation;
import it.solvingteam.course.olimpiadinfinite.repository.NationRepository;


@Service
public class NationService {
	 @Autowired
	 NationMapper nationMapper;
	 @Autowired
	 NationRepository nationRepository;
	 
	 public List<NationDto> findAll() {
			List<Nation> allNation = this.nationRepository.findAll();
			return nationMapper.convertEntityToDto(allNation);
		}
	 
		public Optional<Nation> findById(Long id) {
			return this.nationRepository.findById(id);
		}

}
