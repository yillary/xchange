package com.nashss.se.exchange.lambda;
package com.nashss.se.exchange.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.exchange.activity.requests.CreateItemRequest;
import com.nashss.se.exchange.activity.results.CreateItemResult;
import com.nashss.se.exchange.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.exchange.lambda.LambdaActivityRunner;
import com.nashss.se.exchange.lambda.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateItemLambda
        extends LambdaActivityRunner<CreateItemRequest, CreateItemResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateItemRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();


    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateItemRequest> input, Context context) {
        () -> {
            CreateItemRequest unauthenticatedRequest = input.fromBody(CreateItemRequest.class);
            return input.fromUserClaims(claims ->
                    CreateItemRequest.builder()
                            .withTitle(unauthenticatedRequest.getTitle())
                            .withDescription(unauthenticatedRequest.getDescription())
                            .withType(unauthenticatedRequest.getType())
                            .withExchanged(unauthenticatedRequest.getExchanged())
                            .withImages(unauthenticatedRequest.getImages())
                            .withZipCode(unauthenticatedRequest.getZipCode())
                            .build());
        },
                (request, serviceComponent) ->
                        serviceComponent.provideCreatePlaylistActivity().handleRequest(request)
        );
    }
}
