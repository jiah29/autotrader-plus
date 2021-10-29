package com.example.informationmanipulation;

import com.example.connectouterentity.ConnectAutoTraderDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class dedicated to returning multiple cars when accessing the DB
 */
public class ReturnMultipleCars {

    /**
     * A helper method that iterates through the returned result set of a query.
     * It will return every car in dictionary form and place it into an arraylist
     */
    private static ArrayList<HashMap<String, String>> returnAllCarsQuery(String query) throws SQLException{


        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        ArrayList<HashMap<String, String>> returnList = new ArrayList<>();

        while (myResultSet.next()){

            // Make a map for a car
            HashMap<String, String> carMap = new HashMap<>();

            // Populate that car's information
            carMap = ReturnCarInformation.populateCarMap(myResultSet);

            // Add it to the list
            returnList.add(carMap);

        }
        return returnList;
    }

    /**
     * Return all cars available in the database
     */
    public ArrayList<HashMap<String, String>> returnAllCars() throws SQLException {

        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars;";
        return returnAllCarsQuery(query);
    }

    /**
     * Return all cars that are of a specific car type
     * @param filter the type of car you want to filter out
     */
    public ArrayList<HashMap<String, String>> returnFilteredCars(String filter) throws SQLException {
        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE car_type = \"" + filter + "\";";
        System.out.println(query);

        return returnAllCarsQuery(query);
    }

}
