package com.shyam.orders.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public class ExceptionHandler implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        return chain.next(request).map(response -> {
            if (response.isValid()) {
                return response;
            }

            List<GraphQLError> errors = response.getErrors().stream()
                    .map(error -> {
                        GraphqlErrorBuilder<?> builder = GraphqlErrorBuilder.newError();
                        // ...
                        return builder.build();
                    })
                    .toList();

            return response.transform(builder -> builder.errors(errors).build());
        });
    }
}