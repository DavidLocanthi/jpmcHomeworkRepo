package com.jpmc.Models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Response {

    @NonNull private List<Result> results;
}
