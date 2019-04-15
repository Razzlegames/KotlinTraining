package kotlintraining.lesson5;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class MyResourceImpl implements MyResource {

    @Inject
    private IntegerService integerProvider;

    @Context
    private HttpHeaders httpHeaders;

    @Override
    public int getValue(int id) {

        if (httpHeaders.getRequestHeader("test") != null && httpHeaders.getRequestHeader("test").size() > 0) {
            return Integer.parseInt(httpHeaders.getRequestHeader("test").get(0));
        }

        return integerProvider.getInteger(id);
    }
}
