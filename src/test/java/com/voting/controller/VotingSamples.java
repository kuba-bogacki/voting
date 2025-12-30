package com.voting.controller;

class VotingSamples {

    final String electionRequestMapping = "/election";
    final String voterRequestMapping = "/voter";
    final String createPostMapping = "/create";
    final String votePatchMapping = "/vote";
    final String updatePatchMapping = "/update";

    final String electionRequest = """
            {
                "electionName" : "President Election 2025",
                "electionOptions" : [
                    {
                        "optionName" : "Barrack Obama"
                    },
                    {
                        "optionName" : "Donald Trump"
                    },
                    {
                        "optionName" : "Joe Biden"
                    },
                    {
                        "optionName" : "Kamala Harris"
                    }
                ]
            }
            """;

    final String votingRequest = """
            {
                "voterEmail" : "adrien@wp.pl",
                "electionName" : "President Election 2025",
                "optionName" : "Kamala Harris"
            }
            """;

    final String voterRequest = """
            {
                "voterEmail" : "adrien@wp.pl",
                "voterFirstName" : "Adrien",
                "voterLastName" : "Brody"
            }
            """;

    final String voterStatusRequest = """
            {
                "voterEmail" : "adrien@wp.pl",
                "voterStatus" : "UNBLOCKED"
            }
            """;
}
