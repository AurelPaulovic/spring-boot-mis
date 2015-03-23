package sk.anext.msi.ui.component;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("SessionPanel")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionPanel implements Serializable {
    private static final long serialVersionUID = 5463200694501281756L;
    
    private final AtomicInteger userAddedCount = new AtomicInteger(0);

    public Integer getUserAddedCount() {
        return userAddedCount.get();
    }

    public void setUserAddedCount(Integer userAddedCount) {
        this.userAddedCount.set(userAddedCount);
    }
    
    public void incrementAddedCount() {
        this.userAddedCount.addAndGet(1);
    }
}
