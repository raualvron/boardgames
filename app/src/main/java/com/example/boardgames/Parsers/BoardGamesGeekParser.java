package com.example.boardgames.Parsers;

import android.text.TextUtils;

import com.example.boardgames.Handlers.GameHandler;
import com.example.boardgames.Handlers.HotnessHandler;
import com.example.boardgames.Handlers.ProfileHandler;
import com.example.boardgames.Handlers.Top100Handler;
import com.example.boardgames.Model.Game;
import com.example.boardgames.Model.Top100;
import com.example.boardgames.Model.User;
import com.example.boardgames.Model.Hotness;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import java.net.MalformedURLException;
import javax.xml.parsers.SAXParserFactory;

public class BoardGamesGeekParser {
    private URL url;

    public BoardGamesGeekParser(String url, String user) {
        try {
            if (TextUtils.isEmpty(user)) {
                this.url = new URL(url);
            } else {
                this.url = new URL(url + user);
            }

        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<User> parseUsers() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            ProfileHandler handler = new ProfileHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getUsers();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    public List<Hotness> parseHotnessGames() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            HotnessHandler handler = new HotnessHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getGames();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    public Game parseGame() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            GameHandler handler = new GameHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getGame();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    public List<Top100> parseTop100() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            Top100Handler handler = new Top100Handler();
            parser.parse(this.getInputStream(), handler);
            return handler.getTheards();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    private InputStream getInputStream() {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
