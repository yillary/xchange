package com.nashss.se.exchange.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.exchange.activity.requests.UpdateItemRequest;
import com.nashss.se.exchange.activity.results.UpdateItemResult;


public class UpdateItemLambda
        extends LambdaActivityRunner<UpdateItemRequest, UpdateItemResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateItemRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateItemRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateItemRequest unauthenticatedRequest = input.fromBody(UpdateItemRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateItemRequest.builder()
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .withZipCode(unauthenticatedRequest.getZipCode())
                                    .withExchanged(unauthenticatedRequest.getExchanged())
                                    .withItemId(unauthenticatedRequest.getItemId())
                                    .withEmail(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.providesUpdateItemActivity().handleRequest(request)
        );
    }
}

