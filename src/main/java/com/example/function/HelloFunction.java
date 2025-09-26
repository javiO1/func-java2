package com.example.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import java.util.Optional;

public class HelloFunction {
    @FunctionName("hello")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {
        String name = request.getQueryParameters().get("name");
        if (name == null) {
            name = request.getBody().orElse("world");
        }
        String responseMessage = "Hello, " + name + "!";
        return request.createResponseBuilder(HttpStatus.OK).body(responseMessage).build();
    }
}
