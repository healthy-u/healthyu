package com.example.dustin.cs495helloworld;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Tables extends AsyncTask<String, Void, String> {

    public static final String baseUrl = "http://73.58.10.151";
    public static java.net.URL url;

    public static void initialize() {
        try {
            url = new URL(baseUrl);
            System.out.println("url: " + url.toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    protected String doInBackground(String[] params) {
        // do above Server call here
        return "some message";
    }

    public static void setUrl(String fileName, String params) {
        try {
            url = new URL(baseUrl + "/" + fileName + ".php" + params);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    public static JSONArray hitDB(String filename, String method, String params) {
        final JSONArray[] result = new JSONArray[1];
        result[0] = null;
        try

        {
            setUrl(filename, params);
            System.out.println(filename + ".php" + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.connect();
            result[0] = convertStreamToJson(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result[0];
    }

    private Tables() {}

    public static class UserTable {
        public static final String TABLE_NAME = "user";

        public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE user"
                + "user_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "user_firstname VARCHAR(20) NOT NULL,"
                + "user_lastname VARCHAR(20) NOT NULL,"
                + "user_username VARCHAR(20) NOT NULL,"
                + "user_email VARCHAR(50) NOT NULL,"
                + "user_password VARCHAR(70) NOT NULL,"
                + "lifetime_points DECIMAL(8, 3) DEFAULT 0 NOT NULL,"
                + "team_id MEDIUMINT DEFAULT NULL,"
                + "FOREIGN KEY(team_id) REFERENCES team(team_id))";

        public static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String[] columns = {"user_id", "user_firstname", "user_lastname", "user_username", "user_email", "lifetime_points", "team_id"};


        public static User findForUsernameAndPassword(String username, String pass) {

            final User[] result = new User[1];
            result[0] = null;
            final String finalUsername = username;
            final String finalPass = pass;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?username=" + finalUsername + "&password=" + finalPass;
                        result[0] = fromJson(hitDB("login", "GET", paramString));
                        System.out.println(result[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();

            try {
                thread.join();
            } catch(Exception e) {
                e.printStackTrace();
            }

            System.out.println(result[0]);
            return result[0];
        }

        public static User findForID(Long user_id) {
            final User[] result = new User[1];
            result[0] = null;
            final String id = String.valueOf(user_id);

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?user_id=" + id;
                        result[0] = fromJson(hitDB("users", "GET", paramString));
                        System.out.println(result[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();

            try {
                thread.join();
            } catch(Exception e) {
                e.printStackTrace();
            }

            System.out.println("USER FIND FOR ID: " + result[0]);
            return result[0];
        }

        static public User fromJson(JSONArray jsonArray) {
            try {
                JSONObject json = jsonArray.getJSONObject(0);
                Long team_id = 0L;
                if (json.getString(("team_id")) != "null") team_id = Long.parseLong(json.getString(("team_id")));
                return new User(
                        Long.parseLong(json.getString("user_id")),
                        json.getString("user_firstname"),
                        json.getString("user_lastname"),
                        json.getString("user_username"),
                        json.getString("user_email"),
                        new BigDecimal(json.getString("lifetime_points")),
                        team_id
                );
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class RunTable {

        public static final String FILE_NAME = "runs";

        public static final String TABLE_NAME = "run";

        public static final String[] columns = {"run_id", "user_id", "mile_count", "trail_coords", "start_time", "end_time"};

        public static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE run ("
                + "run_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "user_id INTEGER NOT NULL,"
                + "mile_count DECIMAL(2, 3) NOT NULL,"
                + "trail_coords VARCHAR(1000) DEFAULT NULL,"
                + "start_time DATETIME NOT NULL,"
                + "end_time DATETIME NOT NULL)";



        public static List<Run> findForUser(User u) {
            final List<Run> runs = new ArrayList<Run>();
            final String id = String.valueOf(u.id);

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?user_id=" + id;
                        runs.addAll(fromJson(hitDB("runs", "GET", paramString)));
                        System.out.println(runs.get(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();

            try {
                thread.join();
            } catch(Exception e) {
                e.printStackTrace();
            }

            System.out.println(runs);
            return runs;
        }

        public static List<Run> fromJson(JSONArray jsons) {
            List<Run> runs = new ArrayList<Run>();
            try {
                for (int i = 0; i < jsons.length(); i++) {
                    JSONObject json = jsons.getJSONObject(i);
                    runs.add(new Run(
                            Long.parseLong(json.getString("run_id")),
                            Long.parseLong(json.getString("user_id")),
                            new BigDecimal(json.getString("mile_count")),
                            json.getString("trail_coords"),
                            Database.dateFormat.parse(json.getString("start_time")),
                            Database.dateFormat.parse(json.getString("end_time"))
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return runs;
        }
    }

    private static JSONArray convertStreamToJson(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        JSONArray result = null;

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("sb.toString: " + sb.toString());
            result = new JSONArray(sb.toString());
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }
}