package sk.anext.msi.bo.neo.domain;

import java.util.Collection;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class NeoType {

    @GraphId
    private Long id;

    @Indexed(unique = true, failOnDuplicate = true)
    private String code;

    @RelatedTo(type = "TYPE_IS", direction = Direction.INCOMING)
    private Collection<NeoUser> users;

    public NeoType() {
    }

    public NeoType(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "NeoType [id=" + id + ", code=" + code + ", users=" + users + "]";
    }

    public enum Type {

        USER("USER"), ADMIN("ADMIN");

        private Type(String code) {
            this.code = code;
        }

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }
    
}
