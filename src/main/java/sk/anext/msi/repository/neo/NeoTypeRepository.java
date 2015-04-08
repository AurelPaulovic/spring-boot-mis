package sk.anext.msi.repository.neo;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import sk.anext.msi.bo.neo.domain.NeoType;

public interface NeoTypeRepository extends GraphRepository<NeoType>, RelationshipOperationsRepository<NeoType> {

    public NeoType findByCode(String code);
}
