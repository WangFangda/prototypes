ranking-redis
======

Ranking demo project powered by Spring Boot and Redis

### Procedure

- install Redis on local
- run the program in IntelliJ (suggested)
- open in browser `localhost:8080/init` to insert data in Redis
- open in browser `localhost:8080/update/LeParc/3.6` to update data in Redis
- open in browser `localhost:8080/leaderboard` to see all rankings
- open in browser `localhost:8080/leaderboard/tokyo:dim%20sum` to see a specific ranking

### Example

`http://localhost:8080/leaderboard` returns
<pre><code>
[
  {
    "type": "tokyo:dim sum",
    "data": [
      {
        "score": 3.8,
        "value": "ParadiseDynasty"
      },
      {
        "score": 3.8,
        "value": "DinTaiFung"
      },
      {
        "score": 3.7,
        "value": "LeParc"
      }
    ]
  },
  {
    "type": "tokyo:japanese food",
    "data": [
      {
        "score": 5,
        "value": "RyuGin"
      }
    ]
  }
]
</code></pre>


### Error Handling

###### Bad Request

`localhost:8080/update/LeParc/5.1` gives
<pre><code>
HTTP/1.1 400
Content-Length: 0
</code></pre>

###### Not Found

The following requests
- `localhost:8080/leaderboard/tokyo:dim`
- `localhost:8080/update/XXX/5.0`
give 
<pre><code>
HTTP/1.1 404
Content-Length: 0
</code></pre>

### Reminder

This project aims only to show a ranking retrieval with the help of Redis, thus many functionalities (e.g., logging) are not implemented. Please kindly include the necessary functionalities on production environment.

### Contact
wangfonda@gmail.com