package com.mrstricty;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;

public class LambdaHandler {
    MySQLHandler mySQL;
    SMSHandler sms;
    LambdaLogger logger;

    public void eventHandler(ScheduledEvent event, Context context) {
        logger = context.getLogger();
        logger.log("Scheduled Event Received");

        logger.log("Initating DB Handler");
        mySQL = new MySQLHandler();

        logger.log("Initiating AWS SNS Handler");
        sms = new SMSHandler();

        logger.log("Obtaining Quote From DB");
        String textQuote = mySQL.randomQuoteQuery();
        logger.log("Message obtained: '" + textQuote + "'");

        logger.log("Sending Message");
        sms.sendSMS(textQuote);

        logger.log("eventHandler finished");
    }
}
