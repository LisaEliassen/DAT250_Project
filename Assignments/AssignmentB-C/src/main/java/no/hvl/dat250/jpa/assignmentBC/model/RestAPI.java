package no.hvl.dat250.jpa.assignmentBC.model;

import com.google.gson.Gson;
import no.hvl.dat250.jpa.assignmentBC.daos.FeedAppUserDAO;
import no.hvl.dat250.jpa.assignmentBC.daos.PollDAO;
import no.hvl.dat250.jpa.assignmentBC.daos.VoteDAO;

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
        final FeedAppUserDAO userDAO = new FeedAppUserDAO();
        final VoteDAO voteDAO = new VoteDAO();


        Gson gson = new Gson();


        // ---------------------
        //        POLLS
        // ---------------------

        // Create/POST:
        post("/polls", (request, response) -> {
            response.type("application/json");

            Poll poll = gson.fromJson(request.body(), Poll.class);
            poll = pollDAO.create(poll);
            System.out.println(gson.toJson(poll));
            FeedAppUser user = userDAO.getUserByID(poll.getUser());
            user.addPoll(poll.getID());
            userDAO.update(user, user.getID());

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
                return String.format("Poll with the id \"%s\" not found!", id);
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
                Poll editedPoll = gson.fromJson(request.body(), Poll.class);
                editedPoll = pollDAO.update(editedPoll, Long.valueOf(id));

                if (editedPoll != null) {
                    return gson.toJson(editedPoll);
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
                    return String.format("Poll with the id \"%s\" not found!", id);
                }
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });


        // ---------------------
        //        RESULT
        // ---------------------


        // Read/GET, result for poll with Poll-id:
        get("/result/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (isNumber(id)) { // check if id is a number
                Poll poll = pollDAO.getPollByID(Long.valueOf(id));
                if (poll != null) {
                    return gson.toJson(poll.getResult());
                }
                return String.format("Poll with the id \"%s\" not found!", id);
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
            vote = voteDAO.create(vote);
            pollDAO.addVote(vote.getID(), vote.getPoll());

            return gson.toJson(vote);
        });

        // Read/GET, one vote by Vote-id:
        // Todo: Check if user associated with vote id is the logged in user.
        get("/vote/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (isNumber(id)) { // check if id is a number
                Vote vote = voteDAO.getVoteByID(Long.valueOf(id));
                if (vote != null) {
                    return gson.toJson(vote);
                }
                return String.format("Vote with the id \"%s\" not found!", id);
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
                Vote editedVote = gson.fromJson(request.body(), Vote.class);
                editedVote = voteDAO.update(editedVote, Long.valueOf(id));

                if (editedVote != null) {
                    return gson.toJson(editedVote);
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
                Vote vote = voteDAO.delete(Long.valueOf(id));
                if (vote != null) {
                    return gson.toJson("Success!");
                }
                else {
                    return String.format("Vote with the id \"%s\" not found!", id);
                }
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }

        });

        // ---------------------
        //        USERS
        // ---------------------

        // POST Create user / register user
        post("/user", (request, response) -> {
            response.type("application/json");

            FeedAppUser user = gson.fromJson(request.body(), FeedAppUser.class);
            user =userDAO.create(user);

            return gson.toJson(user);
        });

        // GET all users
        get("/users", (request, response) -> {
            response.type("application/json");

            return gson.toJson(userDAO.getAllUsers());
        });


        // GET user by id
        get("/user/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (isNumber(id)) { // check if id is a number
                FeedAppUser user = userDAO.getUserByID(Long.valueOf(id));
                if (user != null) {
                    return gson.toJson(user);
                }
                return String.format("User with the id \"%s\" not found!", id);
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });

        // PUT / update user
        put("/user/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            // check if id is a number
            if (isNumber(id)) {
                FeedAppUser editedUser = gson.fromJson(request.body(), FeedAppUser.class);
                editedUser = userDAO.update(editedUser, Long.valueOf(id));

                if (editedUser != null) {
                    return gson.toJson(editedUser);
                }
                else {
                    return gson.toJson("User not found or error in edit");
                }
            }
            else {
                return String.format("The id \"%s\" is not a number!", id);
            }
        });

        // DELETE user
        delete("/user/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");
            if(isNumber(id)) {
                FeedAppUser user = userDAO.delete(Long.valueOf(id));
                if (user != null) {
                    return gson.toJson("Success!");
                }
                else {
                    return String.format("User with the id \"%s\" not found!", id);
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

