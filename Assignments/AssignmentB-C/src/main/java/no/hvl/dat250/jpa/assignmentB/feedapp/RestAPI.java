package no.hvl.dat250.jpa.assignmentB.feedapp;

import com.google.gson.Gson;
import no.hvl.dat250.jpa.assignmentB.daos.PollDAO;

import static spark.Spark.*;

public class RestAPI {

    public static void main(String[] args) {
        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        after((req, res) -> res.type("application/json"));

        final PollDAO pollDAO = new PollDAO();
        Gson gson = new Gson();

        // ---------------------
        //        POLLS
        // ---------------------

        // Create/POST:
        post("/polls", (request, response) -> {
            response.type("application/json");

            Poll poll = gson.fromJson(request.body(), Poll.class);
            poll = pollDAO.create(poll);

            return gson.toJson(poll);
        });

        // Read/GET, one poll by id:
        get("/polls/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (isNumber(id)) { // check if id is a number
                Poll poll = pollDAO.getPollByID(Long.valueOf(id));
                if (poll != null) {
                    return gson.toJson(poll);
                }
                return String.format("Poll with the id  \"%s\" not found!", id);
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });

        // Read/GET, all polls:
        get("/polls", (request, response) -> {
            response.type("application/json");

            return gson.toJson(pollDAO.getAllPolls());
        });

        // Update/PUT:
        put("/polls/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            // check if id is a number
            if (isNumber(id)) {
                Poll editedTodo = gson.fromJson(request.body(), Poll.class);
                editedTodo = pollDAO.update(editedTodo, Long.valueOf(id));

                if (editedTodo != null) {
                    return gson.toJson(editedTodo);
                }
                else {
                    return gson.toJson("Poll not found or error in edit");
                }
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });

        // Delete/DELETE:
        delete("/polls/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");
            if(isNumber(id)) {
                Poll poll = pollDAO.delete(Long.valueOf(id));
                if (poll != null) {
                    return gson.toJson("Success!");
                }
                else {
                    return String.format("Poll with the id  \"%s\" not found!", id);
                }
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });

        // ---------------------
        //        VOTES
        // ---------------------

        // Create/POST for vote:
        post("/vote", (request, response) -> {
            response.type("application/json");

            Vote vote = gson.fromJson(request.body(), Vote.class);
            pollDAO.addVote(vote);

            return gson.toJson(vote);
        });

        // Read/GET, result for poll with Poll-id:
        get("/result/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (isNumber(id)) { // check if id is a number
                Poll poll = pollDAO.getPollByID(Long.valueOf(id));
                if (poll != null) {
                    return gson.toJson(poll.getResult());
                }
                return String.format("Poll with the id  \"%s\" not found!", id);
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });

        // Read/GET, one vote by Vote-id:
        // Todo: Check if user associated with vote id is the logged in user.
        get("/vote/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (isNumber(id)) { // check if id is a number
                Poll poll = pollDAO.getPollByID(Long.valueOf(id));
                if (poll != null) {
                    return gson.toJson(poll);
                }
                return String.format("Vote with the id  \"%s\" not found!", id);
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });

        // Update/PUT by Vote-id:
        put("/vote/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            // check if id is a number
            if (isNumber(id)) {
                Poll editedTodo = gson.fromJson(request.body(), Poll.class);
                editedTodo = pollDAO.update(editedTodo, Long.valueOf(id));

                if (editedTodo != null) {
                    return gson.toJson(editedTodo);
                }
                else {
                    return gson.toJson("Vote not found or error in edit");
                }
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });

        // Delete/DELETE for vote by Vote-id:
        delete("/votes/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");
            if(isNumber(id)) {
                Poll poll = pollDAO.delete(Long.valueOf(id));
                if (poll != null) {
                    return gson.toJson("Success!");
                }
                else {
                    return String.format("Vote with the id  \"%s\" not found!", id);
                }
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });
    }

    public static boolean isNumber(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

