partial-response
======

[API's Partial Response](http://yaoganglian.com/2013/07/01/partial-response/) demo project powered by Spring Boot

### Example

###### Complete Response

The API's complete response can be seen via `localhost:8080` as follows:
<pre><code>
{  
   "nation":"Japan",
   "info":{  
      "state":"Tokyo",
      "details":[  
         {  
            "type":"vegetable",
            "number":0,
            "subdetails":[  
               {  
                  "code":"1",
                  "name":"tomato",
                  "price":100
               },
               {  
                  "code":"2",
                  "name":"potato",
                  "price":55
               }
            ]
         }
      ]
   }
}
</code></pre>

###### Partial Response

`http://localhost:8080/?fields=info(state,details(subdetails(name)))` gives:

<pre><code>
{  
   "info":{  
      "state":"Tokyo",
      "details":[  
         {  
            "subdetails":[  
               {  
                  "name":"tomato"
               },
               {  
                  "name":"potato"
               }
            ]
         }
      ]
   }
}
</code></pre>

### Error Handling

#### Malicious Input

`http://localhost:8080/?fields=%3Cscript%3Ealert(malicious)%3C/script%3E` gives:
<pre><code>
{  
   "nation":"Japan",
   "info":{  
      "state":"Tokyo",
      "details":[  
         {  
            "type":"vegetable",
            "number":0,
            "subdetails":[  
               {  
                  "code":"1",
                  "name":"tomato",
                  "price":100
               },
               {  
                  "code":"2",
                  "name":"potato",
                  "price":55
               }
            ]
         }
      ]
   }
}
</code></pre>

#### Whitespace

`http://localhost:8080/?fields=nati%20%20on,info` gives:
<pre><code>
{  
   "nation":"Japan",
   "info":{  
      "state":"Tokyo",
      "details":[  
         {  
            "type":"vegetable",
            "number":0,
            "subdetails":[  
               {  
                  "code":"1",
                  "name":"tomato",
                  "price":100
               },
               {  
                  "code":"2",
                  "name":"potato",
                  "price":55
               }
            ]
         }
      ]
   }
}
</code></pre>


#### Non-existent Properties

###### All child properties are non-existent

When a certain property's all child properties are non-existent, all the child properties will be returned.
`http://localhost:8080/?fields=info(details(subdetails(NONEXISTENT)))` gives:
<pre><code>
{  
   "info":{
      "details":[  
         {  
            "subdetails":[  // all child properties are returned
               {  
                  "code":"1",
                  "name":"tomato",
                  "price":100
               },
               {  
                  "code":"2",
                  "name":"potato",
                  "price":55
               }
            ]
         }
      ]
   }
}
</code></pre>

###### A part of child properties are non-existent
When a certain property's child properties are partially non-existent, the non-existent properties are ignored.
`http://localhost:8080/?fields=info(details(subdetails(name,NONEXISTENT)))` gives:
<pre><code>
{  
   "info":{  
      "details":[  
         {  
            "subdetails":[  // "name" is returned, while "NONEXISTENT" is ignored
               {  
                  "name":"tomato"
               },
               {  
                  "name":"potato"
               }
            ]
         }
      ]
   }
}
</code></pre>