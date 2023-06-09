package com.nashss.se.exchange.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.exchange.activity.requests.SearchTypeZipRequest;
import com.nashss.se.exchange.activity.results.SearchTypeZipResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchTypeZipLambda
        extends LambdaActivityRunner<SearchTypeZipRequest, SearchTypeZipResult>
        implements RequestHandler<LambdaRequest<SearchTypeZipRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchTypeZipRequest> input, Context context) {
        log.info("handleRequest");
        System.out.println("SearchTypeZipRequest.handleRequest() with input: " + input + " " + context);
        return super.runActivity(
                () -> input.fromQuery(query ->
                        SearchTypeZipRequest.builder()
                                .withType(query.get("type"))
                                .withZipCode(query.get("zipCode"))
                                .withCriteria(query.get("q"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.providesSearchTypeZipActivity().handleRequest(request)
        );
    }
}
