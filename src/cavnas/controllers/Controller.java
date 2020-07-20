package cavnas.controllers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Base class for all controllers in this library.
 */
public class Controller
{
    protected static String run(Method method, URL endpoint, String token, String jsonParams) throws IOException
    {
        // Create new connection to the URL.
        HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
        conn.setRequestMethod(method.getName());
        conn.setRequestProperty("Content-Type", "application/JSON");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setDoOutput(true);

        // If there are parameters, send them to the endpoint
        if (jsonParams != null)
        {
            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"))
            {
                writer.write(jsonParams);
                writer.flush();
            }
        }

        StringBuilder response = new StringBuilder();

        // Read the response from the endpoint. If the response code isn't 200, it will throw an IOException
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")))
        {
            String s;

            while ((s = in.readLine()) != null)
                response.append(s);
        }

        // Return response
        return response.toString();
    }
}

/**
 * Contains all HTTP methods used in the Canvas API
 */
enum Method
{
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String name;

    Method(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}