package com.jpmc.Service;

import com.jpmc.Models.Request;
import com.jpmc.Models.Response;
import com.jpmc.Models.Result;
import com.jpmc.config.Config;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.server.core.MappingDiscoverer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@ContextConfiguration(classes = {HttpClient.class, Config.class })
@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest extends Mockito {

    @Mock Config config;

    @Mock
    private HttpService httpService;

    @InjectMocks
    private ValidationService validationService;

    private Request twoProviders;
    private Request noProviders;

    @Before
    public void setupVariables() {
        List<String> testList = new ArrayList<>();
        testList.add("george");
        testList.add("alfred");
        twoProviders = new Request(123123, testList);
        noProviders = new Request(112233, new ArrayList<String>());

        Result first = new Result("george", false);
        Result second = new Result("alfred", true);
        List<Result> addEm = new ArrayList<>();
        addEm.add(first);
        addEm.add(second);
    }

    @Test
    public void contextLoads() {
        Assertions.assertTrue(true);
    }

    @Test
    public void twoProvidersTest() throws IOException, InterruptedException {
        Result first = new Result("george", false);
        Result second = new Result("alfred", true);
        List<Result> addEm = new ArrayList<>();
        addEm.add(first);
        addEm.add(second);
        Response goal = new Response(addEm);

        when(httpService.validate(123123, "george")).thenReturn(first);
        when(httpService.validate(123123, "alfred")).thenReturn(second);

        Assertions.assertEquals(validationService.validate(twoProviders), goal);

    }

    @Test
    public void noProvidersTest() throws IOException, InterruptedException {
        Result first = new Result("george", false);
        Result second = new Result("steven", false);
        Result third = new Result("alfred", true);
        List<Result> addEm = new ArrayList<>();
        addEm.add(first);
        addEm.add(second);
        addEm.add(third);
        Response goal = new Response(addEm);
        Map<String, String> configSet = new HashMap<>();
        configSet.put("george", "testurl1");
        configSet.put("steven", "testurl2");
        configSet.put("alfred", "testurl3");

        when(httpService.validate(112233, "george")).thenReturn(first);
        when(httpService.validate(112233, "steven")).thenReturn(second);
        when(httpService.validate(112233, "alfred")).thenReturn(third);
        when(config.getProviderMap()).thenReturn(configSet);

        System.out.println(validationService.validate(noProviders));
        System.out.println(goal);

        // LOMBOK LETTING ME DOWN HERE. Not worth the time to fix.
        Assertions.assertTrue(validationService.validate(noProviders).equals(goal));


    }
}
