package com.portilho.lol.api.portilhololapi.service.connection;

import com.portilho.lol.api.portilhololapi.constant.Constant;
import com.portilho.lol.api.portilhololapi.exception.GetRequestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class LolConnectionService implements ConnectionService
{
    @Override
    public String sendGetRequest(String url)
    {
        try
        {
            StringBuilder response = sendRequest(url);
            return response.toString();
        } catch (IOException e)
        {
            throw new GetRequestException("Not able to send get request.");
        }
    }

    private StringBuilder sendRequest(String url) throws IOException
    {
        HttpURLConnection con = getHttpURLConnection(url);
        BufferedReader in = getBufferedReader(con);
        StringBuilder response = getResponse(in);
        closeConnection(in);
        return response;
    }

    private HttpURLConnection getHttpURLConnection(String url) throws IOException
    {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(Constant.Request.GET);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("X-Riot-Token", Constant.Request.RIOT_TOKEN);
        return con;
    }

    private StringBuilder getResponse(BufferedReader in) throws IOException
    {
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        return response;
    }

    private BufferedReader getBufferedReader(HttpURLConnection con) throws IOException
    {
        return new BufferedReader(
                new InputStreamReader(con.getInputStream()));
    }

    private void closeConnection(BufferedReader in) throws IOException
    {
        in.close();
    }
}
