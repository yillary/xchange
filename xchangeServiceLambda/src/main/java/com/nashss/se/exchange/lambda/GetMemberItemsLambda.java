package com.nashss.se.exchange.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.exchange.activity.requests.GetMemberItemsRequest;

import com.nashss.se.exchange.activity.results.GetMemberItemsResult;

public class GetMemberItemsLambda
        extends LambdaActivityRunner<GetMemberItemsRequest, GetMemberItemsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetMemberItemsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetMemberItemsRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    return input.fromUserClaims(claims ->
                            GetMemberItemsRequest.builder()
                                    .withMemberId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.providesGetMemberItemsActivity().handleRequest(request)
        );
    }
}
