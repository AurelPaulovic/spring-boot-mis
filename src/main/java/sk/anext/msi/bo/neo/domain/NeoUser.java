package sk.anext.msi.bo.neo.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class NeoUser {

    @GraphId
    private Long id;

    @Indexed(unique = true, failOnDuplicate = true)
    private String name;
    private Integer age;
    private Boolean enabled;

    @RelatedTo(type = "TYPE_IS", direction = Direction.OUTGOING)
    private NeoType type;

    public NeoUser() {
    }

    public NeoUser(String name, Integer age,Boolean enabled) {
        this.name = name;
        this.age = age;
        this.enabled=enabled;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "NeoUser [id=" + id + ", name=" + name + ", age=" + age + ", enabled=" + enabled + ", type=" + type
                + "]";
    }

    public NeoType getType() {
        return type;
    }

    public void setType(NeoType type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
