package com.diegopizzo.league.data

val leaguesByNameResponse = """
{
    "get": "leagues",
    "parameters": {
        "name": "Serie A",
        "type": "league",
        "code": "IT"
    },
    "errors": [],
    "results": 1,
    "paging": {
        "current": 1,
        "total": 1
    },
    "response": [
        {
            "league": {
                "id": 135,
                "name": "Serie A",
                "type": "League",
                "logo": "https://media.api-sports.io/football/leagues/135.png"
            },
            "country": {
                "name": "Italy",
                "code": "IT",
                "flag": "https://media.api-sports.io/flags/it.svg"
            },
            "seasons": [
                {
                    "year": 2010,
                    "start": "2010-08-28",
                    "end": "2011-05-22",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": false,
                            "statistics_players": false
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2011,
                    "start": "2011-09-09",
                    "end": "2012-05-13",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": false,
                            "statistics_players": false
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2012,
                    "start": "2012-08-25",
                    "end": "2013-05-19",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": false,
                            "statistics_players": false
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2013,
                    "start": "2013-08-24",
                    "end": "2014-05-18",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": false,
                            "statistics_players": false
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2014,
                    "start": "2014-08-30",
                    "end": "2015-05-31",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": false,
                            "statistics_players": false
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2015,
                    "start": "2015-08-22",
                    "end": "2016-05-15",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2016,
                    "start": "2016-08-20",
                    "end": "2017-05-28",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2017,
                    "start": "2017-08-19",
                    "end": "2018-05-20",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2018,
                    "start": "2018-08-18",
                    "end": "2019-05-26",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2019,
                    "start": "2019-08-24",
                    "end": "2020-08-02",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2020,
                    "start": "2020-09-19",
                    "end": "2021-05-23",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": true,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2021,
                    "start": "2021-08-21",
                    "end": "2022-05-22",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": true,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2022,
                    "start": "2022-08-13",
                    "end": "2023-06-11",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": true,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2023,
                    "start": "2023-08-19",
                    "end": "2024-05-26",
                    "current": false,
                    "coverage": {
                        "fixtures": {
                            "events": true,
                            "lineups": true,
                            "statistics_fixtures": true,
                            "statistics_players": true
                        },
                        "standings": true,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": true,
                        "predictions": true,
                        "odds": false
                    }
                },
                {
                    "year": 2024,
                    "start": "2024-08-18",
                    "end": "2025-05-25",
                    "current": true,
                    "coverage": {
                        "fixtures": {
                            "events": false,
                            "lineups": false,
                            "statistics_fixtures": false,
                            "statistics_players": false
                        },
                        "standings": false,
                        "players": true,
                        "top_scorers": true,
                        "top_assists": true,
                        "top_cards": true,
                        "injuries": false,
                        "predictions": true,
                        "odds": false
                    }
                }
            ]
        }
    ]
}
""".trimIndent()
