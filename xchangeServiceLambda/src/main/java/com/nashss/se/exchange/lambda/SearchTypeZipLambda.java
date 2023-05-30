//package com.nashss.se.exchange.lambda;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.nashss.se.exchange.activity.requests.SearchTypeZipRequest;
//import com.nashss.se.exchange.activity.results.SearchTypeZipResult;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class SearchTypeZipLambda
//        extends LambdaActivityRunner<SearchTypeZipRequest, SearchTypeZipResult>
//        implements RequestHandler<LambdaRequest<SearchTypeZipRequest>, LambdaResponse> {
//
//    private final Logger log = LogManager.getLogger();
//
////    @Override
////    public LambdaResponse handleRequest(LambdaRequest<SearchTypeZipRequest> input, Context context) {
////        log.info("handleRequest");
////        return super.runActivity(
////                () -> input.fromPath(path ->
////                        SearchTypeZipRequest.builder()
////                                .withType(path.get("type"))
////                                .withZipCode(path.get("zipCode"))
////                                .withCriteria(path.get("criteria"))
////                                .build()),
////                (request, serviceComponent) ->
////                        serviceComponent.providesSearchTypeZipActivity().handleRequest(request)
////        );
////    }
//
//    @Override
//    public LambdaResponse handleRequest(LambdaRequest<SearchTypeZipRequest> input, Context context) {
//        System.out.println("SearchTypeZipRequest.hanldeRequest() with input: " + input + " " + context);
//        log.info("SearchTypeZipLambda: received SearchTypeZipRequest");
//        return super.runActivity(
//                () -> input.fromPathAndQuery((path, query) ->
//                        SearchTypeZipRequest.builder()
//                                .withZipCode(path.get("zipCode"))
//                                .withType(path.get("type"))
//                                .withCriteria(query.get("criteria"))
//                                .build()),
//                (request, serviceComponent) ->
//                        serviceComponent.providesSearchTypeZipActivity().handleRequest(request)
//        );
//    }
//}
