package com.jpmc.Service;

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
import java.time.Duration;
import java.time.temporal.TemporalUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
@Data
public class HttpService {

    private HttpClient httpClient = HttpClient.newHttpClient();

    @Autowired
    private Config config;

    public HttpService() {
    }

    public Result validate(int accNum, String provider) throws IOException, InterruptedException {

        // likely need logic here for bad providers
        HttpRequest Httprequest = HttpRequest.newBuilder()
                .uri(URI.create(config.check(provider)))
                .timeout(Duration.of(2, SECONDS))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(accNum))).build();

        HttpResponse<String> response = httpClient.send(Httprequest,
                HttpResponse.BodyHandlers.ofString());

        return new Result(provider, Boolean.parseBoolean(response.body()));
    }
}
