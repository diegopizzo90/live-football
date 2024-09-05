package com.diegopizzo.core.config

val leagues = """
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
                        "start": "2024-08-17",
                        "end": "2025-05-25",
                        "current": true,
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
                            "odds": true
                        }
                    }
                ]
            }
        ]
    }
""".trimIndent()

val fixtures = """
    {
        "get": "fixtures",
        "parameters": {
            "from": "2024-08-24",
            "league": "135",
            "to": "2024-08-24",
            "season": "2024"
        },
        "errors": [],
        "results": 4,
        "paging": {
            "current": 1,
            "total": 1
        },
        "response": [
            {
                "fixture": {
                    "id": 1223608,
                    "referee": "D. Di Marco",
                    "timezone": "UTC",
                    "date": "2024-08-24T18:45:00+00:00",
                    "timestamp": 1724525100,
                    "periods": {
                        "first": 1724525100,
                        "second": 1724528700
                    },
                    "venue": {
                        "id": 907,
                        "name": "Stadio Giuseppe Meazza",
                        "city": "Milano"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 135,
                    "name": "Serie A",
                    "country": "Italy",
                    "logo": "https://media.api-sports.io/football/leagues/135.png",
                    "flag": "https://media.api-sports.io/flags/it.svg",
                    "season": 2024,
                    "round": "Regular Season - 2"
                },
                "teams": {
                    "home": {
                        "id": 505,
                        "name": "Inter",
                        "logo": "https://media.api-sports.io/football/teams/505.png",
                        "winner": true
                    },
                    "away": {
                        "id": 867,
                        "name": "Lecce",
                        "logo": "https://media.api-sports.io/football/teams/867.png",
                        "winner": false
                    }
                },
                "goals": {
                    "home": 2,
                    "away": 0
                },
                "score": {
                    "halftime": {
                        "home": 1,
                        "away": 0
                    },
                    "fulltime": {
                        "home": 2,
                        "away": 0
                    },
                    "extratime": {
                        "home": null,
                        "away": null
                    },
                    "penalty": {
                        "home": null,
                        "away": null
                    }
                }
            },
            {
                "fixture": {
                    "id": 1223609,
                    "referee": "M. Mariani",
                    "timezone": "UTC",
                    "date": "2024-08-24T18:45:00+00:00",
                    "timestamp": 1724525100,
                    "periods": {
                        "first": 1724525100,
                        "second": 1724528700
                    },
                    "venue": {
                        "id": 12086,
                        "name": "U-Power Stadium",
                        "city": "Monza"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 135,
                    "name": "Serie A",
                    "country": "Italy",
                    "logo": "https://media.api-sports.io/football/leagues/135.png",
                    "flag": "https://media.api-sports.io/flags/it.svg",
                    "season": 2024,
                    "round": "Regular Season - 2"
                },
                "teams": {
                    "home": {
                        "id": 1579,
                        "name": "Monza",
                        "logo": "https://media.api-sports.io/football/teams/1579.png",
                        "winner": false
                    },
                    "away": {
                        "id": 495,
                        "name": "Genoa",
                        "logo": "https://media.api-sports.io/football/teams/495.png",
                        "winner": true
                    }
                },
                "goals": {
                    "home": 0,
                    "away": 1
                },
                "score": {
                    "halftime": {
                        "home": 0,
                        "away": 1
                    },
                    "fulltime": {
                        "home": 0,
                        "away": 1
                    },
                    "extratime": {
                        "home": null,
                        "away": null
                    },
                    "penalty": {
                        "home": null,
                        "away": null
                    }
                }
            },
            {
                "fixture": {
                    "id": 1223614,
                    "referee": "D. Doveri",
                    "timezone": "UTC",
                    "date": "2024-08-24T16:30:00+00:00",
                    "timestamp": 1724517000,
                    "periods": {
                        "first": 1724517000,
                        "second": 1724520600
                    },
                    "venue": {
                        "id": 20416,
                        "name": "Bluenergy Stadium",
                        "city": "Udine"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 135,
                    "name": "Serie A",
                    "country": "Italy",
                    "logo": "https://media.api-sports.io/football/leagues/135.png",
                    "flag": "https://media.api-sports.io/flags/it.svg",
                    "season": 2024,
                    "round": "Regular Season - 2"
                },
                "teams": {
                    "home": {
                        "id": 494,
                        "name": "Udinese",
                        "logo": "https://media.api-sports.io/football/teams/494.png",
                        "winner": true
                    },
                    "away": {
                        "id": 487,
                        "name": "Lazio",
                        "logo": "https://media.api-sports.io/football/teams/487.png",
                        "winner": false
                    }
                },
                "goals": {
                    "home": 2,
                    "away": 1
                },
                "score": {
                    "halftime": {
                        "home": 1,
                        "away": 0
                    },
                    "fulltime": {
                        "home": 2,
                        "away": 1
                    },
                    "extratime": {
                        "home": null,
                        "away": null
                    },
                    "penalty": {
                        "home": null,
                        "away": null
                    }
                }
            }
        ]
    }
""".trimIndent()
