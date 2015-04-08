package sk.anext.msi.repository.neo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;

import sk.anext.msi.SpringBootMsiApplicationTests;
import sk.anext.msi.bo.neo.domain.NeoType;

public class NeoTypeRepositoryTest extends SpringBootMsiApplicationTests {

    private static final String admin = "ADMIN";
    private static final String user = "USER";

    private static final Log log = LogFactory.getLog(NeoTypeRepositoryTest.class);

    @Autowired
    private NeoTypeRepository typeRepository;

    @Test
    public void createData() {
        Result<NeoType> types = typeRepository.findAll();

        // NeoType one = new NeoType(NeoType.Type.ADMIN.getCode());
        // NeoType two = new NeoType(NeoType.Type.USER.getCode());

        // typeRepository.save(one);
        // typeRepository.save(two);

        int size = 0;

        for (NeoType type : types) {
            log.info("Types : " + type);
            size++;
        }

        Assert.assertEquals(2, size);
    }

    @Test
    public void getData() {

        NeoType ad = typeRepository.findByCode(NeoType.Type.ADMIN.getCode());
        Assert.assertEquals(admin, ad.getCode());

        NeoType u = typeRepository.findByCode(NeoType.Type.USER.getCode());
        Assert.assertEquals(user, u.getCode());

    }

}
