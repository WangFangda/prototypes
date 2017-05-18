google-api
======

Google developer API demo project powered by Spring Boot and CompletableFuture

### Procedure

- get a key from [Google Web Service](https://developers.google.com/places/web-service/get-api-key)
- replace your key with `***please use your own Google Developer Key***` in src/main/resources/application.properties


### Example

###### Empty Input
`http://localhost:8080/geocode/?address=` gives:
<pre><code>[]</pre></code>

###### Keyword
`http://localhost:8080/geocode/?address=ryugin` gives:
<pre><code>
[  
   {  
      "name":"日本料理 龍吟",
      "formatted_address":"Japan, 〒1060032 東京都港区 六本木7丁目17−24 eisu bldg.１階",
      "international_phone_number":"+81 3-3423-8006",
      "opening_hours":[  
         "Monday: 6:00 PM – 1:00 AM",
         "Tuesday: 6:00 PM – 1:00 AM",
         "Wednesday: 6:00 PM – 1:00 AM",
         "Thursday: 6:00 PM – 1:00 AM",
         "Friday: 6:00 PM – 1:00 AM",
         "Saturday: 6:00 PM – 1:00 AM",
         "Sunday: Closed"
      ],
      "location":{  
         "lat":35.662775,
         "lng":139.728287
      },
      "types":[  
         "establishment",
         "food",
         "point_of_interest",
         "restaurant"
      ]
   }
]
</pre></code>

###### Address
`http://localhost:8080/geocode/?address=%E8%B1%8A%E5%B3%B6%E5%8C%BA,
%201%E2%88%92%EF%BC%94%EF%BC%93%E2%88%92%EF%BC%93%20%E6%97%A5%E7%B2%BE%E3%83%93%E3%83%AB` gives:
<pre><code>
[  
   {  
      "name":"ロマーニ",
      "formatted_address":"Japan, Tokyo, Toshima, Nishiikebukuro, 1 Chome−４３−３ 日精ビル B1F",
      "international_phone_number":"+81 3-3987-4011",
      "location":{  
         "lat":35.73295299999999,
         "lng":139.7113274
      },
      "types":[  
         "establishment",
         "point_of_interest"
      ]
   }
]
</pre></code>

### Reminder

This project aims only to show how to use [Google Place Details API](https://developers.google
.com/places/web-service/details) and [Google Geocode API](https://developers.google
.com/maps/documentation/geocoding/intro), thus many functionalities (e.g., error handling) are not implemented. Please 
kindly include the necessary functionalities on production environment.


### Contact
wangfonda@gmail.com