package kotlintraining.lesson5;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.HttpHeaders;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class MyResourceImplTest {

    @Mock
    private IntegerService integerProvider;

    @Mock
    private HttpHeaders httpHeaders;

    @InjectMocks
    private MyResourceImpl testSubject;

    @Test
    public void testGetValue() {

        int id = 10;
        int expectedValue = 20;
        Mockito.when(integerProvider.getInteger(id)).thenReturn(expectedValue);

        Assert.assertEquals(expectedValue, testSubject.getValue(id));
    }

    @Test
    public void testGetValue_HeaderOverride() {

        int id = 10;
        int expectedValue = 20;
        Mockito.when(httpHeaders.getRequestHeader("test")).thenReturn(Collections.singletonList(String.valueOf(expectedValue)));

        Assert.assertEquals(expectedValue, testSubject.getValue(id));

        Mockito.verify(integerProvider, Mockito.never()).getInteger(Mockito.anyInt());
    }

}