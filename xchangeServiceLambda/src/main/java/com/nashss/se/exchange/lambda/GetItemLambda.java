package com.nashss.se.exchange.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.exchange.activity.requests.GetItemRequest;
import com.nashss.se.exchange.activity.results.GetItemResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetItemLambda
        extends LambdaActivityRunner<GetItemRequest, GetItemResult>
        implements RequestHandler<LambdaRequest<GetItemRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetItemRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetItemRequest.builder()
                                .withItemId(path.get("itemId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.providesGetItemActivity().handleRequest(request)
        );

    }
}
