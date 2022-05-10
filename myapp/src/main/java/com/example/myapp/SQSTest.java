package com.example.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class SQSTest {
    public static void main( String[] args ) {
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        String queueUrl = sqs.getQueueUrl("wjwtest").getQueueUrl();
        System.out.println(queueUrl);
        
        // Send SQS with messageAttribute count
        Map<String, MessageAttributeValue> attributes = new HashMap<>();
        MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
        messageAttributeValue.setDataType("Number");
        messageAttributeValue.setStringValue("5");
        attributes.put("count", messageAttributeValue);

        
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody("hello world")
                .withMessageAttributes(attributes)
                .withDelaySeconds(5);

        sqs.sendMessage(send_msg_request);

        List<String> attributeNames = new ArrayList<String>();
        attributeNames.add("All");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withAttributeNames(attributeNames).withQueueUrl(queueUrl);
        List<String> list = new ArrayList<>();
        list.add("All");
        receiveMessageRequest.setMessageAttributeNames(list);
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        System.out.println(messages.size());
        for (Message message : messages) {
            System.out.println(message.getBody());
            Map<String, String> attri = message.getAttributes();
            int count = Integer.valueOf(attri.get("ApproximateReceiveCount"));
            System.out.println("ApproximateReceiveCount: " + count);
            // for (Map.Entry<String, String> entry : attri.entrySet()) {
            //     System.out.println(entry.getKey() + " " + entry.getValue());
            // }

            Map<String, MessageAttributeValue> tmp= message.getMessageAttributes();
            System.out.println(tmp.size());
            for (Map.Entry<String, MessageAttributeValue> entry : tmp.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
