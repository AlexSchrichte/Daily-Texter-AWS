package com.mrstricty;


import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SMSHandler
{
    static String arn = "################################";
    PublishRequest publishRequest;
    AmazonSNS snsClient;

    public SMSHandler()
    {
        snsClient = AmazonSNSClientBuilder
                .standard()
                .build();
    }

    public String sendSMS(String input)
    {
        publishRequest = new PublishRequest(arn, input);
        PublishResult publishResult = snsClient.publish(publishRequest);
        return publishResult.getMessageId();
    }
}
