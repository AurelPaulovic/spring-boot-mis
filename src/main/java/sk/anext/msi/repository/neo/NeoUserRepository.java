package sk.anext.msi.repository.neo;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import sk.anext.msi.bo.neo.domain.NeoUser;

public interface NeoUserRepository extends GraphRepository<NeoUser>{

    public List<NeoUser> findByAge(Integer age);
}
