package com.nashss.se.exchange.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.exchange.activity.requests.GetItemRequest;
import com.nashss.se.exchange.activity.requests.UpdateItemRequest;
import com.nashss.se.exchange.activity.results.UpdateItemResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateItemLambda
        extends LambdaActivityRunner<UpdateItemRequest, UpdateItemResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateItemRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateItemRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> {
                    UpdateItemRequest unauthenticatedRequest = input.fromBody(UpdateItemRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateItemRequest.builder()
                                    .withItemId(unauthenticatedRequest.getItemId())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withZipCode(unauthenticatedRequest.getZipCode())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .withMemberId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdatePlaylistActivity().handleRequest(request)
        );
    }
}

