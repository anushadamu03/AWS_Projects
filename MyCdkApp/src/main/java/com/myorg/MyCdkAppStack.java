package com.myorg;

import software.amazon.awscdk.*;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.*;

// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class MyCdkAppStack extends Stack {
    public MyCdkAppStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public MyCdkAppStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // example resource
        // final Queue queue = Queue.Builder.create(this, "MyCdkAppQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();
	//
	Bucket bucket = Bucket.Builder.create(this, "MyFirstBucket")
            .versioned(true)
            .build();


	Function lambdaFunction = Function.Builder.create(this, "MyLambdaFunction")
            .runtime(software.amazon.awscdk.services.lambda.Runtime.NODEJS_18_X) // You can also use JAVA, PYTHON, etc.
            .handler("index.handler")
            .code(Code.fromInline("exports.handler = async function(event, context) { return 'Hello from Lambda'; };"))
            .build();

        // Define the API Gateway
        LambdaRestApi api = LambdaRestApi.Builder.create(this, "MyApiGateway")
            .handler(lambdaFunction)
            .build();
    }
}
