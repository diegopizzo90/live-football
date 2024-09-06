package com.diegopizzo.core.config

val matchesEmpty = """
    {
        "get": "fixtures",
        "parameters": {
            "from": "2024-08-31",
            "league": "1",
            "to": "2024-08-31",
            "season": "2024"
        },
        "errors": [],
        "results": 0,
        "paging": {
            "current": 1,
            "total": 1
        },
        "response": []
    }
""".trimIndent()

val matchesPremier = """
    {
        "get": "fixtures",
        "parameters": {
            "from": "2024-08-31",
            "league": "39",
            "to": "2024-08-31",
            "season": "2024"
        },
        "errors": [],
        "results": 7,
        "paging": {
            "current": 1,
            "total": 1
        },
        "response": [
            {
                "fixture": {
                    "id": 1208041,
                    "referee": "C. Kavanagh",
                    "timezone": "UTC",
                    "date": "2024-08-31T11:30:00+00:00",
                    "timestamp": 1725103800,
                    "periods": {
                        "first": 1725103800,
                        "second": 1725107400
                    },
                    "venue": {
                        "id": 494,
                        "name": "Emirates Stadium",
                        "city": "London"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 39,
                    "name": "Premier League",
                    "country": "England",
                    "logo": "https://media.api-sports.io/football/leagues/39.png",
                    "flag": "https://media.api-sports.io/flags/gb.svg",
                    "season": 2024,
                    "round": "Regular Season - 3"
                },
                "teams": {
                    "home": {
                        "id": 42,
                        "name": "Arsenal",
                        "logo": "https://media.api-sports.io/football/teams/42.png",
                        "winner": null
                    },
                    "away": {
                        "id": 51,
                        "name": "Brighton",
                        "logo": "https://media.api-sports.io/football/teams/51.png",
                        "winner": null
                    }
                },
                "goals": {
                    "home": 1,
                    "away": 1
                },
                "score": {
                    "halftime": {
                        "home": 1,
                        "away": 0
                    },
                    "fulltime": {
                        "home": 1,
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
                    "id": 1208042,
                    "referee": "J. Smith",
                    "timezone": "UTC",
                    "date": "2024-08-31T14:00:00+00:00",
                    "timestamp": 1725112800,
                    "periods": {
                        "first": 1725112800,
                        "second": 1725116400
                    },
                    "venue": {
                        "id": 10503,
                        "name": "Gtech Community Stadium",
                        "city": "Brentford, Middlesex"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 39,
                    "name": "Premier League",
                    "country": "England",
                    "logo": "https://media.api-sports.io/football/leagues/39.png",
                    "flag": "https://media.api-sports.io/flags/gb.svg",
                    "season": 2024,
                    "round": "Regular Season - 3"
                },
                "teams": {
                    "home": {
                        "id": 55,
                        "name": "Brentford",
                        "logo": "https://media.api-sports.io/football/teams/55.png",
                        "winner": true
                    },
                    "away": {
                        "id": 41,
                        "name": "Southampton",
                        "logo": "https://media.api-sports.io/football/teams/41.png",
                        "winner": false
                    }
                },
                "goals": {
                    "home": 3,
                    "away": 1
                },
                "score": {
                    "halftime": {
                        "home": 1,
                        "away": 0
                    },
                    "fulltime": {
                        "home": 3,
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
                    "id": 1208044,
                    "referee": "S. Attwell",
                    "timezone": "UTC",
                    "date": "2024-08-31T14:00:00+00:00",
                    "timestamp": 1725112800,
                    "periods": {
                        "first": 1725112800,
                        "second": 1725116400
                    },
                    "venue": {
                        "id": 8560,
                        "name": "Goodison Park",
                        "city": "Liverpool"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 39,
                    "name": "Premier League",
                    "country": "England",
                    "logo": "https://media.api-sports.io/football/leagues/39.png",
                    "flag": "https://media.api-sports.io/flags/gb.svg",
                    "season": 2024,
                    "round": "Regular Season - 3"
                },
                "teams": {
                    "home": {
                        "id": 45,
                        "name": "Everton",
                        "logo": "https://media.api-sports.io/football/teams/45.png",
                        "winner": false
                    },
                    "away": {
                        "id": 35,
                        "name": "Bournemouth",
                        "logo": "https://media.api-sports.io/football/teams/35.png",
                        "winner": true
                    }
                },
                "goals": {
                    "home": 2,
                    "away": 3
                },
                "score": {
                    "halftime": {
                        "home": 0,
                        "away": 0
                    },
                    "fulltime": {
                        "home": 2,
                        "away": 3
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
                    "id": 1208045,
                    "referee": "L. Smith",
                    "timezone": "UTC",
                    "date": "2024-08-31T14:00:00+00:00",
                    "timestamp": 1725112800,
                    "periods": {
                        "first": 1725112800,
                        "second": 1725116400
                    },
                    "venue": {
                        "id": 545,
                        "name": "Portman Road",
                        "city": "Ipswich, Suffolk"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 39,
                    "name": "Premier League",
                    "country": "England",
                    "logo": "https://media.api-sports.io/football/leagues/39.png",
                    "flag": "https://media.api-sports.io/flags/gb.svg",
                    "season": 2024,
                    "round": "Regular Season - 3"
                },
                "teams": {
                    "home": {
                        "id": 57,
                        "name": "Ipswich",
                        "logo": "https://media.api-sports.io/football/teams/57.png",
                        "winner": null
                    },
                    "away": {
                        "id": 36,
                        "name": "Fulham",
                        "logo": "https://media.api-sports.io/football/teams/36.png",
                        "winner": null
                    }
                },
                "goals": {
                    "home": 1,
                    "away": 1
                },
                "score": {
                    "halftime": {
                        "home": 1,
                        "away": 1
                    },
                    "fulltime": {
                        "home": 1,
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
                    "id": 1208046,
                    "referee": "D. Coote",
                    "timezone": "UTC",
                    "date": "2024-08-31T14:00:00+00:00",
                    "timestamp": 1725112800,
                    "periods": {
                        "first": 1725112800,
                        "second": 1725116400
                    },
                    "venue": {
                        "id": 547,
                        "name": "King Power Stadium",
                        "city": "Leicester, Leicestershire"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 39,
                    "name": "Premier League",
                    "country": "England",
                    "logo": "https://media.api-sports.io/football/leagues/39.png",
                    "flag": "https://media.api-sports.io/flags/gb.svg",
                    "season": 2024,
                    "round": "Regular Season - 3"
                },
                "teams": {
                    "home": {
                        "id": 46,
                        "name": "Leicester",
                        "logo": "https://media.api-sports.io/football/teams/46.png",
                        "winner": false
                    },
                    "away": {
                        "id": 66,
                        "name": "Aston Villa",
                        "logo": "https://media.api-sports.io/football/teams/66.png",
                        "winner": true
                    }
                },
                "goals": {
                    "home": 1,
                    "away": 2
                },
                "score": {
                    "halftime": {
                        "home": 0,
                        "away": 1
                    },
                    "fulltime": {
                        "home": 1,
                        "away": 2
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
                    "id": 1208049,
                    "referee": "S. Hooper",
                    "timezone": "UTC",
                    "date": "2024-08-31T14:00:00+00:00",
                    "timestamp": 1725112800,
                    "periods": {
                        "first": 1725112800,
                        "second": 1725116400
                    },
                    "venue": {
                        "id": 566,
                        "name": "The City Ground",
                        "city": "Nottingham, Nottinghamshire"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 39,
                    "name": "Premier League",
                    "country": "England",
                    "logo": "https://media.api-sports.io/football/leagues/39.png",
                    "flag": "https://media.api-sports.io/flags/gb.svg",
                    "season": 2024,
                    "round": "Regular Season - 3"
                },
                "teams": {
                    "home": {
                        "id": 65,
                        "name": "Nottingham Forest",
                        "logo": "https://media.api-sports.io/football/teams/65.png",
                        "winner": null
                    },
                    "away": {
                        "id": 39,
                        "name": "Wolves",
                        "logo": "https://media.api-sports.io/football/teams/39.png",
                        "winner": null
                    }
                },
                "goals": {
                    "home": 1,
                    "away": 1
                },
                "score": {
                    "halftime": {
                        "home": 1,
                        "away": 1
                    },
                    "fulltime": {
                        "home": 1,
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
                    "id": 1208050,
                    "referee": "M. Oliver",
                    "timezone": "UTC",
                    "date": "2024-08-31T16:30:00+00:00",
                    "timestamp": 1725121800,
                    "periods": {
                        "first": 1725121800,
                        "second": 1725125400
                    },
                    "venue": {
                        "id": 598,
                        "name": "London Stadium",
                        "city": "London"
                    },
                    "status": {
                        "long": "Match Finished",
                        "short": "FT",
                        "elapsed": 90
                    }
                },
                "league": {
                    "id": 39,
                    "name": "Premier League",
                    "country": "England",
                    "logo": "https://media.api-sports.io/football/leagues/39.png",
                    "flag": "https://media.api-sports.io/flags/gb.svg",
                    "season": 2024,
                    "round": "Regular Season - 3"
                },
                "teams": {
                    "home": {
                        "id": 48,
                        "name": "West Ham",
                        "logo": "https://media.api-sports.io/football/teams/48.png",
                        "winner": false
                    },
                    "away": {
                        "id": 50,
                        "name": "Manchester City",
                        "logo": "https://media.api-sports.io/football/teams/50.png",
                        "winner": true
                    }
                },
                "goals": {
                    "home": 1,
                    "away": 3
                },
                "score": {
                    "halftime": {
                        "home": 1,
                        "away": 2
                    },
                    "fulltime": {
                        "home": 1,
                        "away": 3
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

val matchesSerieA = """
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
