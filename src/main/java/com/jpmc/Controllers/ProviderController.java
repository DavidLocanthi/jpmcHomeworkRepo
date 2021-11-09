package com.jpmc.Controllers;

import com.jpmc.Models.Request;
import com.jpmc.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ProviderController {
    @Autowired
    private ValidationService validationService;

    @RequestMapping(value = "/validate")
    @ResponseBody
    public Request validateAccounts (@RequestBody Request request) throws IOException, InterruptedException {
        validationService.validate(request);
        return request;
    }

    // Doesn't need to exist anymore, but I might as well leave it here
    @RequestMapping(value = "/test")
    @ResponseBody
    public Request testerino() {
        List<String> testList = new ArrayList<>();
        testList.add("hi2");
        testList.add("you222");
        return new Request(123123, testList);
    }

    // Doesn't need to exist anymore, but I might as well leave it here
    @RequestMapping(value = "/test2")
    @ResponseBody
    public Set<String> loadTest() {
        return validationService.getConfig().getProviderMap().keySet();
    }
}
