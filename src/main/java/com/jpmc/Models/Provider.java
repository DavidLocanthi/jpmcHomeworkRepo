package com.jpmc.Models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@RequiredArgsConstructor
public class Provider {

    @NonNull private String name;
    @NonNull private String url;

}
