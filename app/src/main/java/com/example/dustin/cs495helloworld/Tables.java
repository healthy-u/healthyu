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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Tables extends AsyncTask<String, Void, String> {

    public static final String baseUrl = "http://73.58.10.151";
    public static java.net.URL url;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static int stepsInAMile = 2155;

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

    public static JSONArray convertStreamToJson(InputStream is) {

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
            result = new JSONArray();
            try {
                result.put(new JSONObject(sb.toString()));
            }
            catch (Exception ex) {
                e.printStackTrace();
                result = null;
            }
        }
        return result;
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

        public static User create(User u, final String password) {
            final Long[] result = new Long[1];
            result[0] = null;
            final User passedInUser = u;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?username=" + passedInUser.username + "&password=" + password + "&user_firstname=" + passedInUser.firstname +
                                "&user_lastname=" + passedInUser.lastname + "&user_email=" + passedInUser.email;
                        result[0] = (Long.parseLong(hitDB("login", "POST", paramString).getJSONObject(0).getString("id")));
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
            u.id = result[0];
            return u;
        }

        public static Boolean update(User u) {
            final Boolean[] result = new Boolean[1];
            result[0] = null;
            final User passedInUser = u;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?user_id=" + passedInUser.id + "&lifetime_points=" + passedInUser.lifetime_points + "&team_id=" + passedInUser.team_id;
                        result[0] = ((Long.parseLong(hitDB("users", "PATCH", paramString).getJSONObject(0).getString("success"))) > 0);
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

        public static User findForUsernameAndPassword(String username, String pass) {

            final User[] result = new User[1];
            result[0] = null;
            final String finalUsername = username;
            final String finalPass = pass;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?username=" + finalUsername + "&password=" + finalPass;
                        result[0] = fromJson(hitDB("login", "GET", paramString).getJSONObject(0));
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
                        result[0] = fromJson(hitDB("users", "GET", paramString).getJSONObject(0));
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

        public static Boolean existsForUsername(String uname) {
            final Boolean[] result = new Boolean[1];
            result[0] = true;
            final String username = uname;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?username=" + username;
                        result[0] = (Long.parseLong(hitDB("users", "GET", paramString).getJSONObject(0).getString("result")) > 0);
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

            return result[0];
        }

        static public User fromJson(JSONObject json) {
            try {
                System.out.println(json.toString());
                Long team_id = 0L;
                if (json.getString(("team_id")) != "null") team_id = Long.parseLong(json.getString(("team_id")));
                System.out.println("first name: " + json.getString("user_firstname"));

                User u = new User(
                        Long.parseLong(json.getString("user_id")),
                        json.getString("user_firstname"),
                        json.getString("user_lastname"),
                        json.getString("user_username"),
                        json.getString("user_email"),
                        new BigDecimal(json.getString("lifetime_points")),
                        team_id
                );

                u.runs = RunTable.findForUser(u);

                return u;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        static public List<User> allUsersFromJson(JSONArray jsonArray) {
            List<User> users = new ArrayList<User>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    User u = fromJson(jsonArray.getJSONObject(i));
                    System.out.println(u.fullname());
                    System.out.println(u.id);
                    users.add(u);
                    System.out.println("users: " + users.size());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return users;
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

        public static Run create(Run r) {
            final Long[] result = new Long[1];
            result[0] = null;
            final Run passedInRun = r;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?user_id=" + passedInRun.user_id + "&mile_count=" + passedInRun.miles + "&trail_coords=" + passedInRun.trailCoords
                                + "&start_time=" + passedInRun.startTime + "&end_time=" + passedInRun.endTime;
                        result[0] = (Long.parseLong(hitDB(FILE_NAME, "POST", paramString).getJSONObject(0).getString("new_id")));
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
            r.id = result[0];
            return r;
        }

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

    public static class ChallengeTable {

        public static final String FILE_NAME = "event";

        public static final String FILE_NAME_USER = "user/event";

        public static final String TABLE_NAME = "event";

        public static Challenge create(Challenge c) {
            final Long[] result = new Long[1];
            result[0] = null;
            final Challenge passedInChallenge = c;

            final Prize p = PrizeTable.create(c.prize);
            passedInChallenge.prize = p;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?sponsor_id=" + passedInChallenge.sponsor_id + "&prize_id=" + passedInChallenge.prize.id + "&start_date=" + dateFormat.format(passedInChallenge.start_date)
                                + "&end_date=" + dateFormat.format(passedInChallenge.end_date) + "&event_name=" + passedInChallenge.name;
                        result[0] = (Long.parseLong(hitDB(FILE_NAME, "POST", paramString).getJSONObject(0).getString("id")));
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
            c.id = result[0];
            return c;
        }

        public static Challenge update(Challenge c) {
            final Challenge passedInChallenge = c;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?sponsor_id=" + passedInChallenge.sponsor_id + "&prize_id=" + passedInChallenge.prize.id + "&start_date=" +dateFormat.format(passedInChallenge.start_date)
                                + "&end_date=" + dateFormat.format(passedInChallenge.end_date) + "&event_name=" + passedInChallenge.name + "&event_id=" + passedInChallenge.id;
                        hitDB(FILE_NAME, "PUT", paramString);
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

            return c;
        }

        public static List<Challenge> findForUser(User u) {
            final List<Challenge> challenges = new ArrayList<Challenge>();
            final String id = String.valueOf(u.id);

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?user_id=" + id;
                        challenges.addAll(fromJson(hitDB(FILE_NAME_USER, "GET", paramString)));
                        System.out.println(challenges.get(0));
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

            System.out.println("CHALLENGES: " + challenges);
            return challenges;
        }

        public static Boolean delete(Challenge c) {
            final Challenge chal = c;
            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?event_id=" + chal.id;
                        hitDB(FILE_NAME, "DELETE", paramString);
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
            return true;
        }

        public static List<Challenge> findForSponsor(Sponsor s) {
            final List<Challenge> challenges = new ArrayList<Challenge>();
            final String id = String.valueOf(s.id);

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?sponsor_id=" + id;
                        challenges.addAll(fromJson(hitDB(FILE_NAME, "GET", paramString)));
                        System.out.println(challenges.get(0));
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

            System.out.println("CHALLENGES: " + challenges);
            return challenges;
        }

        public static List<Challenge> fromJson(JSONArray jsons) {
            List<Challenge> challenges = new ArrayList<Challenge>();
            try {
                for (int i = 0; i < jsons.length(); i++) {
                    JSONObject json = jsons.getJSONObject(i);
                    challenges.add(new Challenge(
                            Long.parseLong(json.getString("event_id")),
                            Long.parseLong(json.getString("sponsor_id")),
                            PrizeTable.fromJson(json),
                            Database.dateFormat.parse(json.getString("start_date")),
                            Database.dateFormat.parse(json.getString("end_date")),
                            json.getString("event_name"),
                            json.getString("event_type")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return challenges;
        }
    }

    public static class SponsorTable {
        public static final String LOGIN_FILENAME = "sponsor-login";
        public static final String FILENAME = "sponsors";

        public static Sponsor create(Sponsor s, final String password) {
            final Long[] result = new Long[1];
            result[0] = null;
            final Sponsor passedInSponsor = s;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?sponsor_name=" + passedInSponsor.username + "&sponsor_password=" + password + "&sponsor_email=" + passedInSponsor.email;
                        result[0] = (Long.parseLong(hitDB(FILENAME, "POST", paramString).getJSONObject(0).getString("new_id")));
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
            s.id = result[0];
            return s;
        }

        public static Sponsor findForUsernameAndPassword(String username, String pass) {

            final Sponsor[] result = new Sponsor[1];
            result[0] = null;
            final String finalUsername = username;
            final String finalPass = pass;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?username=" + finalUsername + "&password=" + finalPass;
                        result[0] = fromJson(hitDB(LOGIN_FILENAME, "GET", paramString));
                        System.out.println("SPONSOR: " + result[0]);
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

        public static Sponsor findForID(Long user_id) {
            final Sponsor[] result = new Sponsor[1];
            result[0] = null;
            final String id = String.valueOf(user_id);

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?sponsor_id=" + id;
                        result[0] = fromJson(hitDB(FILENAME, "GET", paramString));
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

            System.out.println("SPONSOR FIND FOR ID: " + result[0]);
            return result[0];
        }

        static public Sponsor fromJson(JSONArray jsonArray) {
            try {
                JSONObject json = jsonArray.getJSONObject(0);

                return new Sponsor(
                        Long.parseLong(json.getString("sponsor_id")),
                        json.getString("sponsor_name"),
                        json.getString("sponsor_email"),
                        Long.parseLong(json.getString(("campus_id")))
                );
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class TeamTable {
        public static final String FILENAME = "teams";
        public static final String ALLTEAMSFILENAME = "allteams";

        public static List<Team> findAllTeamsForUserCampus(Long user_id) {
            final List<Team> result = new ArrayList<Team>();
            final String id = String.valueOf(user_id);

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?user_id=" + id;
                        result.addAll(fromJson(hitDB(FILENAME, "GET", paramString)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();

            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        public static List<Team> allTeamsForUserCampus(Long user_id) {
            final List<Team> result = new ArrayList<Team>();
            final String id = String.valueOf(user_id);

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?user_id=" + id;
                        result.addAll(fromJson(hitDB(ALLTEAMSFILENAME, "GET", paramString)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();

            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        public static Team findForUser(Long user_id) {
            List<Team> allTeams = findAllTeamsForUserCampus(user_id);

            System.out.println("allTeams: " + allTeams);

            for (int i = 0; i < allTeams.size(); i++) {
                Boolean hasUser = false;
                List<User> users = allTeams.get(i).members;
                for (int j = 0; j < users.size(); j++) {
                    if (users.get(j).id == user_id) return allTeams.get(i);
                }
            }

            return null;
        }

        static public List<Team> fromJson(JSONArray jsonArray) {

            List<Team> teams = new ArrayList<Team>();

            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i).getJSONObject("team");

                    String userString = json.getString("users");
                    List<User> users = new ArrayList<>();

                    if (!userString.equals("[]")) {
                        users = UserTable.allUsersFromJson(json.getJSONArray("users"));
                    }

                    String team_id_string = json.getString("id");
                    Long team_id = 0L;
                    if (team_id_string != "null") {
                        System.out.println("team json: " + json + "\n");
                        team_id = Long.parseLong(team_id_string);

                        teams.add(new Team(
                                team_id,
                                json.getString("name"),
                                users
                        ));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return teams;
        }
    }

    public static class PrizeTable {
        public static final String FILENAME = "prize";

        public static Prize create(Prize p) {
            final Long[] result = new Long[1];
            result[0] = null;
            final Prize passedInPrize = p;

            Thread thread = new Thread() {
                public void run() {
                    try {
                        String paramString = "?prize_link=" + passedInPrize.prize_link + "&point_value=" + passedInPrize.points_awarded;
                        result[0] = (Long.parseLong(hitDB(FILENAME, "POST", paramString).getJSONObject(0).getString("id")));
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
            p.id = result[0];
            return p;
        }

        static public List<Prize> fromJsonArray(JSONArray jsonArray) {

            List<Prize> prizes = new ArrayList<>();

            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i).getJSONObject("team");

                    prizes.add(fromJson(json));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return prizes;
        }

        static public Prize fromJson(JSONObject json) {
            try {
                String winner_id_string = json.getString("winner_id");
                Long winner_id = null;
                if (winner_id_string != null) Long.parseLong(winner_id_string);
                return new Prize(Long.parseLong(json.getString("prize_id")),
                        winner_id,
                        json.getString("prize_link"),
                        Integer.parseInt(json.getString("point_value")),
                        (Long.parseLong(json.getString("winner_id")) > 0));
            }
            catch (Exception e) {
                return null;
            }
        }
    }
}
