package com.diegopizzo.match.api.network.util

internal val matchApiResponse = """
    {
        "get": "fixtures",
        "parameters": {
            "from": "2024-08-24",
            "league": "135",
            "to": "2024-08-25",
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
                    "id": 1223606,
                    "referee": "Simone Sozza, Italy",
                    "timezone": "UTC",
                    "date": "2024-08-25T16:30:00+00:00",
                    "timestamp": 1724603400,
                    "periods": {
                        "first": null,
                        "second": null
                    },
                    "venue": {
                        "id": 902,
                        "name": "Stadio Artemio Franchi",
                        "city": "Firenze"
                    },
                    "status": {
                        "long": "Not Started",
                        "short": "NS",
                        "elapsed": null
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
                        "id": 502,
                        "name": "Fiorentina",
                        "logo": "https://media.api-sports.io/football/teams/502.png",
                        "winner": null
                    },
                    "away": {
                        "id": 517,
                        "name": "Venezia",
                        "logo": "https://media.api-sports.io/football/teams/517.png",
                        "winner": null
                    }
                },
                "goals": {
                    "home": null,
                    "away": null
                },
                "score": {
                    "halftime": {
                        "home": null,
                        "away": null
                    },
                    "fulltime": {
                        "home": null,
                        "away": null
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
                    "referee": "Maurizio Mariani, Italy",
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
                    "id": 1223610,
                    "referee": "L. Pairetto",
                    "timezone": "UTC",
                    "date": "2024-08-25T18:45:00+00:00",
                    "timestamp": 1724611500,
                    "periods": {
                        "first": null,
                        "second": null
                    },
                    "venue": {
                        "id": 11904,
                        "name": "Stadio Diego Armando Maradona",
                        "city": "Napoli"
                    },
                    "status": {
                        "long": "Not Started",
                        "short": "NS",
                        "elapsed": null
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
                        "id": 492,
                        "name": "Napoli",
                        "logo": "https://media.api-sports.io/football/teams/492.png",
                        "winner": null
                    },
                    "away": {
                        "id": 500,
                        "name": "Bologna",
                        "logo": "https://media.api-sports.io/football/teams/500.png",
                        "winner": null
                    }
                },
                "goals": {
                    "home": null,
                    "away": null
                },
                "score": {
                    "halftime": {
                        "home": null,
                        "away": null
                    },
                    "fulltime": {
                        "home": null,
                        "away": null
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
                    "referee": "Daniele Doveri, Italy",
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
