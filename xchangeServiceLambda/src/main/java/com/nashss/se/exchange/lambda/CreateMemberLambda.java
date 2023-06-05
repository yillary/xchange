package com.nashss.se.exchange.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.exchange.activity.requests.CreateMemberRequest;
import com.nashss.se.exchange.activity.results.CreateMemberResult;

public class CreateMemberLambda
        extends LambdaActivityRunner<CreateMemberRequest, CreateMemberResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateMemberRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateMemberRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateMemberRequest arg = input.fromBody(CreateMemberRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateMemberRequest.builder()
                                    .withZipCode(arg.getZipCode())
                                    .withMemberId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.providesCreateMemberActivity().handleRequest(request)
        );
    }
}
