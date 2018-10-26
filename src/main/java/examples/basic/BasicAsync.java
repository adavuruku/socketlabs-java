package examples.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.core.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

import java.io.IOException;

public class BasicAsync implements Example {

    @Override
    public SendResponse RunExample() throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending A Test Message (Basic Send Async)");
        message.setHtmlBody("<html><body><h1>Sending A Test Message</h1><p>This is the Html Body of my message.</p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("david.schrenker@socketlabs.com");

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        client.sendAsync(message, new SendAsyncCallback() {

            @Override
            public void onError(Exception ex)  {
                // Handle Exception here
                ex.printStackTrace();
                return;
            }

            @Override
            public void onResponse(SendResponse response) throws IOException {
                // Handle SendResponse here

                ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

                System.out.println("Response body : ");
                System.out.println(mapper.writeValueAsString(response));
                System.out.println();
                System.out.println();
                System.out.println("Enter a number (or QUIT to exit):");

                return;

            }
        });

        System.out.println("Waiting for response...");
        return null;
    }
}