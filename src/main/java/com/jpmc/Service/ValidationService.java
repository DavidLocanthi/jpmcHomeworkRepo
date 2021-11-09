package com.jpmc.Service;

import com.jpmc.Models.Request;
import com.jpmc.Models.Response;
import com.jpmc.Models.Result;
import com.jpmc.config.Config;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ValidationService {

    @Autowired
    private Config config;

    @Autowired
    private HttpService httpService;


    public Response validate(Request request) throws IOException, InterruptedException {
        List<Result> results = new ArrayList<>();
        List<String> providers = request.getProviders();

        if (providers.size() == 0) {
            providers.addAll(config.getProviderMap().keySet());
        }
        for (String prov: providers) {
            results.add(httpService.validate(request.getAccountNumber(), prov));
        }

        return new Response(results);
    }

}
