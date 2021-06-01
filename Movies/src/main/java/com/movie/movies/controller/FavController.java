package com.movie.movies.controller;

import com.movie.movies.models.info.Fav;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Controller
public class FavController {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @GetMapping("/fav/{movieName}/{poster}")
    public String name(@PathVariable("movieName") String movieName, @PathVariable("poster") String poster, HttpServletRequest request) {
        try {

            HttpSession session = request.getSession();
            String Email = session.getAttribute("Email").toString();

            String query = "INSERT INTO `fav`(`UserEmail`, `MovieName`,`Poster`) VALUES (?,?,?)";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/movies", "root", "");
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, Email);
            pst.setString(2, movieName);
            pst.setString(3, poster);

            pst.executeUpdate();

        } catch (Exception e) {


            System.out.println(e.getMessage() + "");
        }


        return "redirect://localhost:8080/favPage";
    }

    @GetMapping("/favPage")
    public String fav(RestTemplate restTemplate, Model model, HttpServletRequest request) throws Exception
    {

        ArrayList<Fav> favArrayList = new ArrayList<>();
        try
        {

            HttpSession session = request.getSession();
            String Email = session.getAttribute("Email").toString();

            String sql = "SELECT * FROM `fav` where UserEmail=?";
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, Email);

            rs= pst.executeQuery();

            while (rs.next())
            {
                Fav f= new Fav( rs.getString("MovieName"), rs.getString("Poster"));
                favArrayList.add(f);
            }

            model.addAttribute("Fav", favArrayList);
            return ("fav");
        }
        catch(Exception e)
        {
        }
        return ("fav");
    }
    @GetMapping("/delete/{MovieName}")
    public String del(RestTemplate restTemplate, Model model, @PathVariable("MovieName") String movieName, HttpServletRequest request) throws Exception
    {
        HttpSession session = request.getSession();
        String Email = session.getAttribute("Email").toString();


        try
        {
            String sql = "DELETE  FROM `fav` where UserEmail=? and MovieName=?";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/movies", "root", "");
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, Email);
            pst.setString(2, movieName);

            pst.executeUpdate();


            return ("redirect://localhost:8080/favPage");
        }
        catch(Exception e)
        {

            System.out.println(e.getMessage()+"");
        }
        return ("redirect://localhost:8080/favPage");
    }


}
