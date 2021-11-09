package com.jpmc.Models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Data
public class Result {
    @NonNull private String name;
    @NonNull private Boolean isValid;

}
