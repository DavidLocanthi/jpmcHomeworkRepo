package com.jpmc.Models;

import com.jpmc.config.Config;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Request {

    @NonNull @ToString.Exclude private int accountNumber;
    @ToString.Exclude private final List<String> providers;



}
